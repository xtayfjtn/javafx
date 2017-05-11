package application.Util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by ZQ on 2017/5/9.
 */
public class AttrStage extends Stage {
    private Label label;
    private static TextField text;
    private Button confirmbtn;
    private Button cancelbtn;
    private static CircleCom circlecom;
    private static EllipseCom ellipsecom;
    private static PolygonCom polygoncom;
    private AttrStage() {
        super();
        label = new Label("属性");
        text = new TextField();
        confirmbtn = new Button("确定");
        confirmbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (circlecom != null) {
                    circlecom.attr.setText(text.getText());
                } else if (ellipsecom != null) {
                    ellipsecom.attr.setText(text.getText());
                } else if (polygoncom != null) {
                    polygoncom.attr.setText(text.getText());
                }
                close();
            }
        });
        cancelbtn = new Button("取消");
        GridPane layout = new GridPane();
        layout.add(label, 0, 0);
        layout.add(text, 1, 0);
        layout.add(confirmbtn, 0, 1);
        layout.add(cancelbtn, 1, 1);
        Scene scene = new Scene(layout);
        setScene(scene);
    }

    private static final AttrStage instance = new AttrStage();

    public static AttrStage getInstance(CircleCom circleCom) {
        circlecom = circleCom;
        ellipsecom = null;
        polygoncom = null;
        text.setText(String.valueOf(circleCom.getCenterX()));
        return instance;
    }

    public static AttrStage getInstance(EllipseCom ellipseCom) {
        ellipsecom = ellipseCom;
        circlecom = null;
        polygoncom = null;
        text.setText(String.valueOf(ellipseCom.getCenterX()));
        return instance;
    }

    public static AttrStage getInstance(PolygonCom polygonCom) {
        ellipsecom = null;
        circlecom = null;
        polygoncom = polygonCom;
        text.setText(String.valueOf(polygoncom.getCenterX()));
        return instance;
    }
}
