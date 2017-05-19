package application.controller;

import application.Util.TextFieldTreeCellImpl;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ZQ on 2017/4/6.
 */
public class ProjectController implements Initializable {
    private MainController mainController;

    @FXML
    TreeView<String> projectTree;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void init(MainController controller) {
        mainController = controller;
    }

    //画出工程的树
    public void newProject(String name) {
        TreeItem<String> mRoot = new TreeItem<>(name);
        for (int i = 0; i < 4; i++) {
            TreeItem item = new TreeItem("模块" + i);
            if (i == 0) {
                for (int j = 0; j < 3; j++) {
                    TreeItem t = new TreeItem("功能点" + j);
                    item.getChildren().add(t);
                }
            }

            mRoot.getChildren().add(item);
        }
        projectTree.setRoot(mRoot);
        projectTree.setEditable(true);
        projectTree.setCellFactory((TreeView<String> p) -> new TextFieldTreeCellImpl(mainController));
    }


}
