package application.Util;

import application.controller.ComponentController;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * Created by ZQ on 2017/4/14.
 */
public class DrawPane extends Pane {

    private final double ellipseX = 40;
    private final double ellipseY = 20;
    private final double circleR = 15;
    private final double synchR = 40;

    public int type = 0;

    public DrawPane() {
        super();
        addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            double eventX = e.getX();
            double eventY = e.getY();
            double width = getWidth();
            double height = getHeight();
            type = ComponentController.type;
            switch (type) {
                case 1:
                    if (eventX > circleR && eventX < width - circleR && eventY > circleR && eventY < height - circleR) {
                        drawStart(eventX, eventY, circleR, Color.BLACK);
                    }
                    break;
                case 2:
                    if (eventX > ellipseX && eventX < width - ellipseX && eventY > ellipseY && eventY < height - ellipseY) {
                        drawEllipse(eventX, eventY, ellipseX, ellipseY);
                    }
                    break;
                case 3:
                    if (eventX > ellipseX && eventX < width - ellipseX && eventY > ellipseY && eventY < height - ellipseY) {
                        drawBranch(eventX, eventY);
                    }
                    break;
                case 4:
                    if (eventX > synchR && eventX < width - synchR && eventY > 0 && eventY < height) {
                        drawSynch(eventX, eventY);
                    }
                    break;
                case 5:
                    if (eventX > circleR + 10 && eventX < width - circleR - 10 && eventY > circleR && eventY < height - circleR - 10) {
                        drawEnd(eventX, eventY, circleR + 10);
                    }
                    break;
                case 6:
                    drawLine();
                    break;
            }
        });
    }

    private void drawLine() {

    }

    private void drawBranch(double eventX, double eventY) {
        double leftX = eventX - 40;
        double leftY = eventY;
        double topX = eventX;
        double topY = eventY - 20;
        double rightX = eventX + 40;
        double rightY = eventY;
        double bottomX = eventX;
        double bottomY = eventY + 20;
        PolygonCom pc = new PolygonCom(this, leftX, leftY, topX, topY, rightX, rightY, bottomX, bottomY, leftX, leftY);
        getChildren().add(pc);
    }

    private void drawEllipse(double x, double y, double radiusX, double radiusY) {
        EllipseCom a = new EllipseCom(x, y, radiusX, radiusY, this);
        a.setFill(Color.BISQUE);
        a.setStroke(Color.BLACK);
        getChildren().add(a);
    }

    private void drawStart(double x, double y, double radius, Paint fill) {
        CircleCom cc = new CircleCom(x, y, radius, fill, this);
        getChildren().add(cc);
    }

    private void drawSynch(double x, double y) {
        LineCom line = new LineCom(x - synchR - 1, y, x + synchR - 1, y, this);
        line.setStrokeWidth(10);
        getChildren().add(line);
    }

    private void drawEnd(double x, double y, double radius) {

        EndCircleCom ecc = new EndCircleCom(x, y, radius, this);
        RadialGradient gradient = new RadialGradient(0, .5, x, y, 20, false, CycleMethod.NO_CYCLE, new Stop(0, Color.BLACK), new Stop(.8, Color.BLACK), new Stop(.8, Color.WHITE), new Stop(1, Color.WHITE));
        ecc.setFill(gradient);
        ecc.setStroke(Color.BLACK);
        getChildren().add(ecc);
    }
}
