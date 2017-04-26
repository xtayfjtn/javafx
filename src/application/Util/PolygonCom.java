package application.Util;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.Ellipse2D;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.scene.DirtyBits;
import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;

/**
 * Created by ZQ on 2017/4/15.
 */
public class PolygonCom extends Polygon {
    //pane的宽度和高度
    private double pWidth;
    private double pHeight;

    private double xOffset;
    private double yOffset;

    //形状的中心的坐标
    private double centerX;
    private double centerY;

    //形状的宽度和高度
    private double mWidth;
    private double mHeight;


    public PolygonCom() {
    }

    public PolygonCom(DrawPane pane, double... points) {
        super(points);
        pWidth = pane.getWidth();
        pHeight = pane.getHeight();
        int len = points.length;
        double sumX = 0;
        double sumY = 0;
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                sumX += points[i];
            } else {
                sumY += points[i];
            }
        }

        setCenterX(sumX * 2 / len);
        setCenterY(sumY * 2 / len);

        mWidth = Math.abs(points[0] - getCenterX());
        mHeight = Math.abs(points[1] - getCenterY());
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
        addEvent();
    }

    public final void setCenterX(double value) {
        centerX = value;
    }

    public final void setCenterY(double value) {
        centerY = value;
    }


    public void addEvent() {
        addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            e.consume();
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
            e.consume();


            double relativeX = getCenterX() + getLayoutX();
            double relativeY = getCenterY() + getLayoutY();
            double finalX = e.getSceneX() - xOffset;
            double finalY= e.getSceneY() - yOffset;
            if (relativeX < mWidth) {
                finalX = mWidth - getCenterX();
            }
            if (relativeX > pWidth - mWidth) {
                finalX = pWidth - mWidth - getCenterX();
            }

            if (relativeY < mHeight) {
                finalY = mHeight - getCenterY();
            }

            if (relativeY > pHeight - mHeight) {
                finalY = pHeight - mHeight - getCenterY();
            }
            setLayoutX(finalX);
            setLayoutY(finalY);
            setCenterX(e.getX());
            setCenterY(e.getY());
        });

        addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent e) -> {
            setCenterX(e.getX());
            setCenterY(e.getY());
        });
    }

    public final double getCenterX() {
        return centerX;
    }

    public final double getCenterY() {
        return centerY;
    }


}
