package application.Util;

import application.controller.MainController;
import application.model.Clause;
import application.model.Project;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;

/**
 * Created by ZQ on 2017/5/19.
 */
public class TextFieldTreeCellImpl extends TreeCell<Object> {
    public String modelInfo = "";
    public String funcInfo = "";
    public String funcInput = "";
    public String funcOutput = "";
    private TextField textField;
    private final ContextMenu projectMenu = new ContextMenu();//工程节点菜单
    private final ContextMenu modelMenu = new ContextMenu();//模块节点菜单
    private final ContextMenu functionMenu = new ContextMenu();//功能点节点菜单

    MainController mainController;

    public TextFieldTreeCellImpl(MainController mainController) {

        this.mainController = mainController;
        MenuItem addMenuItem = new MenuItem("添加模块");
        addMenuItem.setOnAction((ActionEvent t) -> {
            mainController.initModel(this);
//                TreeItem newEmployee = new TreeItem<>("新模块");
//                getTreeItem().getChildren().add(newEmployee);
        });
        projectMenu.getItems().add(addMenuItem);

        MenuItem modelInfoItem = new MenuItem("查看详情");
        modelInfoItem.setOnAction(event -> {
            mainController.seeInfo(this);
        });
        MenuItem addFuncItem = new MenuItem("添加功能点");
        addFuncItem.setOnAction((ActionEvent t) -> {
            mainController.initFunc(this);
        });
        modelMenu.getItems().add(addMenuItem);
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

        if (getItem() instanceof Project) {

        }
        if (getItem() instanceof Clause) {
            Clause clause = (Clause)getItem();
            if (clause.getcType().equals("模块"))
            {
                mainController.seeInfo(this);
            } else {
                mainController.funcedit(this);
            }
        }
//        if (!getTreeItem().isLeaf() && getTreeItem().getParent() == null) {
////                mainController.funcedit(this);
//        } else if (getTreeItem().getParent() != null && getTreeItem().getParent().getParent() == null) {
//            mainController.seeInfo(this);
//        } else {
//            mainController.funcedit(this);
//        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
    }

    @Override
    public void updateItem(Object item, boolean empty) {
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
                if (getItem() instanceof Project ) {
                    setContextMenu(projectMenu);
                }
                if (getItem() instanceof Clause) {
                    Clause clause = (Clause)getItem();
                    if (clause.getcType().equals("模块"))
                    {
                        setContextMenu(modelMenu);
                    } else {
                        setContextMenu(functionMenu);
                    }
                }
//                if (!getTreeItem().isLeaf() && getTreeItem().getParent() == null) {
//                    setContextMenu(projectMenu);
//                } else if (getTreeItem().getParent() != null && getTreeItem().getParent().getParent() == null) {
//                    setContextMenu(modelMenu);
//                } else {
//                    setContextMenu(functionMenu);
//                }
            }
        }
    }

//        private void createTextField() {
//            textField = new TextField(getString());
//            textField.setOnKeyReleased((KeyEvent t) -> {
//                if (t.getCode() == KeyCode.ENTER) {
//                    commitEdit(textField.getText());
//                } else if (t.getCode() == KeyCode.ESCAPE) {
//                    cancelEdit();
//                }
//            });
//        }

    private String getString() {
        if (getItem() == null) {
            return "";
        }
        else {
            if (getItem() instanceof Clause) {
                return ((Clause)getItem()).getClauseName();
            } else if (getItem() instanceof Project){
                return ((Project)getItem()).getProjectName();
            } else {
                return getItem().toString();
            }
        }
    }
}
