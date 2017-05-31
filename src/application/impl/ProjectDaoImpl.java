package application.impl;

import application.util.Loger;
import application.util.SystemUtil;
import application.dao.ProjectDao;
import application.model.Project;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by ZQ on 2017/5/19.
 */
public class ProjectDaoImpl implements ProjectDao {
    @Override
    public int insert(Project project) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num;
        try {
            num = session.insert("application.mapper.ProjectDaoMapper.insert", project);
        } catch (Exception e) {
            Loger.logi("Error when insert Project");
        }

        session.commit();
        session.close();
        return 0;
    }

    @Override
    public int update(Project project) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = session.update("application.mapper.ProjectDaoMapper.update", project);
        session.commit();
        session.close();
        return num;
    }

    @Override
    public int delete(int project_id) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = session.delete("application.mapper.ProjectDaoMapper.delete", project_id);
        session.commit();
        session.close();
        return num;
    }

    @Override
    public List<Project> selectAll() {
        SqlSession sqlSession = SystemUtil.openAndGetSession();
        List<Project> projects = sqlSession.selectList("application.mapper.ProjectDaoMapper.selectAll");
        sqlSession.commit();
        sqlSession.close();
        return projects;
    }

    @Override
    public int countAll() {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = session.selectOne("application.mapper.ProjectDaoMapper.countAll");
        session.commit();
        session.close();
        return num;
    }

    @Override
    public Project findByProjectName(String projectName) {
        SqlSession session = SystemUtil.openAndGetSession();
        Project project = session.selectOne("application.mapper.ProjectDaoMapper.findByProjectName", projectName);
        session.commit();
        session.close();
        return project;
    }
}
