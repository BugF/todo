package com.todo.service;

import com.todo.dao.BlogDao;
import com.todo.entity.Blog;
import com.todo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogDao blogDao;
    @Transactional
    public Blog create(){
        Blog obj=new Blog();
        obj.setId("BLOG_"+Util.buildID());
        obj.setPublish(false);
        obj.setCreateTime(new Timestamp(new Date().getTime()));
        obj.setModifyTime(obj.getCreateTime());
        obj.setCreator("flx");
        blogDao.create(obj);
        return obj;
    }
    @Transactional
    public Blog modify(Blog obj){
        obj.setModifyTime(new Timestamp(new Date().getTime()));
        blogDao.update(obj);
        return obj;
    }
    @Transactional
    public List<Blog> listAll(){
        return blogDao.listAll();
    }
    @Transactional
    public List<Blog> getById(String id){
        return blogDao.getById(id);
    }




}
