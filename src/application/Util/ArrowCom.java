package application.Util;

import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 * Created by ZQ on 2017/4/25.
 */
public class ArrowCom extends Line {
    ArrowCom(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
        startXProperty().bind(startX);
        startYProperty().bind(startY);
        endXProperty().bind(endX);
        endYProperty().bind(endY);
        setMouseTransparent(true);
    }
}
