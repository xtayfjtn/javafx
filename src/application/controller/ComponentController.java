package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;

/**
 * Created by ZQ on 2017/4/9.
 */
public class ComponentController {
    private MainController mainController;
    public static int type = 0;
    @FXML TabPane componentPane;
    @FXML Ellipse ellipsecom;
    @FXML Pane startpane;
    @FXML Pane ellipsepane;
    @FXML Pane branchpane;
    @FXML Pane synchpane;
    @FXML Pane endpane;
    @FXML Pane arrowpane;
    public void init(MainController controller) {
        mainController = controller;
        componentPane.setVisible(false);
    }

    public void showPane() {
        componentPane.setVisible(true);
    }

    public void hidePane() {
        componentPane.setVisible(false);
    }

    public void onStartClicked(MouseEvent mouseEvent) {
        startpane.getStyleClass().add("choosen");
        ellipsepane.getStyleClass().removeAll("choosen");
        branchpane.getStyleClass().removeAll("choosen");
        synchpane.getStyleClass().removeAll("choosen");
        endpane.getStyleClass().removeAll("choosen");
        arrowpane.getStyleClass().removeAll("choosen");
        type = 1;
    }

    public void onEllipseClicked(MouseEvent mouseEvent) {
        startpane.getStyleClass().removeAll("choosen");
        ellipsepane.getStyleClass().add("choosen");
        branchpane.getStyleClass().removeAll("choosen");
        synchpane.getStyleClass().removeAll("choosen");
        endpane.getStyleClass().removeAll("choosen");
        arrowpane.getStyleClass().removeAll("choosen");
        type = 2;
    }

    public void onBranchClicked(MouseEvent mouseEvent) {
        startpane.getStyleClass().removeAll("choosen");
        ellipsepane.getStyleClass().removeAll("choosen");
        branchpane.getStyleClass().add("choosen");
        synchpane.getStyleClass().removeAll("choosen");
        endpane.getStyleClass().removeAll("choosen");
        arrowpane.getStyleClass().removeAll("choosen");
        type = 3;
    }

    public void onSynchClicked(MouseEvent mouseEvent) {
        startpane.getStyleClass().removeAll("choosen");
        ellipsepane.getStyleClass().removeAll("choosen");
        branchpane.getStyleClass().removeAll("choosen");
        synchpane.getStyleClass().add("choosen");
        endpane.getStyleClass().removeAll("choosen");
        arrowpane.getStyleClass().removeAll("choosen");
        type = 4;
    }



    public void onEndClicked(MouseEvent mouseEvent) {
        startpane.getStyleClass().removeAll("choosen");
        ellipsepane.getStyleClass().removeAll("choosen");
        branchpane.getStyleClass().removeAll("choosen");
        synchpane.getStyleClass().removeAll("choosen");
        endpane.getStyleClass().add("choosen");
        arrowpane.getStyleClass().removeAll("choosen");
        type = 5;
    }

    public void onArrowClicked(MouseEvent mouseEvent) {
        startpane.getStyleClass().removeAll("choosen");
        ellipsepane.getStyleClass().removeAll("choosen");
        branchpane.getStyleClass().removeAll("choosen");
        synchpane.getStyleClass().removeAll("choosen");
        endpane.getStyleClass().removeAll("choosen");
        arrowpane.getStyleClass().add("choosen");
        type = 6;
    }

}
