package application.Util;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;

/**
 * Created by ZQ on 2017/4/14.
 */
public class EllipseCom extends Ellipse {
    private double xOffset;
    private double yOffset;
    private double mradiusX;
    private double mradiusY;
    private double pWidth;
    private double pHeight;
    public EllipseCom() {
        super();
    }

    public EllipseCom(double radiusX, double radiusY, DrawPane pane) {
        super(radiusX, radiusY);
        pWidth = pane.getWidth();
        pHeight = pane.getHeight();
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
        addEvent();
    }

    private void addEvent() {
        addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            e.consume();
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
            e.consume();
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
}
