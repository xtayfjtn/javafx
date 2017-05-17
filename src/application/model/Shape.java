package application.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by ZQ on 2017/5/17.
 */
public class Shape {
    private int shapeID;
    private int type;
    private double layoutX;
    private double layoutY;
    private int clauseID;

    @XmlElement
    public int getShapeID() {
        return shapeID;
    }

    @XmlElement
    public void setShapeID(int shapeID) {
        this.shapeID = shapeID;
    }

    @XmlElement
    public int getType() {
        return type;
    }

    @XmlElement
    public void setType(int type) {
        this.type = type;
    }

    @XmlElement
    public double getLayoutX() {
        return layoutX;
    }

    @XmlElement
    public void setLayoutX(double layoutX) {
        this.layoutX = layoutX;
    }

    @XmlElement
    public double getLayoutY() {
        return layoutY;
    }

    @XmlElement
    public void setLayoutY(double layoutY) {
        this.layoutY = layoutY;
    }

    @XmlElement
    public int getClauseID() {
        return clauseID;
    }

    @XmlElement
    public void setClauseID(int clauseID) {
        this.clauseID = clauseID;
    }
}
