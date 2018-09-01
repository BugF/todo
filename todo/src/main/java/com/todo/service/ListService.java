package com.todo.service;

import com.todo.dao.ListDao;
import com.todo.entity.List;
import com.todo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class ListService {
    @Autowired
    private ListDao listDao;
    @Transactional
    public List create(String title){
        List obj=new List();
        obj.setId("LIST_"+Util.buildID());
        obj.setTitle(title);
        obj.setCreateTime(new Timestamp(new Date().getTime()));
        listDao.create(obj);
        return obj;
    }
    @Transactional
    public List update(List list){
        listDao.update(list);
        return list;
    }
    @Transactional
    public void delete(List list){
        listDao.delete(list);
    }
    @Transactional
    public java.util.List<List> listAll(){
        return listDao.listAll();
    }
    public java.util.List<List> getById(String id){
        return listDao.getById(id);
    }


}
