package application.Util;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Node;

/**
 * Created by ZQ on 2017/5/5.
 */
public class Center {
    private DoubleProperty centerX = new SimpleDoubleProperty();
    private DoubleProperty centerY = new SimpleDoubleProperty();

    public Center(Node node) {
        calcCenter(node.getBoundsInParent());
        node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override public void changed(
                    ObservableValue<? extends Bounds> observableValue,
                    Bounds oldBounds,
                    Bounds bounds
            ) {
                calcCenter(bounds);
            }
        });
    }

    private void calcCenter(Bounds bounds) {
        centerX.set(bounds.getMinX() + bounds.getWidth()  / 2);
        centerY.set(bounds.getMinY() + bounds.getHeight() / 2);
    }

    DoubleProperty centerXProperty() {
        return centerX;
    }

    DoubleProperty centerYProperty() {
        return centerY;
    }
}
