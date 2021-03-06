package com.jointsky.esper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuZifan on 2018/2/2.
 * 定义strom的Tuple的类型
 */
public class TupleTypeDescriptor implements Serializable{
    private final Map<String, String> fieldTypes;

    public TupleTypeDescriptor(Map<String, String> fieldTypes)
    {
        this.fieldTypes = new HashMap<String, String>(fieldTypes);
    }

    public String getFieldType(String fieldName)
    {
        return fieldTypes.get(fieldName);
    }
}
