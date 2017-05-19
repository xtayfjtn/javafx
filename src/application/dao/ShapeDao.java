package application.dao;

import application.model.Line;
import application.model.Shape;

import java.util.List;

/**
 * Created by ZQ on 2017/5/18.
 */
public interface ShapeDao {
    public int insert(Shape shape);

    public int update(Shape shape);

    public int delete(int shape_id);

    public List<Shape> selectAll();

    public int countAll();

    public Shape find(int shape_id);
}
