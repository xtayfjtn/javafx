package application.Util;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import org.jcp.xml.dsig.internal.dom.DOMUtils;

/**
 * Created by ZQ on 2017/4/15.
 */
public class CircleCom extends Circle {

    protected double mradius;
    protected double pWidth;
    protected double pHeight;
    protected DrawPane parentPane;

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
//    public CircleCom(double radius, DrawPane pane) {
//        super(radius);
//        mradius = radius;
//        pWidth = pane.getWidth();
//        pHeight = pane.getHeight();
//        parentPane = pane;
//        addEvent();
//    }
//
//    public CircleCom(double radius, Paint fill, DrawPane pane) {
//        super(radius, fill);
//        mradius = radius;
//        pWidth = pane.getWidth();
//        pHeight = pane.getHeight();
//        parentPane = pane;
//        addEvent();
//    }
//
//    public CircleCom(DrawPane pane) {
//        super();
//        pWidth = pane.getWidth();
//        pHeight = pane.getHeight();
//        parentPane = pane;
//        addEvent();
//    }
//
//    public CircleCom(double centerX, double centerY, double radius, DrawPane pane) {
//        super(centerX, centerY, radius);
//        mradius = radius;
//        pWidth = pane.getWidth();
//        pHeight = pane.getHeight();
//        parentPane = pane;
//        addEvent();
//    }
//
//    public CircleCom(double centerX, double centerY, double radius, Paint fill, DrawPane pane) {
//        super(centerX, centerY, radius, fill);
//        mradius = radius;
//        pWidth = pane.getWidth();
//        pHeight = pane.getHeight();
//        addEvent();
//    }
//
//    public CircleCom(double centerX, double centerY, double radius, Paint fill) {
//        super(centerX, centerY, radius, fill);
//        mradius = radius;
//        addEvent();
//    }

    public void addEvent() {
        final Delta dragDelta = new Delta();
        addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            e.consume();
            dragDelta.x = getCenterX() - e.getX();
            dragDelta.y = getCenterY() - e.getY();
//            getScene().setCursor(Cursor.HAND);
        });

        addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
            e.consume();
            double newX = e.getX() + dragDelta.x;
            if (newX > 0 && newX < pWidth) {
                setCenterX(newX);
            }

            double newY = e.getY() + dragDelta.y;
            if (newY > 0 && newY < pHeight) {
                setCenterY(newY);
            }


//            double mx = e.getX();
//            double my = e.getY();
//
//            if (mx < mradius) {
//                mx = mradius;
//            }
//
//            if (mx > pWidth - mradius) {
//                mx = pWidth - mradius;
//            }
//
//            if (my < mradius) {
//                my = mradius;
//            }
//            if (my > pHeight - mradius) {
//                my = pHeight - mradius;
//            }
//            setCenterX(mx);
//            setCenterY(my);
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
        this.setVisible(false);
    }


    private class Delta {
        public double x;
        public double y;
    }
}
