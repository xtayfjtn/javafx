package application.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by ZQ on 2017/5/17.
 */
public class Line {
    private int line_id;
    private int start_id = 1;
    private int end_id = 2;

    @XmlElement
    public int getLine_id() {
        return line_id;
    }

    @XmlElement
    public void setLine_id(int line_id) {
        this.line_id = line_id;
    }

    @XmlElement
    public int getStart_id() {
        return start_id;
    }

    @XmlElement
    public void setStart_id(int start_id) {
        this.start_id = start_id;
    }

    @XmlElement
    public int getEnd_id() {
        return end_id;
    }

    @XmlElement
    public void setEnd_id(int end_id) {
        this.end_id = end_id;
    }

    @Override
    public String toString() {
        return "Line{" +
                "line_id=" + line_id +
                ", start_id=" + start_id +
                ", end_id=" + end_id +
                '}';
    }
}
