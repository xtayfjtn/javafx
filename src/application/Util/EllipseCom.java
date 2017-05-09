package application.Util;

import application.controller.ComponentController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;

/**
 * Created by ZQ on 2017/4/14.
 */
public class EllipseCom extends Ellipse {
    private double mradiusX;
    private double mradiusY;
    private double pWidth;
    private double pHeight;
    private DrawPane parentPane;
    public EllipseCom() {
        super();
    }

    public EllipseCom(double radiusX, double radiusY, DrawPane pane) {
        super(radiusX, radiusY);
        pWidth = pane.getWidth();
        pHeight = pane.getHeight();
        parentPane = pane;
        mradiusX = radiusX;
        mradiusY = radiusY;
        addEvent();
    }

    public EllipseCom(double centerX, double centerY, double radiusX, double radiusY, DrawPane pane) {
        super(centerX, centerY, radiusX, radiusY);
        pWidth = pane.getWidth();
        pHeight = pane.getHeight();
        mradiusX = radiusX;
        mradiusY = radiusY;
        parentPane = pane;
        addEvent();
    }

    private void addEvent() {
        final Delta dragDelta = new Delta();
        addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
//            e.consume();
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
        });

        addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
//            e.consume();
            if (parentPane.type == 6) {
                return;
            }
            double mx = e.getX();
            double my = e.getY();
            if (mx < mradiusX) {
                mx = mradiusX;
            }
            if (mx > pWidth - mradiusX) {
                mx = pWidth - mradiusX;
            }

            if (my < mradiusY) {
                my = mradiusY;
            }

            if (my > pHeight - mradiusY) {
                my = pHeight - mradiusY;
            }

            setCenterX(mx);
            setCenterY(my);
        });
    }

    private class Delta {
        public double x;
        public double y;
    }
}
