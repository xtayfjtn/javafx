package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ZQ on 2017/5/9.
 */
public class ComponentattrController implements Initializable {
    @FXML public TextField attrtxt1;
    @FXML public Button confirmbtn;
    @FXML public Button cancelbtn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onConfirmClicked(ActionEvent actionEvent) {
        attrtxt1.setText("wodetian");
    }
}
