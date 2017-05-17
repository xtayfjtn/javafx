package application.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by ZQ on 2017/5/17.
 */
public class Line {
    private int lineID;
    private int startID;
    private int endID;

    @XmlElement
    public int getLineID() {
        return lineID;
    }

    @XmlElement
    public void setLineID(int lineID) {
        this.lineID = lineID;
    }

    @XmlElement
    public int getStartID() {
        return startID;
    }

    @XmlElement
    public void setStartID(int startID) {
        this.startID = startID;
    }

    @XmlElement
    public int getEndID() {
        return endID;
    }

    @XmlElement
    public void setEndID(int endID) {
        this.endID = endID;
    }
}
