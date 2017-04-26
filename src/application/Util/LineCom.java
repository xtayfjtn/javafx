package application.Util;

import com.sun.javafx.geom.Edge;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;


/**
 * 线控件
 * Created by ZQ on 2017/4/19.
 */
public class LineCom extends Line {

    //主布局宽度
    private double pWidth;
    private double pHeight;
    private DrawPane drawPane;
    //控件宽度
    private double mWidth;
    public LineCom() {
    }

    public LineCom(double startX, double startY, double endX, double endY, DrawPane pane) {
        super(startX, startY, endX, endY);
        drawPane = pane;
        pWidth = pane.getWidth();
        pHeight = pane.getHeight();
        mWidth = Math.abs(endX - startX) / 2;
        addEvent();
    }

    public void addEvent() {

        addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            e.consume();
        });

        addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
            e.consume();
            double mStartx = e.getX() - 40;
            double mStarty = e.getY();
            double mEndx = e.getX() + 40;
            double mEndy = e.getY();
            if (mStartx < 0) {
                mStartx = 0;
                mEndx = 2 * mWidth;
            }
            if (mEndx > pWidth) {
                mStartx = pWidth - 2 * mWidth;
                mEndx = pWidth;
            }

            if (mStarty < 0) {
                mStarty = 0;
                mEndy = 0;
            }
            if (mStarty > pHeight) {
                mEndy = pHeight;
                mStarty = pHeight;
            }

            setStartX(mStartx);
            setEndX(mEndx);
            setStartY(mStarty);
            setEndY(mEndy);
        });
    }
}
