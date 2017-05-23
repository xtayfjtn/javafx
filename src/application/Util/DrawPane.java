package application.Util;

import application.controller.ComponentController;
import application.dao.ShapeDao;
import application.impl.ShapeDaoImpl;
import application.model.Shape;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


/**
 * Created by ZQ on 2017/4/14.
 */
public class DrawPane extends Pane {

    public static final int START_TYPE = 1;
    public static final int ELLIPSE_TYPE = 2;
    public static final int BRANCH_TYPE = 3;
    public static final int SYNCH_TYPE = 4;
    public static final int END_TYPE = 5;
    private int clause_id = 0;
    private final double ellipseX = 40;
    private final double ellipseY = 20;
    private final double circleR = 15;
    private final double synchR = 40;

    public DoubleProperty startX;
    public DoubleProperty startY;
    public DoubleProperty endX;
    public DoubleProperty endY;
    public LineCom line;
    public boolean startEmpty = true;
    public boolean endEmpty = true;
    public boolean isLine = false;

    public int type = 0;

    public DrawPane() {
        super();
        addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            double eventX = e.getX();
            double eventY = e.getY();

            DoubleProperty circleX = new SimpleDoubleProperty(eventX);
            DoubleProperty circleY = new SimpleDoubleProperty(eventY);

            double width = getWidth();
            double height = getHeight();
            type = ComponentController.type;
            switch (type) {
                case 1:
                    if (eventX > circleR && eventX < width - circleR && eventY > circleR && eventY < height - circleR) {
                        drawStart(circleX, circleY, circleR);
                    }
                    break;
                case 2:
                    if (eventX > ellipseX && eventX < width - ellipseX && eventY > ellipseY && eventY < height - ellipseY) {
                        drawEllipse(eventX, eventY, ellipseX, ellipseY);
                    }
                    break;
                case 3:
                    if (eventX > ellipseX && eventX < width - ellipseX && eventY > ellipseY && eventY < height - ellipseY) {
                        drawBranch(eventX, eventY);
                    }
                    break;
                case 4:
                    if (eventX > synchR && eventX < width - synchR && eventY > 0 && eventY < height) {
                        drawSynch(eventX, eventY);
                    }
                    break;
                case 5:
                    if (eventX > circleR + 10 && eventX < width - circleR - 10 && eventY > circleR && eventY < height - circleR - 10) {
                        drawEnd(circleX, circleY, circleR + 5);
                    }
                    break;
                case 6:
                    drawLineStart(eventX, eventY);
                    break;
            }

        });

        addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
            if (type == 6) {
                endX = new SimpleDoubleProperty(e.getX());
                endY = new SimpleDoubleProperty(e.getY());
                line.setEndX(endX.get());
                line.setEndY(endY.get());
            }
        });

        addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent e) -> {
            if (type == 6) {
                if (line != null && !isInComponent(line.getStartX(), line.getStartY(), line, true) || !isInComponent(line.getEndX(), line.getEndY(), line, false)) {
                    getChildren().remove(line);
                }
                else {

                }
            }
        });
    }

    public void drawLineStart(double x, double y) {
        startX = new SimpleDoubleProperty(x);
        startY = new SimpleDoubleProperty(y);
        line = new LineCom(startX, startY, startX, startY, this);
        getChildren().add(line);
    }

    //画出开始控件；
    private void drawStart(DoubleProperty x, DoubleProperty y, double radius) {
        if (isInComponent(x.get(), y.get())) {
            return;
        }
        CircleCom start = new CircleCom(x, y, radius, this);
        getChildren().add(start);
        getChildren().add(start.attr);
        ShapeDao shapeDao = new ShapeDaoImpl();
        Shape shape = new Shape();
        shape.setClause_id(getClause_id());
        shape.setLayoutX(x.get());
        shape.setLayoutY(y.get());
        shape.setAttr(start.attr.getText());
        shape.setsType(START_TYPE);
        shapeDao.insert(shape);
        start.setShape_id(shapeDao.getMaxId());
    }

    //画出顺序控件；
    private void drawEllipse(double x, double y, double radiusX, double radiusY) {
        if (isInComponent(x, y)) {
            return;
        }
        EllipseCom a = new EllipseCom(x, y, radiusX, radiusY, this);
        getChildren().add(a);
        getChildren().add(a.attr);
        ShapeDao shapeDao = new ShapeDaoImpl();
        Shape shape = new Shape();
        shape.setClause_id(getClause_id());
        shape.setLayoutX(x);
        shape.setLayoutY(y);
        shape.setAttr(a.attr.getText());
        shape.setsType(ELLIPSE_TYPE);
        shapeDao.insert(shape);
        a.setShape_id(shapeDao.getMaxId());

    }

    //画出分支；
    private void drawBranch(double eventX, double eventY) {
        if (isInComponent(eventX, eventY)) {
            return;
        }
        PolygonCom pc = new PolygonCom(eventX, eventY, this);
        getChildren().add(pc);
        getChildren().add(pc.attr);

        ShapeDao shapeDao = new ShapeDaoImpl();
        Shape shape = new Shape();
        shape.setClause_id(getClause_id());
        shape.setLayoutX(eventX);
        shape.setLayoutY(eventY);
        shape.setAttr(pc.attr.getText());
        shape.setsType(BRANCH_TYPE);
        shapeDao.insert(shape);
        pc.setShape_id(shapeDao.getMaxId());
    }

    //画同步的控件；
    private void drawSynch(double x, double y) {
        if (isInComponent(x, y)) {
            return;
        }
        SynchCom line = new SynchCom(x - synchR - 1, y, x + synchR - 1, y, this);
        getChildren().add(line);

        ShapeDao shapeDao = new ShapeDaoImpl();
        Shape shape = new Shape();
        shape.setClause_id(getClause_id());
        shape.setLayoutX(x);
        shape.setLayoutY(y);
        shape.setsType(SYNCH_TYPE);
        shapeDao.insert(shape);
        line.setShape_id(shapeDao.getMaxId());
    }

    //画结束节点。
    private void drawEnd(DoubleProperty x, DoubleProperty y, double radius) {
        if (isInComponent(x.get(), y.get())) {
            return;
        }
        EndCircleCom ecc = new EndCircleCom(x, y, radius, this);
        getChildren().add(ecc);

        ShapeDao shapeDao = new ShapeDaoImpl();
        Shape shape = new Shape();
        shape.setClause_id(getClause_id());
        shape.setLayoutX(x.get());
        shape.setLayoutY(y.get());
        shape.setAttr(ecc.attr.getText());
        shape.setsType(END_TYPE);
        shapeDao.insert(shape);
        ecc.setShape_id(shapeDao.getMaxId());
    }

    //判断点(x, y)是否在当前画面的组件上
    public boolean isInComponent(double x, double y) {
        System.out.println(getChildren().size());
        int len = getChildren().size();
        for (int i = 0; i < len; i++) {
//            System.out.println(getChildren().get(i));
            if (getChildren().get(i).contains(x, y)) {
                System.out.println("true");
                System.out.println(getChildren().get(i));
                return true;
            }
        }
        System.out.println("false");
        return false;
    }

    //判断当前线的端点是否在组件上。
    public boolean isInComponent(double x, double y, LineCom line, boolean isStart) {
//        以下是针对普通控件，无Center属性。
        int len = getChildren().size();
        for (int i = 0; i < len; i++) {
            if (getChildren().get(i).contains(x, y) && !getChildren().get(i).equals(line)) {
                if (isStart) {
                    Node start = getChildren().get(i);
                    Center startCenter = new Center(start);
                    line.bindStartProperties(startCenter.centerXProperty(), startCenter.centerYProperty(), start);
                }
                else {
                    Node end = getChildren().get(i);
                    Center endCenter = new Center(end);
                    line.bindEndProperties(endCenter.centerXProperty(), endCenter.centerYProperty(), end);
                }
                return true;
            }
        }
        return false;
//        以下针对圆形控件，有Center属性
//        int len = getChildren().size();
//        for (int i = 0; i < len; i++) {
//            if (getChildren().get(i).contains(x, y) && !getChildren().get(i).equals(line)) {
//                if (isStart) {
//                    line.bindStartProperties(((CircleCom)getChildren().get(i)).centerXProperty(), ((CircleCom)getChildren().get(i)).centerYProperty());
//                }
//                else {
//                    line.bindEndProperties(((CircleCom)getChildren().get(i)).centerXProperty(), ((CircleCom)getChildren().get(i)).centerYProperty());
//                }
//                return true;
//            }
//        }
//        return false;
    }

    public int getClause_id() {
        return clause_id;
    }

    public void setClause_id(int clause_id) {
        this.clause_id = clause_id;
    }
}
