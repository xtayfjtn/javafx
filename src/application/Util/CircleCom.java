package application.Util;

import application.controller.ComponentController;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import org.jcp.xml.dsig.internal.dom.DOMUtils;

import javax.print.DocFlavor;
import java.net.URL;

/**
 * Created by ZQ on 2017/4/15.
 */
public class CircleCom extends Circle {

    protected Label attr;
    protected double mradius;
    protected double pWidth;
    protected double pHeight;
    protected DrawPane parentPane;

    private long currentTime = 0;
    private long lastTime = 0;

    public CircleCom(Color color, DoubleProperty x, DoubleProperty y) {
        super(x.get(), y.get(), 10);
        setFill(color.deriveColor(1, 1, 1, 0.5));
        setStroke(color);
        setStrokeWidth(2);
        setStrokeType(StrokeType.OUTSIDE);

        x.bind(centerXProperty());
        y.bind(centerYProperty());
        addEvent();
    }

    public CircleCom(Color color, DoubleProperty x, DoubleProperty y, double radius, DrawPane pane) {
        super(x.get(), y.get(), radius);
        x.bind(centerXProperty());
        y.bind(centerXProperty());
        pWidth = pane.getWidth();
        pHeight = pane.getHeight();
        parentPane = pane;
        addEvent();
    }

    public CircleCom(Color color, DoubleProperty x, DoubleProperty y, double radius, DrawPane pane, Label text) {
        super(x.get(), y.get(), radius);
        attr = text;
        attr.layoutXProperty().bind(centerXProperty().add(-radius));
        attr.layoutYProperty().bind(centerYProperty().add(radius));
        x.bind(centerXProperty());
        y.bind(centerXProperty());
        pWidth = pane.getWidth();
        pHeight = pane.getHeight();
        parentPane = pane;
        addEvent();
    }


    public void addEvent() {
        final Delta dragDelta = new Delta();
        addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            long diff = 0;
            currentTime = System.currentTimeMillis();
            boolean isDbClicked = false;
            if (lastTime != 0 && currentTime != 0) {
                diff = currentTime - lastTime;
                if (diff <= 215) {
                    isDbClicked = true;
                } else {
                    isDbClicked = false;
                }
            }
            lastTime = currentTime;

            //以上的双击事件不够完善，双击总是运行单击事件。暂时先以这个为准。
            if (isDbClicked) {
                Stage stage = AttrStage.getInstance(this);
                stage.show();
                System.out.println("db is true");
            } else {
                dragDelta.x = getCenterX() - e.getX();
                dragDelta.y = getCenterY() - e.getY();
                if (ComponentController.type == 6) {
                    if (!parentPane.isLine) {
                        parentPane.startX = new SimpleDoubleProperty(getCenterX());
                        parentPane.startY = new SimpleDoubleProperty(getCenterY());
                        parentPane.isLine = true;
                    } else{
                        parentPane.endX = new SimpleDoubleProperty(getCenterX());
                        parentPane.endY = new SimpleDoubleProperty(getCenterY());
//                    parentPane.drawLine();
                        parentPane.isLine = false;
                    }
                }
            }
        });

        addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
//            e.consume();
            if (parentPane.type == 6) {
                return;
            }
            double newX = e.getX() + dragDelta.x;
            if (newX > 0 && newX < pWidth) {
                setCenterX(newX);
            }

            double newY = e.getY() + dragDelta.y;
            if (newY > 0 && newY < pHeight) {
                setCenterY(newY);
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!event.isPrimaryButtonDown()) {
                    getScene().setCursor(Cursor.HAND);
                }
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!event.isPrimaryButtonDown()) {
                    getScene().setCursor(Cursor.DEFAULT);
                }
            }
        });
    }

    private void delete() {
        System.out.println("wodetian");
        parentPane.getChildren().remove(this);
        this.setVisible(false);
    }


    private class Delta {
        public double x;
        public double y;
    }

//    public boolean hasIn(double x, double y) {
//        double len = (x - getCenterX()) * (x - getCenterX()) + (y - getCenterY()) * (y - getCenterY());
//        if (len > getRadius() * getRadius()) {
//            return false;
//        } else {
//            return true;
//        }
//    }
}
