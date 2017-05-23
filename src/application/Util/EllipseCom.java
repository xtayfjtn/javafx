package application.Util;

import application.controller.ComponentController;
import application.dao.ShapeDao;
import application.impl.ShapeDaoImpl;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

/**
 * Created by ZQ on 2017/4/14.
 */
public class EllipseCom extends Ellipse implements BaseCom {
    private double mradiusX;
    private double mradiusY;
    private int shape_id;
//    private double pWidth;
//    private double pHeight;
    private DrawPane parentPane;

    //双击事件所有变量。
    private long currentTime = 0;
    private long lastTime = 0;


    public Label attr;
    public EllipseCom() {
        super();
    }

    public EllipseCom(double radiusX, double radiusY, DrawPane pane) {
        super(radiusX, radiusY);
//        pWidth = pane.getWidth();
//        pHeight = pane.getHeight();
        parentPane = pane;
        mradiusX = radiusX;
        mradiusY = radiusY;

        addEvent();
    }

    public EllipseCom(double centerX, double centerY, double radiusX, double radiusY, DrawPane pane) {
        super(centerX, centerY, radiusX, radiusY);
        setFill(Color.BISQUE);
        setStroke(Color.BLACK);
//        pWidth = pane.getWidth();
//        pHeight = pane.getHeight();
        mradiusX = radiusX;
        mradiusY = radiusY;
        parentPane = pane;
        attr = new Label("开始");
        attr.layoutXProperty().bind(centerXProperty().add(-20));
        attr.layoutYProperty().bind(centerYProperty());
//        pane.getChildren().add(attr);
        addEvent();
    }
//    public EllipseCom(double centerX, double centerY, double radiusX, double radiusY, DrawPane pane, Label text) {
//        super(centerX, centerY, radiusX, radiusY);
//        attr = text;
//        setFill(Color.BISQUE);
//        setStroke(Color.BLACK);
//        text.layoutXProperty().bind(centerXProperty().add(-20));
//        text.layoutYProperty().bind(centerYProperty());
////        pWidth = pane.getWidth();
////        pHeight = pane.getHeight();
//        mradiusX = radiusX;
//        mradiusY = radiusY;
//        parentPane = pane;
//        addEvent();
//    }

    private void addEvent() {
        final Delta dragDelta = new Delta();
        addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
//            e.consume();
            if (e.isSecondaryButtonDown()) {
                ContextMenu contextMenu = new CompContextMenu(this);
                contextMenu.show(parentPane, e.getScreenX(), e.getScreenY());
                return;
            }
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
            //同理以上双击不完善。

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
            double mx = e.getX();
            double my = e.getY();
            if (mx < mradiusX) {
                mx = mradiusX;
            }
            if (mx > parentPane.getWidth() - mradiusX) {
                mx = parentPane.getWidth() - mradiusX;
            }

            if (my < mradiusY) {
                my = mradiusY;
            }

            if (my > parentPane.getHeight() - mradiusY) {
                my = parentPane.getHeight() - mradiusY;
            }

            setCenterX(mx);
            setCenterY(my);
        });
    }

    @Override
    public void delete() {
        parentPane.getChildren().remove(this);
        parentPane.getChildren().remove(attr);
        ShapeDao shapeDao = new ShapeDaoImpl();
        shapeDao.delete(shape_id);
    }

    private class Delta {
        public double x;
        public double y;
    }

    public int getShape_id() {
        return shape_id;
    }

    public void setShape_id(int shape_id) {
        this.shape_id = shape_id;
    }
}
