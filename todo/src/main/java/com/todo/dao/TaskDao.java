package com.todo.dao;

import com.todo.entity.Task;

import java.sql.Timestamp;
import java.util.List;

public interface TaskDao {
    /**
     * 添加
     * @param task
     */
    void insert(Task task);
    void create(Task task);
    void setOver(Task task);

    /**
     * 列出指人指定时间段
     * @param creator
     * @param minTime
     * @param maxTime
     */
    //void listByCreatorWithinTime(String creator, Timestamp minTime,Timestamp maxTime);

    /**
     * 列出指人
     * @param creator
     */
    List<Task> list(Task task);
    void delete(Task task);
    List<Task> listByCreator(String creator);
    List<Task> listByCreatorAndTime(String creator, Timestamp min,Timestamp max);
}
