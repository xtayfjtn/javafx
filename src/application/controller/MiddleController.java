package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        Label projectname = new Label("工程名");
        TextField nametxt = new TextField();
        Button confirm = new Button("确定");
        confirm.setOnAction(event -> {
            String name = nametxt.getText();
            if (name.equals("")) {
                System.out.println("null");
            } else {
                mainController.addProjcet(name);
                mMiddle.getChildren().clear();
                //System.out.println(name);
            }
        });
        Button cancel = new Button("取消");
        cancel.setOnAction(event -> {
            mMiddle.getChildren().clear();
        });
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
        System.out.println("middle.controller.initmodel");
        mMiddle.getChildren().clear();
        Label projectname = new Label("模块名");
        TextField nametxt = new TextField("新模块");
        Button confirm = new Button("确定");
        confirm.setOnAction(event -> {
            String name = nametxt.getText();
            if (name.equals("")) {
                System.out.println("null");
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
        mMiddle.add(projectname, 0, 0);
        mMiddle.add(nametxt, 1, 0);
        mMiddle.add(confirm, 0, 1);
        mMiddle.add(cancel, 1, 1);
    }
    //模块详细描述
    public void infoShown(ProjectController.TextFieldTreeCellImpl textFieldTreeCell) {
        mMiddle.getChildren().clear();
        Label projectname = new Label("模块名");
        TextField nametxt = new TextField(textFieldTreeCell.getText());
        nametxt.setEditable(true);
        Label modelInfo = new Label("模块描述");
        TextArea infoText = new TextArea(textFieldTreeCell.modelInfo);
        Label modelActionlab = new Label("模块行为");
        TextArea modelAction = new TextArea();
        Button confirmbtn = new Button("确定");
        confirmbtn.setOnAction(event -> {
            String name = nametxt.getText();
            if (name.equals("")) {
                System.out.println("null");
            } else {
                mMiddle.getChildren().clear();
                textFieldTreeCell.modelInfo = infoText.getText();
            }
        });
        Button cancel = new Button("取消");
        cancel.setOnAction(event -> {
            mMiddle.getChildren().clear();
        });
        mMiddle.add(projectname, 0, 0);
        mMiddle.add(nametxt, 1, 0);
        mMiddle.add(modelInfo, 0, 1);
        mMiddle.add(infoText, 1, 1);
        mMiddle.add(modelActionlab, 0, 2);
        mMiddle.add(modelAction, 1, 2, 1, 2);
        mMiddle.add(confirmbtn, 0, 3);
        mMiddle.add(cancel, 1, 3);
    }


    /*
    功能点设计函数
     */
    //展示功能点详细信息
    public void funcShown(ProjectController.TextFieldTreeCellImpl textFieldTreeCell) {
        mMiddle.getChildren().clear();
        Label funcName = new Label("功能名称：");
        Label funcDisplay = new Label("功能描述：");
        Label inputlab = new Label("输入：");
        Label outputlab = new Label("输出：");
        Label actionlab = new Label("功能行为：");
        TextField funcNametxt = new TextField(textFieldTreeCell.getText());
        TextArea funcDisplaytxt = new TextArea(textFieldTreeCell.funcInfo);
        TextArea inputtxt = new TextArea(textFieldTreeCell.funcInput);
        TextArea outputtxt = new TextArea(textFieldTreeCell.funcOutput);
        TextArea test = new TextArea();
        mMiddle.add(test, 1, 4, 2, 1);

        Button confirmbtn = new Button("确定");
        confirmbtn.setOnAction(event -> {
            textFieldTreeCell.funcInfo = funcDisplaytxt.getText();
            textFieldTreeCell.funcInput = inputtxt.getText();
            textFieldTreeCell.funcOutput = outputtxt.getText();
            mMiddle.getChildren().clear();
        });
        Button cancelbtn = new Button("取消");
        cancelbtn.setOnAction(event -> {
            mMiddle.getChildren().clear();
        });

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
        System.out.println("middle.controller.addfun");
        mMiddle.getChildren().clear();
        Label projectname = new Label("功能点");
        TextField nametxt = new TextField("新功能");
        Button confirm = new Button("确定");
        confirm.setOnAction(event -> {
            String name = nametxt.getText();
            if (name.equals("")) {
                System.out.println("null");
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
        mMiddle.add(projectname, 0, 0);
        mMiddle.add(nametxt, 1, 0);
        mMiddle.add(confirm, 0, 1);
        mMiddle.add(cancel, 1, 1);
    }
}
