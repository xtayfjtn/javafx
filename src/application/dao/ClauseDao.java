package application.dao;

import application.model.Clause;
import application.model.Project;

import java.util.List;

/**
 * Created by ZQ on 2017/5/18.
 */
public interface ClauseDao {
    public int insert(Clause clause);

    public int update(Clause clause);

    public int delete(int clause_id);

    public List<Clause> selectAll();

    public int countAll();

    public Clause findByClauseName(String clauseName);

    public List<Clause> selectByProject(int project_id);

    public List<Clause> selectByFatherClause(int clause_id);

    public int getMaxId();
}
