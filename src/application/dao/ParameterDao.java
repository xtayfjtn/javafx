package application.dao;


import application.model.Parameter;

import java.util.List;

/**
 * Created by ZQ on 2017/5/18.
 */
public interface ParameterDao {
    public int insert(Parameter parameter);

    public int update(Parameter parameter);

    public int delete(int parameterID);

    public List<Parameter> selectAll();

    public int countAll();

    public Parameter find(int parameterID);
}
