package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private final Node rootIcon = new ImageView(
            new Image(getClass().getResourceAsStream("folder.png"))
    );
    private final Node modelIcon = new ImageView(
            new Image(getClass().getResourceAsStream("model.png"))
    );
    @FXML
    public Label mLable;
    @FXML MenuController menuController;
    @FXML ProjectController projectController;
    @FXML MiddleController middleController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuController.init(this);
        projectController.init(this);
        middleController.init(this);
        //initProject();
    }

    //为新建的工程的初始化方法进行调用
    public void initProject() {
        middleController.projectInition();
    }

    //在工程目录中添加树视图
    public void addProjcet(String name) {
        projectController.newProject(name);
    }

    public void editModel(ProjectController.TextFieldTreeCellImpl textFieldTreeCell) {
        System.out.println(textFieldTreeCell.getText());
    }

    //查看模块的信息
    public void seeInfo(ProjectController.TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.infoShown(textFieldTreeCell);
    }
    //新建模块
    public void addModel(ProjectController.TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.initModel(textFieldTreeCell);
    }

    //查看功能点信息
    public void funcedit(ProjectController.TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.funcShown(textFieldTreeCell);
    }


    public void addFunc(ProjectController.TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.initFunc(textFieldTreeCell);
    }
}
