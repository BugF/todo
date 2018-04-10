package com.todo.controller;

import java.util.List;

public class SelectParam {

    private List<Object> table;
    private List<Object> field;
    private List<Object> eq;
    private List<Object> orderBy;
    private List<String> groupBy;
    private String sql;
    private Class clazz;


    public String buildSQL() throws Exception {
        checkParam();
        return "";
    }
    private Boolean checkParam() throws Exception {
        if(null==clazz)
            throw new Exception();
        if(null==table||table.size()<0)
            throw new Exception();
        return true;
    }
}
