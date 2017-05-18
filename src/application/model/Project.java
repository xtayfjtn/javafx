package application.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by ZQ on 2017/5/17.
 */
public class Project {
    private int project_id;
    private String projectName = "新项目";
    private String description = "这是一个新项目";
    private String pType = "type1";

    @XmlElement
    public int getProject_id() {
        return project_id;
    }

    @XmlElement
    public void setProject_id(int project_id) {
        this.project_id = project_id;
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
    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public String getpType() {
        return pType;
    }

    @XmlElement
    public void setpType(String pType) {
        this.pType = pType;
    }

    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                ", pType='" + pType + '\'' +
                '}';
    }
}
