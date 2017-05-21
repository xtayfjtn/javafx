package application.dao;


import application.model.Parameter;

import java.util.List;

/**
 * Created by ZQ on 2017/5/18.
 */
public interface ParameterDao {
    public int insert(Parameter parameter);

    public int update(Parameter parameter);

    public int delete(int parameter_id);

    public List<Parameter> selectAll();

    public int countAll();

    public Parameter find(int parameter_id);

    public int getMaxId();

    public void insertUpdate(Parameter parameter);

    public Parameter getInputParameter(int clause_id);
    public Parameter getOutputParameter(int clause_id);
}
