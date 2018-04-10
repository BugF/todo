package com.todo.service;

import com.todo.dao.TaskDao;
import com.todo.entity.Task;
import com.todo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskDao taskDao;
    @Transactional
    public void insert(Task obj){
        obj.setId("TASK_"+Util.buildID());
        obj.setBeOver(false);
        obj.setBeDelete(false);
        obj.setCreateTime(new Timestamp(new Date().getTime()));
        obj.setCreator("flx");
        taskDao.insert(obj);
    }
    @Transactional
    public List<Task> listByCreator(String creator){
        return taskDao.listByCreator(creator);
    }


}
