package application.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by ZQ on 2017/5/17.
 */
public class Clause {
    private int clause_id;
    private int project_id;
    private String clauseName = "clauseName";
    private String description ="模块描述";
    private String cType = "模块/功能";
    private String cOrder = "1";
    private String cLevel = "2";
    private int fatherClause_id;

    @XmlElement
    public int getClause_id() {
        return clause_id;
    }

    @XmlElement
    public void setClause_id(int clause_id) {
        this.clause_id = clause_id;
    }

    @XmlElement
    public int getProject_id() {
        return project_id;
    }

    @XmlElement
    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    @XmlElement
    public String getClauseName() {
        return clauseName;
    }

    @XmlElement
    public void setClauseName(String clauseName) {
        this.clauseName = clauseName;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public String getcType() {
        return cType;
    }

    @XmlElement
    public void setcType(String cType) {
        this.cType = cType;
    }

    @XmlElement
    public String getcOrder() {
        return cOrder;
    }

    @XmlElement
    public void setcOrder(String cOrder) {
        this.cOrder = cOrder;
    }

    @XmlElement
    public String getcLevel() {
        return cLevel;
    }

    @XmlElement
    public void setcLevel(String cLevel) {
        this.cLevel = cLevel;
    }

    @XmlElement
    public int getFatherClause_id() {
        return fatherClause_id;
    }

    @XmlElement
    public void setFatherClause_id(int fatherClause_id) {
        this.fatherClause_id = fatherClause_id;
    }

    @Override
    public String toString() {
        return "Clause{" +
                "clause_id=" + clause_id +
                ", project_id=" + project_id +
                ", clauseName='" + clauseName + '\'' +
                ", description='" + description + '\'' +
                ", cType='" + cType + '\'' +
                ", cOrder='" + cOrder + '\'' +
                ", cLevel='" + cLevel + '\'' +
                ", fatherClause_id=" + fatherClause_id +
                '}';
    }
}
