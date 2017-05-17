package application.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by ZQ on 2017/5/17.
 */
public class Parameter {
    private int paameterID;
    private String category;
    private String type;
    private String name;
    private String desc;
    private String order;
    private String clauseID;

    @XmlElement
    public int getPaameterID() {
        return paameterID;
    }

    @XmlElement
    public void setPaameterID(int paameterID) {
        this.paameterID = paameterID;
    }

    @XmlElement
    public String getCategory() {
        return category;
    }

    @XmlElement
    public void setCategory(String category) {
        this.category = category;
    }

    @XmlElement
    public String getType() {
        return type;
    }

    @XmlElement
    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getDesc() {
        return desc;
    }

    @XmlElement
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @XmlElement
    public String getOrder() {
        return order;
    }

    @XmlElement
    public void setOrder(String order) {
        this.order = order;
    }

    @XmlElement
    public String getClauseID() {
        return clauseID;
    }

    @XmlElement
    public void setClauseID(String clauseID) {
        this.clauseID = clauseID;
    }
}
