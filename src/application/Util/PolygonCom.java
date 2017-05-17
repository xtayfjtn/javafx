package application.Util;

import application.controller.ComponentController;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.Ellipse2D;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.scene.DirtyBits;
import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

/**
 * Created by ZQ on 2017/4/15.
 */
public class PolygonCom extends Polygon implements BaseCom {
    //pane的宽度和高度
    private double pWidth;
    private double pHeight;

    private double xOffset;
    private double yOffset;

    //形状的宽度和高度
    private double mWidth;
    private double mHeight;

    private DrawPane parentPane;

    //形状的中心的坐标
    private DoubleProperty centerX = new SimpleDoubleProperty();
    private DoubleProperty centerY = new SimpleDoubleProperty();

    public Label attr;

    private long currentTime = 0;
    private long lastTime = 0;


    public PolygonCom() {
    }

    public PolygonCom(DrawPane pane, double... points) {
        super(points);
        setCenter();
        bindCenter();
        pWidth = pane.getWidth();
        pHeight = pane.getHeight();
        parentPane = pane;
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

//        setCenterX(sumX * 2 / len);
//        setCenterY(sumY * 2 / len);

        mWidth = Math.abs(points[0] - getCenterX().get());
        mHeight = Math.abs(points[1] - getCenterY().get());
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
        addEvent();
    }

    public PolygonCom(Label text, DrawPane pane, double... points) {
        super(points);
        setCenter();
        bindCenter();
        pWidth = pane.getWidth();
        pHeight = pane.getHeight();
        attr = text;
        attr.layoutXProperty().bind(getCenterX());
        attr.layoutYProperty().bind(getCenterY());
        parentPane = pane;
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

//        setCenterX(sumX * 2 / len);
//        setCenterY(sumY * 2 / len);

        mWidth = Math.abs(points[0] - getCenterX().get());
        mHeight = Math.abs(points[1] - getCenterY().get());
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
        addEvent();
    }

    public void setCenter() {
        Bounds bounds = getBoundsInParent();
        centerX.set(bounds.getMinX() + bounds.getWidth()  / 2);
        centerY.set(bounds.getMinY() + bounds.getHeight() / 2);
    }

    public void bindCenter() {
        boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                setCenter();
            }
        });
    }
    public void addEvent() {
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

            if (isDbClicked) {
                Stage stage = AttrStage.getInstance(this);
                stage.show();
                System.out.println("db is true");
            } else {
                xOffset = e.getSceneX();
                yOffset = e.getSceneY();
                if (ComponentController.type == 6) {
                    if (!parentPane.isLine) {
                        parentPane.startX = getCenterX();
                        parentPane.startY = getCenterY();
                        parentPane.isLine = true;
                    } else{
                        parentPane.endX = getCenterX();
                        parentPane.endY = getCenterY();
//                    parentPane.drawLine();
                        parentPane.isLine = false;
                    }
                }
            }

        });

        addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
//            e.consume();
            if (ComponentController.type == 6) {
                return;
            }
            double relativeX = getCenterX().get() + getLayoutX();
            double relativeY = getCenterY().get() + getLayoutY();
            double finalX = e.getSceneX() - xOffset;
            double finalY= e.getSceneY() - yOffset;
            if (relativeX < mWidth) {
                finalX = mWidth - getCenterX().get();
            }
            if (relativeX > pWidth - mWidth) {
                finalX = pWidth - mWidth - getCenterX().get();
            }

            if (relativeY < mHeight) {
                finalY = mHeight - getCenterY().get();
            }

            if (relativeY > pHeight - mHeight) {
                finalY = pHeight - mHeight - getCenterY().get();
            }
            setLayoutX(finalX);
            setLayoutY(finalY);

        });

        addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent e) -> {
        });
    }

    public final DoubleProperty getCenterX() {
        return centerX;
    }

    public final DoubleProperty getCenterY() {
        return centerY;
    }


    @Override
    public void delete() {
        parentPane.getChildren().remove(this);
        parentPane.getChildren().remove(attr);
    }
}
