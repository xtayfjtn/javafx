package application.impl;

import application.Util.Loger;
import application.Util.SystemUtil;
import application.dao.ClauseDao;
import application.model.Clause;
import application.model.Project;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by ZQ on 2017/5/19.
 */
public class ClauseDaoImpl implements ClauseDao {
    @Override
    public int insert(Clause clause) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.insert("application.mapper.ClauseDaoMapper.insert", clause);
        } catch (Exception e) {
            Loger.logi("Error when inserting Clause");
        }

        session.commit();
        session.close();
        return num;
    }

    @Override
    public int update(Clause clause) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.update("application.mapper.ClauseDaoMapper.update", clause);
        } catch (Exception e) {
            Loger.loge("Error when updating Clause;");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public int delete(int clause_id) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.delete("application.mapper.ClauseDaoMapper.delete", clause_id);
        } catch (Exception e) {
            Loger.loge("Error when deleting Clause");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public List<Clause> selectAll() {
        SqlSession session = SystemUtil.openAndGetSession();
        List<Clause> clauses = null;
        try {
            clauses = session.selectList("application.mapper.ClauseDaoMapper.selectAll");
        } catch (Exception e) {
            Loger.loge("Error when selecting Clause;");
        }
        session.commit();
        session.close();
        return clauses;
    }

    @Override
    public int countAll() {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.selectOne("application.mapper.ClauseDaoMapper.countAll");
        } catch (Exception e) {
            Loger.loge("Error when countAll Clause");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public Clause findByClauseName(String clauseName) {
        SqlSession session = SystemUtil.openAndGetSession();
        Clause clause = null;
        try {
            clause = session.selectOne("application.mapper.ClauseDaoMapper.findByClauseName", clauseName);
        } catch (Exception e) {
            Loger.loge("Error when findbyclausename;");
        }
        session.commit();
        session.close();
        return clause;
    }

    @Override
    public List<Clause> selectByProject(int project_id) {
        SqlSession session = SystemUtil.openAndGetSession();
        List<Clause> clauses = null;
        try {
            clauses = session.selectList("application.mapper.ClauseDaoMapper.selectByProject", project_id);
        } catch (Exception e) {
            Loger.loge("Error when selectByProject.");
        }
        session.commit();
        session.close();
        return clauses;
    }

    @Override
    public List<Clause> selectByFatherClause(int clause_id) {
        SqlSession session = SystemUtil.openAndGetSession();
        List<Clause> clauses = null;
        try {
            clauses = session.selectList("application.mapper.ClauseDaoMapper.selectByFatherClause", clause_id);
        } catch (Exception e) {
            Loger.loge("Error when selectByFatherClause");
        }
        session.commit();
        session.close();
        return clauses;
    }

    @Override
    public int getMaxId() {
        SqlSession session = SystemUtil.openAndGetSession();
        int id = 0;
        try {
            id = session.selectOne("application.mapper.ClauseDaoMapper.getMaxId");
        } catch (Exception e) {
            Loger.loge("Error when getMaxId of Clause");
        }

        session.commit();
        session.close();
        return id;
    }
}
