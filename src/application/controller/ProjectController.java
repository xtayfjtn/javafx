package application.controller;

import application.util.TextFieldTreeCellImpl;
import application.dao.ClauseDao;
import application.dao.ProjectDao;
import application.impl.ClauseDaoImpl;
import application.impl.ProjectDaoImpl;
import application.model.Clause;
import application.model.Project;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ZQ on 2017/4/6.
 */
public class ProjectController implements Initializable {
    private MainController mainController;

    @FXML
    TreeView<Object> projectTree;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void init(MainController controller) {
        mainController = controller;
    }

    //画出工程的树
    public void newProject(String name) {
        //存入数据库
        Project project = new Project();
        project.setProjectName(name);
        ProjectDao projectDao = new ProjectDaoImpl();
        projectDao.insert(project);
        project.setProject_id(projectDao.findByProjectName(name).getProject_id());

        //画出界面
        TreeItem<Object> mRoot = new TreeItem<>(project);
        projectTree.setRoot(mRoot);
        projectTree.setEditable(true);
        projectTree.setCellFactory((TreeView<Object> p) -> new TextFieldTreeCellImpl(mainController));
    }

    public void openProject(String name) {
        ProjectDao projectDao = new ProjectDaoImpl();
        Project project = projectDao.findByProjectName(name);
        TreeItem<Object> mRoot = new TreeItem<>(project.getProjectName());

        ClauseDao clauseDao = new ClauseDaoImpl();
        List<Clause> clauses = clauseDao.selectByProject(project.getProject_id());
        Iterator<Clause> iterator = clauses.iterator();
        while (iterator.hasNext()) {
            Clause c = iterator.next();
            TreeItem item = new TreeItem(c);
            drawTree(item, c);
            mRoot.getChildren().add(item);
        }
        projectTree.setRoot(mRoot);
        projectTree.setEditable(true);
        projectTree.setCellFactory((TreeView<Object> p) -> new TextFieldTreeCellImpl(mainController));
//        projectTree.setCellFactory((ModelTreeItem<String> p) -> new TextFieldTreeCellImpl(mainController));
    }

    public void drawTree(TreeItem father, Clause clause) {
        ClauseDao clauseDao = new ClauseDaoImpl();
        List<Clause> clauses = clauseDao.selectByFatherClause(clause.getClause_id());
        if (clauses == null) {
            return;
        } else {
            Iterator<Clause> iterator = clauses.iterator();
            while (iterator.hasNext()) {
                Clause c = iterator.next();
                TreeItem<Clause> item = new TreeItem<Clause>(c);
//                TreeItem<Object> item = new TreeItem(c.getClauseName());
//                ModelTreeItem item = new ModelTreeItem(c.getClauseName(), projectTree);
                father.getChildren().add(item);
                drawTree(item, c);
            }
        }
    }

    public void newModel(String name, TextFieldTreeCellImpl textFieldTreeCell) {
        Clause clause = new Clause();
        clause.setClauseName(name);
        clause.setcType("模块");


        if (textFieldTreeCell.getItem() instanceof Project) {
            Project project = (Project) textFieldTreeCell.getItem();
            clause.setProject_id(project.getProject_id());
            ClauseDao clauseDao = new ClauseDaoImpl();
            clauseDao.insert(clause);
            clause.setClause_id(clauseDao.getMaxId());
        } else if (textFieldTreeCell.getItem() instanceof Clause) {
            Clause clause1 = (Clause) textFieldTreeCell.getItem();
            clause.setFatherClause_id(clause1.getClause_id());
            clause.setProject_id(clause1.getProject_id());
            ClauseDao clauseDao = new ClauseDaoImpl();
            clauseDao.insert(clause);
            clause.setClause_id(clauseDao.getMaxId());
        }
        TreeItem newModel = new TreeItem<>(clause);
        textFieldTreeCell.getTreeItem().getChildren().add(newModel);
    }

    public void newFunc(String name, TextFieldTreeCellImpl textFieldTreeCell) {
        Clause clause = new Clause();
        clause.setClauseName(name);
        clause.setcType("功能点");

        if (textFieldTreeCell.getItem() instanceof Clause) {
            Clause clause1 = (Clause) textFieldTreeCell.getItem();
            clause.setProject_id(clause1.getProject_id());
            clause.setFatherClause_id(clause1.getClause_id());
            ClauseDao clauseDao = new ClauseDaoImpl();
            clauseDao.insert(clause);
            clause.setClause_id(clauseDao.getMaxId());
        }
        TreeItem newModel = new TreeItem(clause);
        textFieldTreeCell.getTreeItem().getChildren().add(newModel);

    }
}
