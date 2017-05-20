package application.controller;

import application.Util.TextFieldTreeCellImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public void editModel(TextFieldTreeCellImpl textFieldTreeCell) {
        System.out.println(textFieldTreeCell.getText());
    }

    //查看模块的信息
    public void seeInfo(TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.infoShown(textFieldTreeCell);
    }
    //初始化模块信息
    public void initModel(TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.initModel(textFieldTreeCell);
    }
    public void addModel(String name, TextFieldTreeCellImpl textFieldTreeCell) {
        projectController.newModel(name, textFieldTreeCell);

    }

    //查看功能点信息
    public void funcedit(TextFieldTreeCellImpl textFieldTreeCell) {
        middleController.funcShown(textFieldTreeCell);
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
}
