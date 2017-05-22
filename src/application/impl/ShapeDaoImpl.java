package application.impl;

import application.Util.Loger;
import application.Util.SystemUtil;
import application.dao.ShapeDao;
import application.model.Line;
import application.model.Parameter;
import application.model.Shape;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by ZQ on 2017/5/19.
 */
public class ShapeDaoImpl implements ShapeDao{
    @Override
    public int insert(Shape shape) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.insert("application.mapper.ShapeDaoMapper.insert", shape);
        } catch (Exception e) {
            Loger.loge("Error when inserting shape");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public int update(Shape shape) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.update("application.mapper.ShapeDaoMapper.update", shape);
        } catch (Exception e) {
            Loger.loge("Error when updating shape");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public int delete(int shape_id) {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.delete("application.mapper.ShapeDaoMapper.delete", shape_id);
        } catch (Exception e) {
            Loger.loge("Error when deleting shape");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public List<Shape> selectAll() {
        SqlSession session = SystemUtil.openAndGetSession();
        List<Shape> shapes = null;
        try {
            shapes = session.selectList("application.mapper.ShapeDaoMapper.selectAll");
        } catch (Exception e) {
            Loger.loge("Error when selectAll shape");
        }
        session.commit();
        session.close();
        return shapes;
    }

    @Override
    public int countAll() {
        SqlSession session = SystemUtil.openAndGetSession();
        int num = 0;
        try {
            num = session.selectOne("application.mapper.ShapeDaoMapper.countAll");
        } catch (Exception e) {
            Loger.loge("Error when countAll shapes");
        }
        session.commit();
        session.close();
        return num;
    }

    @Override
    public Shape find(int shape_id) {
        SqlSession session = SystemUtil.openAndGetSession();
        Shape shape = null;
        try {
            shape = session.selectOne("application.mapper.ShapeDaoMapper.find", shape_id);
        } catch (Exception e) {
            Loger.loge("Error when find parameter");
        }
        session.commit();
        session.close();
        return shape;
    }

    @Override
    public List<Shape> selectShapesByClauseId(int clause_id) {
        SqlSession session = SystemUtil.openAndGetSession();
        List<Shape> shapes = null;
        try {
            shapes = session.selectList("application.mapper.ShapeDaoMapper.selectShapesByClauseId", clause_id);
        } catch (Exception e) {
            Loger.loge("Error when selecShapeByClauseId");
        }
        session.commit();
        session.close();
        return shapes;
    }
}
