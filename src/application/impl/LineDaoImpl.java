package application.impl;

import application.Util.Loger;
import application.Util.SystemUtil;
import application.dao.LineDao;
import application.model.Line;
import com.sun.org.apache.bcel.internal.generic.LOR;
import org.apache.ibatis.session.SqlSession;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.logging.Logger;

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
    public Line find(int lineID) {
        SqlSession session = SystemUtil.openAndGetSession();
        Line line = null;
        try {
            line = session.selectOne("application.mapper.LineDaoMapper.find", lineID);
        } catch (Exception e) {
            Loger.loge("Error when find line");
        }
        session.commit();
        session.close();
        return line;
    }
}