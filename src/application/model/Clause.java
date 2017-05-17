package application.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by ZQ on 2017/5/17.
 */
public class Clause {
    private int clauseID;
    private int projectID;
    private String name;
    private String desc;
    private String type;
    private String order;
    private String level;
    private int fatherClauseID;

    @XmlElement
    public int getClauseID() {
        return clauseID;
    }

    @XmlElement
    public void setClauseID(int clauseID) {
        this.clauseID = clauseID;
    }

    @XmlElement
    public int getProjectID() {
        return projectID;
    }

    @XmlElement
    public void setProjectID(int projectID) {
        this.projectID = projectID;
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
    public String getType() {
        return type;
    }

    @XmlElement
    public void setType(String type) {
        this.type = type;
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
    public String getLevel() {
        return level;
    }

    @XmlElement
    public void setLevel(String level) {
        this.level = level;
    }

    @XmlElement
    public int getFatherClauseID() {
        return fatherClauseID;
    }

    @XmlElement
    public void setFatherClauseID(int fatherClauseID) {
        this.fatherClauseID = fatherClauseID;
    }
}
