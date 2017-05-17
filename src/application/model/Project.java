package application.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by ZQ on 2017/5/17.
 */
public class Project {
    private int projectID;
    private String projectName;
    private String desc;
    private String type;

    @XmlElement
    public int getProjectID() {
        return projectID;
    }

    @XmlElement
    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    @XmlElement
    public String getProjectName() {
        return projectName;
    }

    @XmlElement
    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
}
