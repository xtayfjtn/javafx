package application.dao;

import application.model.Project;

import java.util.List;

/**
 * Created by ZQ on 2017/5/17.
 */
public interface ProjectDao {
    public int insert(Project project);

    public int update(Project project);

    public int delete(int projectID);

    public List<Project> selectAll();

    public int countAll();

    public Project findByProjectName(String projectName);
}
