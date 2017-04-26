package application.Util;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;

/**
 * Created by ZQ on 2017/4/19.
 */
public class EndCircleCom extends CircleCom {
//    private RadialGradient gradient = null;
//    private double pWidth;
//    private double pHeight;
//    private double mradius;

    public EndCircleCom(double radius, DrawPane pane) {
        super(radius, pane);
    }

    public EndCircleCom(double radius, Paint fill, DrawPane pane) {
        super(radius, fill, pane);
    }

    public EndCircleCom(DrawPane pane) {
        super(pane);
    }

    public EndCircleCom(double centerX, double centerY, double radius, DrawPane pane) {
        super(centerX, centerY, radius, pane);
    }

    public EndCircleCom(double centerX, double centerY, double radius, Paint fill, DrawPane pane) {
        super(centerX, centerY, radius, fill, pane);
    }

    public EndCircleCom(double centerX, double centerY, double radius, Paint fill) {
        super(centerX, centerY, radius, fill);
    }

    @Override
    public void addEvent() {
        super.addEvent();
        addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
            double mx = e.getX();
            double my = e.getY();

            if (mx < mradius) {
                mx = mradius;
            }

            if (mx > pWidth - mradius) {
                mx = pWidth - mradius;
            }

            if (my < mradius) {
                my = mradius;
            }
            if (my > pHeight - mradius) {
                my = pHeight - mradius;
            }
            RadialGradient gradient = new RadialGradient(0, .5, mx, my, 20, false, CycleMethod.NO_CYCLE, new Stop(0, Color.BLACK), new Stop(.8, Color.BLACK), new Stop(.8, Color.WHITE), new Stop(1, Color.WHITE));
            setFill(gradient);
        });
    }
}
