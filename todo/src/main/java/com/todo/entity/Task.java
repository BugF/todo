package com.todo.entity;

import java.sql.Timestamp;

/**
 * Created by fulixiu on 2017-12-20.
 *
 * 代办的任务实体类
 *
 *
 */
public class Task {
    private String id;
    private String setId;
    private String title;
    private String note;
    private String creator;
    private Boolean beOver;
    private Boolean beDelete;
    private Timestamp createTime;
    private Timestamp alarmTime;

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getBeOver() {
        return beOver;
    }

    public void setBeOver(Boolean beOver) {
        this.beOver = beOver;
    }

    public Boolean getBeDelete() {
        return beDelete;
    }

    public void setBeDelete(Boolean beDelete) {
        this.beDelete = beDelete;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Timestamp alarmTime) {
        this.alarmTime = alarmTime;
    }
}
