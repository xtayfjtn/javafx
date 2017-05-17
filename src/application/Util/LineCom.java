package application.Util;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

/**
 * Created by ZQ on 2017/5/4.
 */
public class LineCom extends Line implements BaseCom {
    private DrawPane parentPane;
    public LineCom(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY, DrawPane pane) {
        super(startX.get(), startY.get(), endX.get(), endY.get());
        addEvent();
        setStrokeWidth(2);
        setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.5));
        setStroke(Color.GRAY);
        setStrokeLineCap(StrokeLineCap.BUTT);
        parentPane = pane;
//        getStrokeDashArray().setAll(10.0, 5.0);
//        setMouseTransparent(true);

    }

    public void addEvent() {
        addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            if (e.isSecondaryButtonDown()) {
                ContextMenu contextMenu = new CompContextMenu(this);
                contextMenu.show(parentPane, e.getScreenX(), e.getScreenY());
                return;
            }
        });
    }

    public void bindStartProperties(DoubleProperty startX, DoubleProperty startY) {
        startXProperty().bind(startX);
        startYProperty().bind(startY);
    }

    public void bindEndProperties(DoubleProperty endX, DoubleProperty endY) {
        endXProperty().bind(endX);
        endYProperty().bind(endY);
    }

    @Override
    public void delete() {
        parentPane.getChildren().remove(this);
    }
}
