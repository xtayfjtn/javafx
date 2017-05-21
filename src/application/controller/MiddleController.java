package application.controller;

import application.Util.DrawPane;
import application.Util.Loger;
import application.Util.TextFieldTreeCellImpl;
import application.dao.ParameterDao;
import application.impl.ParameterDaoImpl;
import application.model.Clause;
import application.model.Parameter;
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
    public void initProject() {
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
                mainController.addProject(name);
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
    public void initModel(TextFieldTreeCellImpl textFieldTreeCell) {
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
                mainController.addModel(name, textFieldTreeCell);
//                Clause clause = new Clause();
//                clause.setClauseName(name);
//                clause.setcType("模块");
//                TreeItem newModel = new TreeItem<>(clause);
//                textFieldTreeCell.getTreeItem().getChildren().addAll(newModel);
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
    public void modelInfo(TextFieldTreeCellImpl textFieldTreeCell) {
        mMiddle.getChildren().clear();
        mainController.showComponent();
        Label projectname = new Label("模块名：");
        Label modelInfo = new Label("模块描述：");
        Label modelActionlab = new Label("模块行为：");

        TextField nametxt = new TextField(textFieldTreeCell.getText());
        nametxt.setEditable(true);
        TextArea infoText = new TextArea(((Clause)textFieldTreeCell.getItem()).getDescription());
        DrawPane modelAction = new DrawPane();
        modelAction.getStyleClass().add("drawpane");
        Button confirmbtn = new Button("确定");
        confirmbtn.setOnAction(event -> {
            mMiddle.getChildren().clear();
            mainController.saveModel(textFieldTreeCell, nametxt.getText(), infoText.getText(), modelAction);
//            textFieldTreeCell.modelInfo = infoText.getText();
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
    public void funcInfo(TextFieldTreeCellImpl textFieldTreeCell) {
        mMiddle.getChildren().clear();
        mainController.showComponent();
        Label funcName = new Label("功能名称：");
        Label funcDisplay = new Label("功能描述：");
        Label inputlab = new Label("输入：");
        Label outputlab = new Label("输出：");
        Label actionlab = new Label("功能行为：");

        String cFuncName = "";
        String cFuncDesc = "";
        String cFuncInput = "";
        String cFuncOutput = "";
        if (textFieldTreeCell.getItem() instanceof Clause) {
            Clause clause = (Clause) textFieldTreeCell.getItem();
            cFuncName = clause.getClauseName();
            cFuncDesc = clause.getDescription();
            ParameterDao parameterDao = new ParameterDaoImpl();
            Parameter inputParameter = parameterDao.getInputParameter(clause.getClause_id());
            Parameter outputParameter = parameterDao.getOutputParameter(clause.getClause_id());
            if (inputParameter == null) {
                inputParameter = new Parameter();
                inputParameter.setClause_id(clause.getClause_id());
                inputParameter.setpType("input");
                outputParameter = new Parameter();
                outputParameter.setClause_id(clause.getClause_id());
                outputParameter.setpType("output");
                parameterDao.insert(inputParameter);
                parameterDao.insert(outputParameter);
            }
            cFuncInput = inputParameter.getParameterName();
            cFuncOutput = outputParameter.getParameterName();
        }
        TextField funcNametxt = new TextField(cFuncName);
        TextArea funcDisplaytxt = new TextArea(cFuncDesc);
        TextArea inputtxt = new TextArea(cFuncInput);
        TextArea outputtxt = new TextArea(cFuncOutput);
        DrawPane funcAction = new DrawPane();
        funcAction.getStyleClass().add("drawpane");
//        TextArea test = new TextArea();
        mMiddle.add(funcAction, 1, 4, 2, 1);

        Button confirmbtn = new Button("确定");
        confirmbtn.setOnAction(event -> {
            mainController.saveFunc(textFieldTreeCell,funcNametxt.getText(), funcDisplaytxt.getText(), inputtxt.getText(), outputtxt.getText(), funcAction);
//            textFieldTreeCell.funcInfo = funcDisplaytxt.getText();
//            textFieldTreeCell.funcInput = inputtxt.getText();
//            textFieldTreeCell.funcOutput = outputtxt.getText();
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
    public void initFunc(TextFieldTreeCellImpl textFieldTreeCell) {
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
                mainController.addFunc(name, textFieldTreeCell);
//                Clause clause = new Clause();
//                clause.setClauseName(name);
//                clause.setcType("功能点");
//                TreeItem newFunc = new TreeItem<>(clause);
//                textFieldTreeCell.getTreeItem().getChildren().addAll(newFunc);
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
