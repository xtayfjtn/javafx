package application.controller;

import application.Util.DrawPane;
import application.Util.Loger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ZQ on 2017/4/8.
 */
public class MiddleController implements Initializable {
    private MainController mainController;
    @FXML private GridPane mMiddle;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void init(MainController controller) {
        this.mainController = controller;
    }

    /*
    项目涉及到的函数
     */

    //对项目进行初始化 输入工程名等信息
    public void projectInition() {
        mMiddle.getChildren().clear();
        mainController.hideComponent();
        Label projectname = new Label("工程名：");
        TextField nametxt = new TextField();
        Button confirm = new Button("确定");
        confirm.setOnAction(event -> {
            String name = nametxt.getText();
            if (name.equals("")) {
                Loger.logi("工程名为空");
            } else {
                mainController.addProjcet(name);
                mMiddle.getChildren().clear();
            }
        });
        Button cancel = new Button("取消");
        cancel.setOnAction(event -> {
            mMiddle.getChildren().clear();
        });
        GridPane.setHalignment(projectname, HPos.RIGHT);
        GridPane.setHalignment(confirm, HPos.RIGHT);
        GridPane.setHalignment(cancel, HPos.CENTER);
        mMiddle.add(projectname, 0, 0);
        mMiddle.add(nametxt, 1, 0);
        mMiddle.add(confirm, 0, 1);
        mMiddle.add(cancel, 1, 1);
    }

    /*
    模块涉及函数
     */
    //新建模块
    public void initModel(ProjectController.TextFieldTreeCellImpl textFieldTreeCell) {
        mMiddle.getChildren().clear();
        mainController.hideComponent();
        Label projectname = new Label("模块名：");
        TextField nametxt = new TextField("新模块");
        Button confirm = new Button("确定");
        confirm.setOnAction(event -> {
            String name = nametxt.getText();
            if (name.equals("")) {
                Loger.logi("模块名为空");
            } else {
                mMiddle.getChildren().clear();
                TreeItem newModel = new TreeItem<>(name);
                textFieldTreeCell.getTreeItem().getChildren().addAll(newModel);
            }
        });
        Button cancel = new Button("取消");
        cancel.setOnAction(event -> {
            mMiddle.getChildren().clear();
        });

        GridPane.setHalignment(projectname, HPos.RIGHT);
        GridPane.setHalignment(confirm, HPos.RIGHT);
        GridPane.setHalignment(cancel, HPos.CENTER);
        mMiddle.add(projectname, 0, 0);
        mMiddle.add(nametxt, 1, 0);
        mMiddle.add(confirm, 0, 1);
        mMiddle.add(cancel, 1, 1);
    }

    //模块详细描述
    public void infoShown(ProjectController.TextFieldTreeCellImpl textFieldTreeCell) {
        mMiddle.getChildren().clear();
        mainController.showComponent();
        Label projectname = new Label("模块名：");
        Label modelInfo = new Label("模块描述：");
        Label modelActionlab = new Label("模块行为：");

        TextField nametxt = new TextField(textFieldTreeCell.getText());
        nametxt.setEditable(true);
        TextArea infoText = new TextArea(textFieldTreeCell.modelInfo);
        DrawPane modelAction = new DrawPane();
        modelAction.getStyleClass().add("drawpane");
        Button confirmbtn = new Button("确定");
        confirmbtn.setOnAction(event -> {
            mMiddle.getChildren().clear();
            textFieldTreeCell.modelInfo = infoText.getText();
            mainController.hideComponent();
        });
        Button cancel = new Button("取消");
        cancel.setOnAction(event -> {
            mMiddle.getChildren().clear();
            mainController.hideComponent();
        });

        GridPane.setHalignment(projectname, HPos.RIGHT);
        GridPane.setHalignment(modelInfo, HPos.RIGHT);
        GridPane.setHalignment(modelActionlab, HPos.RIGHT);
        GridPane.setHalignment(confirmbtn, HPos.RIGHT);
        GridPane.setHalignment(cancel, HPos.CENTER);
        mMiddle.add(projectname, 0, 0, 1, 1);
        mMiddle.add(nametxt, 1, 0, 1, 1);
        mMiddle.add(modelInfo, 0, 1, 1, 1);
        mMiddle.add(infoText, 1, 1, 1, 1);
        mMiddle.add(modelActionlab, 0, 2, 1, 1);
        mMiddle.add(modelAction, 1, 2, 2, 2);
        mMiddle.add(confirmbtn, 0, 4);
        mMiddle.add(cancel, 1, 4);
    }


