package application.controller;

import application.view.DrawPane;
import application.view.LineCom;
import application.util.TextFieldTreeCellImpl;
import application.dao.ClauseDao;
import application.dao.LineDao;
import application.impl.ClauseDaoImpl;
import application.impl.LineDaoImpl;
import application.model.Clause;
import application.model.Line;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Label mLable;
    @FXML MenuController menuController;
    @FXML ProjectController projectController;
    @FXML MiddleController middleController;
    @FXML ComponentController componentController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuController.init(this);
        projectController.init(this);
        middleController.init(this);
        componentController.init(this);
        //initProject();
    }

    //为新建的工程的初始化方法进行调用
    public void initProject() {
        middleController.initProject();
    }

    //在工程目录中添加树视图
    public void addProject(String name) {
        projectController.newProject(name);
    }

//    public void editModel(TextFieldTreeCellImpl textFieldTreeCell) {
//        System.out.println(textFieldTreeCell.getText());
//    }

    //查看模块的信息
    public void editModel(TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.modelInfo(textFieldTreeCell);
    }
    //初始化模块信息
    public void initModel(TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.initModel(textFieldTreeCell);
    }
    public void addModel(String name, TextFieldTreeCellImpl textFieldTreeCell) {
        projectController.newModel(name, textFieldTreeCell);

    }

    //查看功能点信息
    public void editFunc(TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.funcInfo(textFieldTreeCell);
    }


    public void initFunc(TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.initFunc(textFieldTreeCell);
    }

    public void showComponent() {
        componentController.showPane();
    }

    public void hideComponent() {
        componentController.hidePane();
    }

    public void openProject() {
        //暂时写死读取的project。
        projectController.openProject("我的天");
    }

    public void addFunc(String name, TextFieldTreeCellImpl textFieldTreeCell) {
        projectController.newFunc(name, textFieldTreeCell);
    }

    public void saveModel(TextFieldTreeCellImpl textFieldTreeCell, String name, String info, DrawPane modelAction) {
        if (textFieldTreeCell.getItem() instanceof Clause) {
            Clause clause = (Clause) textFieldTreeCell.getItem();
            clause.setcType("模块");
            clause.setClauseName(name);
            clause.setDescription(info);
            ClauseDao clauseDao = new ClauseDaoImpl();
            clauseDao.update(clause);

            int shapeNum = modelAction.getChildren().size();
            for (int i = 0; i < shapeNum; i++) {
                if (modelAction.getChildren().get(i) instanceof LineCom) {
                    LineCom lineCom = (LineCom) modelAction.getChildren().get(i);
                    LineDao lineDao = new LineDaoImpl();
                    Line line = new Line();
                    line.setStart_id(lineCom.getStart_id());
                    line.setEnd_id(lineCom.getEnd_id());
                    line.setClause_id(modelAction.getClause_id());
                    line.setLine_id(lineCom.getLine_id());
                    lineDao.insertUpdate(line);
                }
            }
        }
    }

    public void saveFunc(TextFieldTreeCellImpl textFieldTreeCell, String name, String description, String input, String output, DrawPane funcAction) {
        if (textFieldTreeCell.getItem() instanceof Clause) {
            Clause clause = (Clause) textFieldTreeCell.getItem();
            clause.setcType("功能点");
            clause.setClauseName(name);
            clause.setDescription(description);
            ClauseDao clauseDao = new ClauseDaoImpl();
            clauseDao.update(clause);

            int shapeNum = funcAction.getChildren().size();
            for (int i = 0; i < shapeNum; i++) {
                if (funcAction.getChildren().get(i) instanceof LineCom) {
                    LineCom lineCom = (LineCom) funcAction.getChildren().get(i);
                    LineDao lineDao = new LineDaoImpl();
                    Line line = new Line();
                    line.setStart_id(lineCom.getStart_id());
                    line.setEnd_id(lineCom.getEnd_id());
                    line.setClause_id(funcAction.getClause_id());
                    line.setLine_id(lineCom.getLine_id());
                    lineDao.insertUpdate(line);
                }
            }
//            Clause clause = (Clause) textFieldTreeCell.getItem();
//            clause.setClauseName(funcName);
//            clause.setDescription(description);
//            ParameterDao parameterDao = new ParameterDaoImpl();
//            Parameter inputParameter = parameterDao.getInputParameter(clause.getClause_id());
//            inputParameter.setParameterName(input);
//            inputParameter.setClause_id(clause.getClause_id());
//            parameterDao.insertUpdate(inputParameter);
////            inputParameter.setParameter_id(parameterDao.getMaxId());
//
//            Parameter outParameter = parameterDao.getOutputParameter(clause.getClause_id());
//            outParameter.setParameterName(output);
//            outParameter.setClause_id(clause.getClause_id());
//            parameterDao.insertUpdate(outParameter);
//
//            ClauseDao clauseDao = new ClauseDaoImpl();
//            clauseDao.update(clause);
//
//            int shapeNum = funcAction.getChildren().size();
//            for (int i = 0; i < shapeNum; i++) {
//                if (funcAction.getChildren().get(i) instanceof LineCom) {
//                    LineCom lineCom = (LineCom) funcAction.getChildren().get(i);
//                    LineDao lineDao = new LineDaoImpl();
//                    Line line = new Line();
//                    line.setStart_id(lineCom.getStart_id());
//                    line.setEnd_id(lineCom.getEnd_id());
//                    line.setClause_id(funcAction.getClause_id());
//                    line.setLine_id(lineCom.getLine_id());
//                    lineDao.insertUpdate(line);
//                }
//            }
        }
    }

//    public void insertLines()
}
