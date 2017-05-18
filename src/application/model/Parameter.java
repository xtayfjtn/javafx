package application.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by ZQ on 2017/5/17.
 */
public class Parameter {
    private int parameter_id;
    private String category = "hello";
    private String pType = "type1";
    private String parameterName = "parameterName";
    private String description = "这是描述";
    private String pOrder = "order1";
    private String clause_id;

    @XmlElement
    public int getParameter_id() {
        return parameter_id;
    }

    @XmlElement
    public void setParameter_id(int parameter_id) {
        this.parameter_id = parameter_id;
    }

    @XmlElement
    public String getCategory() {
        return category;
    }

    @XmlElement
    public void setCategory(String category) {
        this.category = category;
    }

    @XmlElement
    public String getpType() {
        return pType;
    }

    @XmlElement
    public void setpType(String pType) {
        this.pType = pType;
    }

    @XmlElement
    public String getParameterName() {
        return parameterName;
    }

    @XmlElement
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public String getpOrder() {
        return pOrder;
    }

    @XmlElement
    public void setpOrder(String pOrder) {
        this.pOrder = pOrder;
    }

    @XmlElement
    public String getClause_id() {
        return clause_id;
    }

    @XmlElement
    public void setClause_id(String clause_id) {
        this.clause_id = clause_id;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "parameter_id=" + parameter_id +
                ", category='" + category + '\'' +
                ", pType='" + pType + '\'' +
                ", parameterName='" + parameterName + '\'' +
                ", description='" + description + '\'' +
                ", pOrder='" + pOrder + '\'' +
                ", clause_id='" + clause_id + '\'' +
                '}';
    }
}
