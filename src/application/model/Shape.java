package application.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by ZQ on 2017/5/17.
 */
public class Shape {
    private int shape_id;
    private int sType = 0;
    private double layoutX = 0.0;
    private double layoutY = 0.0;
    private int clause_id;
    private String attr = "开始";

    @XmlElement
    public int getShape_id() {
        return shape_id;
    }

    @XmlElement
    public void setShape_id(int shape_id) {
        this.shape_id = shape_id;
    }

    @XmlElement
    public int getsType() {
        return sType;
    }

    @XmlElement
    public void setsType(int sType) {
        this.sType = sType;
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
    public int getClause_id() {
        return clause_id;
    }

    @XmlElement
    public void setClause_id(int clause_id) {
        this.clause_id = clause_id;
    }

    @XmlElement
    public String getAttr() {
        return attr;
    }

    @XmlElement
    public void setAttr(String attr) {
        this.attr = attr;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "shape_id=" + shape_id +
                ", sType=" + sType +
                ", layoutX=" + layoutX +
                ", layoutY=" + layoutY +
                ", clause_id=" + clause_id +
                ", attr='" + attr + '\'' +
                '}';
    }
}
