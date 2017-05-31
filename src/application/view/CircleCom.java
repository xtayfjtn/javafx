package application.view;

import application.controller.ComponentController;
import application.dao.ShapeDao;
import application.impl.ShapeDaoImpl;
import application.util.AttrStage;
import application.util.CompContextMenu;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Created by ZQ on 2017/4/15.
 */
public class CircleCom extends Circle implements BaseCom {

    public Label attr;
    protected double mradius;
    protected int shape_id = 0;
//    protected double pWidth;
//    protected double pHeight;
    protected DrawPane parentPane;

    private long currentTime = 0;
    private long lastTime = 0;

    public CircleCom(Color color, DoubleProperty x, DoubleProperty y) {
        super(x.get(), y.get(), 10);
        x.bind(centerXProperty());
        y.bind(centerYProperty());
        addEvent();
    }

    public CircleCom(DoubleProperty x, DoubleProperty y, double radius, DrawPane pane) {
        super(x.get(), y.get(), radius);
        x.bind(centerXProperty());
        y.bind(centerYProperty());
//        pWidth = pane.getWidth();
//        pHeight = pane.getHeight();
        parentPane = pane;
        attr = new Label("开始");
        attr.layoutXProperty().bind(centerXProperty().add(-radius));
        attr.layoutYProperty().bind(centerYProperty().add(radius));
        addEvent();
    }

    public CircleCom(Color color, DoubleProperty x, DoubleProperty y, double radius, DrawPane pane, Label text) {
        super(x.get(), y.get(), radius);
        attr = text;
        attr.layoutXProperty().bind(centerXProperty().add(-radius));
        attr.layoutYProperty().bind(centerYProperty().add(radius));
        x.bind(centerXProperty());
        y.bind(centerXProperty());
//        pWidth = pane.getWidth();
//        pHeight = pane.getHeight();
        parentPane = pane;
        addEvent();
    }


    public void addEvent() {
        final Delta dragDelta = new Delta();
        addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
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

            //以上的双击事件不够完善，双击总是运行单击事件。暂时先以这个为准。
            if (isDbClicked) {
                //双击打开窗口编辑;
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
            if (newX > 0 && newX < parentPane.getWidth()) {
                setCenterX(newX);
            }

            double newY = e.getY() + dragDelta.y;
            if (newY > 0 && newY < parentPane.getHeight()) {
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

    public void delete() {
//        System.out.println("wodetian");
        parentPane.getChildren().remove(this);
        parentPane.getChildren().remove(attr);
        ShapeDao shapeDao = new ShapeDaoImpl();
        shapeDao.delete(shape_id);
//        this.setVisible(false);
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

    //    public boolean hasIn(double x, double y) {
//        double len = (x - getCenterX()) * (x - getCenterX()) + (y - getCenterY()) * (y - getCenterY());
//        if (len > getRadius() * getRadius()) {
//            return false;
//        } else {
//            return true;
//        }
//    }
}
