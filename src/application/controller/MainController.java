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
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

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

    /*
    打开工程
     */
    public void openProject() {
        //暂时写死读取的project。
        projectController.openProject("测试用例");
    }

    /*
    在工程目录中添加树视图
     */
    public void addProject(String name) {
        projectController.newProject(name);
    }

    /*
    为新建的工程的初始化方法进行调用
     */
    public void initProject() {
        middleController.initProject();
    }

    /*
    新建功能模块
     */
    public void addModule(String name, TextFieldTreeCellImpl textFieldTreeCell) {
        projectController.newModule(name, textFieldTreeCell);
    }

    /*
    初始化模块信息
     */
    public void initModule(TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.initModule(textFieldTreeCell);
    }

    /*
    查看/编辑模块的信息
     */
    public void editModule(TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.moduleInfo(textFieldTreeCell);
    }

    /*
    保存模块信息
     */
    public void saveModule(TextFieldTreeCellImpl textFieldTreeCell, String name, String info, DrawPane modelAction) {
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


    /*
    以下是功能点相关方法。
     */

    /*
    添加功能点
     */
    public void addFunc(String name, TextFieldTreeCellImpl textFieldTreeCell) {
        projectController.newFunc(name, textFieldTreeCell);
    }

    /*
    初始化功能点
     */
    public void initFunc(TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.initFunc(textFieldTreeCell);
    }

    /*
    查看/编辑功能点信息
     */
    public void editFunc(TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.funcInfo(textFieldTreeCell);
    }

    /*
    保存功能点。
     */
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
        }
    }


    /*
    显示工具栏
     */
    public void showComponent() {
        componentController.showPane();
    }

    /*
    隐藏工具栏。
     */
    public void hideComponent() {
        componentController.hidePane();
    }






}
