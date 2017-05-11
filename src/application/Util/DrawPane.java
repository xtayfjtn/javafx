package application.Util;

import application.controller.ComponentController;
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
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;


/**
 * Created by ZQ on 2017/4/14.
 */
public class DrawPane extends Pane {

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
                        drawStart(circleX, circleY, circleR, Color.BLACK);
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
                        drawEnd(circleX, circleY, circleR + 10);
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
        line = new LineCom(startX, startY, startX, startY);
        getChildren().add(line);
    }

    //画出分支；
    private void drawBranch(double eventX, double eventY) {
        if (isInComponent(eventX, eventY)) {
            return;
        }
        Label text = new Label("guard?");
        double leftX = eventX - 40;
        double leftY = eventY;
        double topX = eventX;
        double topY = eventY - 20;
        double rightX = eventX + 40;
        double rightY = eventY;
        double bottomX = eventX;
        double bottomY = eventY + 20;
        PolygonCom pc = new PolygonCom(text, this, leftX, leftY, topX, topY, rightX, rightY, bottomX, bottomY, leftX, leftY);
        getChildren().add(pc);
        getChildren().add(text);
    }

    //画出顺序控件；
    private void drawEllipse(double x, double y, double radiusX, double radiusY) {
        if (isInComponent(x, y)) {
            return;
        }
        Label text = new Label("Activity");
        EllipseCom a = new EllipseCom(x, y, radiusX, radiusY, this, text);
        a.setFill(Color.BISQUE);
        a.setStroke(Color.BLACK);
        getChildren().add(a);
        getChildren().add(text);

    }

    //画出开始控件；
    private void drawStart(DoubleProperty x, DoubleProperty y, double radius, Paint fill) {
        if (isInComponent(x.get(), y.get())) {
            return;
        }
        Label text = new Label("开始");//控件下方标签
        CircleCom start = new CircleCom(Color.PALEGREEN, x, y, radius, this, text);
        getChildren().add(text);
        getChildren().add(start);
    }

    //画同步的控件；
    private void drawSynch(double x, double y) {
        if (isInComponent(x, y)) {
            return;
        }
        SynchCom line = new SynchCom(x - synchR - 1, y, x + synchR - 1, y, this);
        line.setStrokeWidth(10);
        getChildren().add(line);
    }

    //画结束节点。
    private void drawEnd(DoubleProperty x, DoubleProperty y, double radius) {
        //以下是正式代码，以上是调试代码
        EndCircleCom ecc = new EndCircleCom(Color.BLACK, x, y, radius, this);
        RadialGradient gradient = new RadialGradient(0, .5, x.get(), y.get(), 20, false, CycleMethod.NO_CYCLE, new Stop(0, Color.BLACK), new Stop(.8, Color.BLACK), new Stop(.8, Color.WHITE), new Stop(1, Color.WHITE));
        ecc.setFill(gradient);
        ecc.setStroke(Color.BLACK);
        getChildren().add(ecc);
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
                    line.bindStartProperties(startCenter.centerXProperty(), startCenter.centerYProperty());
                }
                else {
                    Node end = getChildren().get(i);
                    Center endCenter = new Center(end);
                    line.bindEndProperties(endCenter.centerXProperty(), endCenter.centerYProperty());
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
}
