package application.Util;

import application.controller.ComponentController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;


/**
 * 线控件
 * Created by ZQ on 2017/4/19.
 */
public class SynchCom extends Line {

    //主布局宽度
    private double pWidth;
    private double pHeight;
    private DrawPane parentPane;
    //控件宽度
    private double mWidth;

    private double centerX;
    private double centerY;

    public SynchCom() {
    }

    public SynchCom(double startX, double startY, double endX, double endY, DrawPane pane) {
        super(startX, startY, endX, endY);
        parentPane = pane;
        pWidth = pane.getWidth();
        pHeight = pane.getHeight();
        mWidth = Math.abs(endX - startX) / 2;
        centerX = (startX + endX) / 2;
        centerY = (startY + endY) / 2;
        addEvent();
    }

    public void addEvent() {

        addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
//            e.consume();
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
            double mStartx = e.getX() - 40;
            double mStarty = e.getY();
            double mEndx = e.getX() + 40;
            double mEndy = e.getY();
            if (mStartx < 0) {
                mStartx = 0;
                mEndx = 2 * mWidth;
            }
            if (mEndx > pWidth) {
                mStartx = pWidth - 2 * mWidth;
                mEndx = pWidth;
            }

            if (mStarty < 0) {
                mStarty = 0;
                mEndy = 0;
            }
            if (mStarty > pHeight) {
                mEndy = pHeight;
                mStarty = pHeight;
            }

            setStartX(mStartx);
            setEndX(mEndx);
            setStartY(mStarty);
            setEndY(mEndy);
        });
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }
}
