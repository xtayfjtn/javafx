package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ZQ on 2017/3/28.
 */
public class MenuController implements Initializable {
    private MainController main;
    public int flag = 0;
    public void onNewProjectClick(ActionEvent event) {
        System.out.println("Create new Project!");
        main.initProject();
    }

    public void onOpenProjectClick(ActionEvent event) {
        System.out.println("Open a project!");
    }

    public void onSaveProjectClick(ActionEvent event) {
        System.out.println("Save project");

    }

    public void onNewModelClick(ActionEvent event) {
        System.out.println("Create new Model");
    }

    public void onNewFuncClick(ActionEvent event) {
        System.out.println("Create new function!");
    }

    public void onCutClick(ActionEvent event) {
        System.out.println("Cut the word!");
    }

    public void onCopyClick(ActionEvent event) {
        System.out.println("Copy the word!");
    }

    public void onPasteClick(ActionEvent event) {
        System.out.println("Paste the word!");
    }

    public void onUpdateClick(ActionEvent event) {
        System.out.println("Update cliced!");
    }

    public void onAboutClick(ActionEvent event) {
        System.out.println("About Clicked!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        ControllerBridge.getInstance().registerController2(controller2Controller);
//        ControllerBridge.getInstance().registerController3(controller3Controller);
    }

    public void init(MainController controller) {
        main = controller;
    }
}
