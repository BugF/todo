package com.todo.entity;

import java.sql.Timestamp;

/**
 * Created by fulixiu on 2017-12-20.
 */
public class List {
    private String id;
    private String title;
    private Timestamp createTime;
    private int allTaskCount;
    private int overTaskCount;


    public int getAllTaskCount() {
        return allTaskCount;
    }

    public void setAllTaskCount(int allTaskCount) {
        this.allTaskCount = allTaskCount;
    }

    public int getOverTaskCount() {
        return overTaskCount;
    }

    public void setOverTaskCount(int overTaskCount) {
        this.overTaskCount = overTaskCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
