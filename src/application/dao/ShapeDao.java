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

    public int delete(int shapeID);

    public List<Shape> selectAll();

    public int countAll();

    public Line find(int shapeID);
}
