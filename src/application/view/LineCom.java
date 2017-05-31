package application.view;

import application.util.CompContextMenu;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
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
    private int start_id = 0;
    private int end_id = 0;
    private int line_id = 0;
    public LineCom() {
        super();
        addEvent();
    }
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

    public void bindStartProperties(DoubleProperty startX, DoubleProperty startY, Node start) {
        startXProperty().bind(startX);
        startYProperty().bind(startY);
//        System.out.println(start.getClass());
        if (start instanceof CircleCom) {
            start_id = ((CircleCom) start).getShape_id();
        }
        if (start instanceof EllipseCom) {
            start_id = ((EllipseCom) start).getShape_id();
        }
        if (start instanceof PolygonCom) {
            start_id = ((PolygonCom) start).getShape_id();
        }
        if (start instanceof SynchCom) {
            start_id = ((SynchCom) start).getShape_id();
        }
        if (start instanceof EndCircleCom) {
            start_id = ((EndCircleCom) start).getShape_id();
        }
    }

    public void bindEndProperties(DoubleProperty endX, DoubleProperty endY, Node end) {
        endXProperty().bind(endX);
        endYProperty().bind(endY);
        if (end instanceof CircleCom) {
            end_id = ((CircleCom) end).getShape_id();
        }
        if (end instanceof EllipseCom) {
            end_id = ((EllipseCom) end).getShape_id();
        }
        if (end instanceof PolygonCom) {
            end_id = ((PolygonCom) end).getShape_id();
        }
        if (end instanceof SynchCom) {
            end_id = ((SynchCom) end).getShape_id();
        }
        if (end instanceof EndCircleCom) {
            end_id = ((EndCircleCom) end).getShape_id();
        }
    }

    @Override
    public void delete() {
        parentPane.getChildren().remove(this);
    }

    public int getStart_id() {
        return start_id;
    }

    public void setStart_id(int start_id) {
        this.start_id = start_id;
    }

    public int getEnd_id() {
        return end_id;
    }

    public void setEnd_id(int end_id) {
        this.end_id = end_id;
    }

    public int getLine_id() {
        return line_id;
    }

    public void setLine_id(int line_id) {
        this.line_id = line_id;
    }
}
