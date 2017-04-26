package application.Util;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by ZQ on 2017/4/12.
 */
public class MyCanvas extends Canvas {
    private GraphicsContext gc;
    public MyCanvas(double width, double height) {
        super(width, height);
        gc = getGraphicsContext2D();
        draw(gc);
    }

    private void draw(GraphicsContext gc) {
        gc.save();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);                //设置线的宽度
        gc.strokeLine(0, 0, 50, 50);       //绘制直线
        gc.restore();

        //绘制椭圆
        gc.save();
        gc.setFill(Color.YELLOWGREEN);
        gc.strokeOval(0, 80, 50, 50);
        gc.fillOval(100, 80, 50, 50);
        gc.restore();

        //绘制多边形
        gc.save();
        gc.setFill(Color.RED);
        gc.setStroke(Color.CHOCOLATE);
        gc.fillPolygon(new double[]{0, 40, 50, 60, 100, 70, 85, 50, 15, 30}, new double[]{440,440,400,440,440,  460,500,470,500,460}, 10);
        gc.strokePolygon(new double[]{0, 40, 50, 60, 100, 70, 85, 50, 15, 30}, new double[]{440,440,400,440,440,  460,500,470,500,460}, 10);
        gc.restore();
    }

    public void addEllipse(double x, double y) {
        gc.save();
        gc.setFill(Color.YELLOWGREEN);
        gc.strokeOval(x, y, 50, 50);
        gc.fillOval(x, y, 50, 50);
        gc.restore();
    }
}
