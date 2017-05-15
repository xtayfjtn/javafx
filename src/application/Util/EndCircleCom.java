package application.Util;

import application.controller.ComponentController;
import javafx.beans.property.DoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;

/**
 * Created by ZQ on 2017/4/19.
 */
public class EndCircleCom extends CircleCom {
    public EndCircleCom(Color color, DoubleProperty x, DoubleProperty y, double radius, DrawPane pane) {
        super(color, x, y, radius, pane);
    }

    @Override
    public void addEvent() {
        super.addEvent();
        addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
            if (ComponentController.type == 6) {
                return;
            }
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