    /*
    功能点设计函数
     */
    //展示功能点详细信息
    public void funcShown(ProjectController.TextFieldTreeCellImpl textFieldTreeCell) {
        mMiddle.getChildren().clear();
        mainController.showComponent();
        Label funcName = new Label("功能名称：");
        Label funcDisplay = new Label("功能描述：");
        Label inputlab = new Label("输入：");
        Label outputlab = new Label("输出：");
        Label actionlab = new Label("功能行为：");
        TextField funcNametxt = new TextField(textFieldTreeCell.getText());
        TextArea funcDisplaytxt = new TextArea(textFieldTreeCell.funcInfo);
        TextArea inputtxt = new TextArea(textFieldTreeCell.funcInput);
        TextArea outputtxt = new TextArea(textFieldTreeCell.funcOutput);
        DrawPane funcAction = new DrawPane();
        funcAction.getStyleClass().add("drawpane");
//        TextArea test = new TextArea();
        mMiddle.add(funcAction, 1, 4, 2, 1);

        Button confirmbtn = new Button("确定");
        confirmbtn.setOnAction(event -> {
            textFieldTreeCell.funcInfo = funcDisplaytxt.getText();
            textFieldTreeCell.funcInput = inputtxt.getText();
            textFieldTreeCell.funcOutput = outputtxt.getText();
            mMiddle.getChildren().clear();
            mainController.hideComponent();
        });
        Button cancelbtn = new Button("取消");
        cancelbtn.setOnAction(event -> {
            mMiddle.getChildren().clear();
            mainController.hideComponent();
        });

        GridPane.setHalignment(funcName, HPos.RIGHT);
        GridPane.setHalignment(funcDisplay, HPos.RIGHT);
        GridPane.setHalignment(inputlab, HPos.RIGHT);
        GridPane.setHalignment(outputlab, HPos.RIGHT);
        GridPane.setHalignment(actionlab, HPos.RIGHT);
        GridPane.setHalignment(confirmbtn, HPos.RIGHT);
        GridPane.setHalignment(cancelbtn, HPos.CENTER);
        mMiddle.add(funcName, 0, 0);
        mMiddle.add(funcDisplay, 0, 1);
        mMiddle.add(inputlab, 0, 2);
        mMiddle.add(outputlab, 0, 3);
        mMiddle.add(funcNametxt, 1, 0);
        mMiddle.add(funcDisplaytxt, 1, 1);
        mMiddle.add(inputtxt, 1, 2);
        mMiddle.add(outputtxt, 1, 3);

        mMiddle.add(actionlab, 0, 4);
        mMiddle.add(confirmbtn, 0, 5);
        mMiddle.add(cancelbtn, 1, 5);
    }

    //初始化功能点
    public void initFunc(ProjectController.TextFieldTreeCellImpl textFieldTreeCell) {
        mMiddle.getChildren().clear();
        Label funcname = new Label("功能点：");
        TextField nametxt = new TextField("新功能");
        Button confirm = new Button("确定");
        confirm.setOnAction(event -> {
            String name = nametxt.getText();
            if (name.equals("")) {
                Loger.loge("功能点名称为空");
            } else {
                mMiddle.getChildren().clear();
                TreeItem newFunc = new TreeItem<>(name);
                textFieldTreeCell.getTreeItem().getChildren().addAll(newFunc);
            }
        });
        Button cancel = new Button("取消");
        cancel.setOnAction(event -> {
            mMiddle.getChildren().clear();
        });

        GridPane.setHalignment(funcname, HPos.RIGHT);
        GridPane.setHalignment(confirm, HPos.RIGHT);
        GridPane.setHalignment(cancel, HPos.CENTER);
        mMiddle.add(funcname, 0, 0);
        mMiddle.add(nametxt, 1, 0);
        mMiddle.add(confirm, 0, 1);
        mMiddle.add(cancel, 1, 1);
    }
}
