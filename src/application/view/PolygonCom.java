package application.view;

import application.controller.ComponentController;
import application.util.AttrStage;
import application.util.CompContextMenu;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 * Created by ZQ on 2017/4/15.
 */
public class PolygonCom extends Polygon implements BaseCom {
    public static final double BRANCH_X = 40.0;
    public static final double BRANCH_Y = 20.0;

    final ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();

    private int shape_id;
    //pane的宽度和高度
//    private double pWidth;
//    private double pHeight;

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

    public PolygonCom(double centerX, double centerY, DrawPane pane) {
//        double leftX = centerX - BRANCH_X;
//        double leftY = centerY;
//        double topX = centerX;
//        double topY = centerY - BRANCH_Y;
//        double rightX = centerX + BRANCH_X;
//        double rightY = centerY;
//        double bottomX = centerX;
//        double bottomY = centerY + BRANCH_Y;
        super(centerX - BRANCH_X, centerY, centerX, centerY - BRANCH_Y, centerX + BRANCH_X, centerY, centerX, centerY + BRANCH_Y);
        setCenter();
        bindCenter();
        parentPane = pane;
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
        int len = getPoints().size();
        double sumX = 0;
        double sumY = 0;
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                sumX += getPoints().get(i);
            } else {
                sumY += getPoints().get(i);
            }
        }

//        setCenterX(sumX * 2 / len);
//        setCenterY(sumY * 2 / len);

        mWidth = Math.abs(getPoints().get(0) - getCenterX().get());
        mHeight = Math.abs(getPoints().get(1) - getCenterY().get());
        addEvent();
        attr = new Label("选择");
        attr.layoutXProperty().bind(getCenterX());
        attr.layoutYProperty().bind(getCenterY());

    }

    public PolygonCom(DrawPane pane, double... points) {
        super(points);
        setCenter();
        bindCenter();
//        pWidth = pane.getWidth();
//        pHeight = pane.getHeight();
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
//        pWidth = pane.getWidth();
//        pHeight = pane.getHeight();
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
//                mousePosition.set(new Point2D(e.getSceneX(), e.getSceneY()));
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

//        改进但有缺陷版拖拽反馈
//        addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent event) -> {
//            double deltaX = event.getSceneX() - mousePosition.get().getX();
//            double deltaY = event.getSceneY() - mousePosition.get().getY();
//            setLayoutX(getLayoutX()+deltaX);
//            setLayoutY(getLayoutY()+deltaY);
//            mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
//        });
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
            if (relativeX > parentPane.getWidth() - mWidth) {
                finalX = parentPane.getWidth() - mWidth - getCenterX().get();
            }

            if (relativeY < mHeight) {
                finalY = mHeight - getCenterY().get();
            }

            if (relativeY > parentPane.getHeight() - mHeight) {
                finalY = parentPane.getHeight() - mHeight - getCenterY().get();
            }
            setLayoutX(finalX);
            setLayoutY(finalY);

        });

        addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent e) -> {
            setCenter();
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

    public int getShape_id() {
        return shape_id;
    }

    public void setShape_id(int shape_id) {
        this.shape_id = shape_id;
    }
}
