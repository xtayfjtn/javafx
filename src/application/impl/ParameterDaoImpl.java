package application.impl;

import application.Util.Loger;
import application.Util.SystemUtil;
import application.dao.ParameterDao;
import application.model.Parameter;
import org.apache.ibatis.session.SqlSession;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * Created by ZQ on 2017/5/19.
 */
public class ParameterDaoImpl implements ParameterDao {
    @Override
    public int insert(Parameter parameter) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.insert("application.mapper.ParameterDaoMapper.insert", parameter);
        } catch (Exception e) {
            Loger.loge("Error when inserting parameter");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public int update(Parameter parameter) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.update("application.mapper.ParameterDaoMapper.update", parameter);
        } catch (Exception e) {
            Loger.loge("Error when updating parameter");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public int delete(int parameter_id) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.delete("application.mapper.ParameterDaoMapper.delete", parameter_id);
        } catch (Exception e) {
            Loger.loge("Error when deleting parameter");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public List<Parameter> selectAll() {
        SqlSession session = SystemUtil.openAndGetSession();
        List<Parameter> parameters = null;
        try {
            parameters = session.selectList("application.mapper.ParameterDaoMapper.selectAll");
        } catch (Exception e) {
            Loger.loge("Error when selectAll parameter");
        }
        session.commit();
        session.close();
        return parameters;
    }

    @Override
    public int countAll() {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.selectOne("application.mapper.ParameterDaoMapper.countAll");
        } catch (Exception e) {
            Loger.loge("Error when countAll parameter");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public Parameter find(int parameter_id) {
        SqlSession session = SystemUtil.openAndGetSession();
        Parameter parameter = null;
        try {
            parameter = session.selectOne("application.mapper.ParameterDaoMapper.find", parameter_id);
        } catch (Exception e) {
            Loger.loge("Error when find parameter");
        }
        session.commit();
        session.close();
        return parameter;
    }
}

