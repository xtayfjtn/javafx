package application.controller;

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
        projectTree.setCellFactory((TreeView<String> p) -> new TextFieldTreeCellImpl());
    }



    public class TextFieldTreeCellImpl extends TreeCell<String > {

        public String modelInfo = "";
        public String funcInfo = "";
        public String funcInput = "";
        public String funcOutput = "";
        private TextField textField;
        private final ContextMenu projectMenu = new ContextMenu();//工程节点菜单
        private final ContextMenu modelMenu = new ContextMenu();//模块节点菜单
        private final ContextMenu functionMenu = new ContextMenu();//功能点节点菜单

        public TextFieldTreeCellImpl() {

            MenuItem addMenuItem = new MenuItem("添加模块");
            addMenuItem.setOnAction((ActionEvent t) -> {
                mainController.addModel(this);
//                TreeItem newEmployee = new TreeItem<>("新模块");
//                getTreeItem().getChildren().add(newEmployee);
            });
            projectMenu.getItems().add(addMenuItem);

            MenuItem modelInfoItem = new MenuItem("查看详情");
            modelInfoItem.setOnAction(event -> {
                mainController.seeInfo(this);
                System.out.println("查看详情");
            });
            MenuItem addFuncItem = new MenuItem("添加功能点");
            addFuncItem.setOnAction((ActionEvent t) -> {
                mainController.addFunc(this);
//                TreeItem newFunc = new TreeItem("新功能点");
//                System.out.println(getTreeItem().getParent().getParent());
//                getTreeItem().getChildren().add(newFunc);
            });
            modelMenu.getItems().add(addFuncItem);
            modelMenu.getItems().add(modelInfoItem);

            MenuItem funcInfoItem = new MenuItem("查看详情");
            funcInfoItem.setOnAction(event -> {
                mainController.funcedit(this);
            });
            functionMenu.getItems().add(funcInfoItem);

        }

        @Override
        public void startEdit() {
            super.startEdit();
            System.out.print("dfdsfs");
            if (!getTreeItem().isLeaf() && getTreeItem().getParent() == null) {
//                mainController.funcedit(this);
            } else if (getTreeItem().getParent() != null && getTreeItem().getParent().getParent() == null) {
                mainController.seeInfo(this);
            } else {
                mainController.funcedit(this);
            }
//            if (textField == null) {
//                createTextField();
//            }
//            setText(null);
//            setGraphic(textField);
//            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            System.out.println("dfd");
//            setText((String) getItem());
//            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                    if (!getTreeItem().isLeaf() && getTreeItem().getParent() == null) {
                        setContextMenu(projectMenu);
                    } else if (getTreeItem().getParent() != null && getTreeItem().getParent().getParent() == null) {
                        setContextMenu(modelMenu);
                    } else {
                        setContextMenu(functionMenu);
                    }
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyReleased((KeyEvent t) -> {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(textField.getText());
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}
