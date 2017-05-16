package application.Util;

import application.controller.ComponentattrController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by ZQ on 2017/5/9.
 */
public class AttrStage extends Stage {
//    private Label label;
//    private static TextField text;
//    private Button confirmbtn;
//    private Button cancelbtn;
    static ComponentattrController componentattrController;
    private static CircleCom circlecom;
    private static EllipseCom ellipsecom;
    private static PolygonCom polygoncom;
    private AttrStage(){
        super();
        //以下注释为未绑定fxml时代码。
//        label = new Label("属性");
//        text = new TextField();
//        confirmbtn = new Button("确定");
//        confirmbtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                if (circlecom != null) {
//                    circlecom.attr.setText(text.getText());
//                } else if (ellipsecom != null) {
//                    ellipsecom.attr.setText(text.getText());
//                } else if (polygoncom != null) {
//                    polygoncom.attr.setText(text.getText());
//                }
//                close();
//            }
//        });
//        cancelbtn = new Button("取消");
//        GridPane layout = new GridPane();
//        layout.add(label, 0, 0);
//        layout.add(text, 1, 0);
//        layout.add(confirmbtn, 0, 1);
//        layout.add(cancelbtn, 1, 1);
//        Scene scene = new Scene(layout);
//        setScene(scene);

        Parent target = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/componentattr.fxml"));
        try {
            target = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        componentattrController = fxmlLoader.getController();
        componentattrController.confirmbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (circlecom != null) {
                    circlecom.attr.setText(componentattrController.attrtxt1.getText());
                } else if (ellipsecom != null) {
                    ellipsecom.attr.setText(componentattrController.attrtxt1.getText());
                } else if (polygoncom != null) {
                    polygoncom.attr.setText(componentattrController.attrtxt1.getText());
                }
                close();
            }
        });
        componentattrController.cancelbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                close();
            }
        });
        Scene scene = new Scene(target); //创建场景；

        setScene(scene); //将场景载入舞台；

    }

    private static final AttrStage instance = new AttrStage();

    public static AttrStage getInstance(CircleCom circleCom) {
        circlecom = circleCom;
        ellipsecom = null;
        polygoncom = null;
        componentattrController.attrtxt1.setText(String.valueOf(circleCom.getCenterY()));
//        text.setText(String.valueOf(circleCom.getCenterY()));
        return instance;
    }

    public static AttrStage getInstance(EllipseCom ellipseCom) {
        ellipsecom = ellipseCom;
        circlecom = null;
        polygoncom = null;
        componentattrController.attrtxt1.setText(String.valueOf(ellipseCom.getCenterX()));
        return instance;
    }

    public static AttrStage getInstance(PolygonCom polygonCom) {
        ellipsecom = null;
        circlecom = null;
        polygoncom = polygonCom;
        componentattrController.attrtxt1.setText(String.valueOf(polygoncom.getCenterX()));
        return instance;
    }
}
