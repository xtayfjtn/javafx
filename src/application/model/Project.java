package application.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by ZQ on 2017/5/17.
 */
public class Project {
    private int projectid;
    private String projectName;

    @XmlElement
    public int getProjectid() {
        return projectid;
    }

    @XmlElement
    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    @XmlElement
    public String getProjectName() {
        return projectName;
    }

    @XmlElement
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
