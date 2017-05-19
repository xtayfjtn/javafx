package application.dao;

import application.model.Line;

import java.util.List;

/**
 * Created by ZQ on 2017/5/18.
 */
public interface LineDao {
    public int insert(Line line);

    public int update(Line line);

    public int delete(int line_id);

    public List<Line> selectAll();

    public int countAll();

    public Line find(int lineID);
}
