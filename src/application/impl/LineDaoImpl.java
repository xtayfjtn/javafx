package application.impl;

import application.util.Loger;
import application.util.SystemUtil;
import application.dao.LineDao;
import application.model.Line;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by ZQ on 2017/5/19.
 */
public class LineDaoImpl implements LineDao {
    @Override
    public int insert(Line line) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.insert("application.mapper.LineDaoMapper.insert", line);
        } catch (Exception e) {
            Loger.logi("Error when inserting line");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public int update(Line line) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.update("application.mapper.LineDaoMapper.update", line);
        } catch (Exception e) {
            Loger.logi("Error when updating line");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public int delete(int line_id) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.delete("application.mapper.LineDaoMapper.delete", line_id);
        } catch (Exception e) {
            Loger.logi("Error when deleting line");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public List<Line> selectAll() {
        SqlSession session = SystemUtil.openAndGetSession();
        List<Line> lines = null;
        try {
            lines = session.selectList("application.mapper.LineDaoMapper.selectAll");
        } catch (Exception e) {
            Loger.loge("Error when deleting line");
        }
        session.commit();
        session.close();
        return lines;
    }

    @Override
    public int countAll() {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.selectOne("application.mapper.LineDaoMapper.countAll");
        } catch (Exception e) {
            Loger.loge("Error when countAll lines");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public Line find(int line_id) {
        SqlSession session = SystemUtil.openAndGetSession();
        Line line = null;
        try {
            line = session.selectOne("application.mapper.LineDaoMapper.find", line_id);
        } catch (Exception e) {
            Loger.loge("Error when find line");
        }
        session.commit();
        session.close();
        return line;
    }

    @Override
    public List<Line> selectByClauseId(int clause_id) {
        SqlSession session = SystemUtil.openAndGetSession();
        List<Line> lines = null;
        try {
            lines = session.selectList("application.mapper.LineDaoMapper.selectByClauseId", clause_id);
        } catch (Exception e) {
            Loger.loge("Error when find Line");
        }
        session.commit();
        session.close();
        return lines;
    }

    @Override
    public void insertUpdate(Line line) {
        SqlSession session = SystemUtil.openAndGetSession();
        try {
            session.selectOne("application.mapper.LineDaoMapper.insertUpdate", line);
        } catch (Exception e) {
            Loger.loge("Error when insertUpdate line");
        }
        session.commit();
        session.close();
    }
}
