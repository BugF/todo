package com.todo.service;

import com.todo.dao.UserDao;
import com.todo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Transactional
    public void add(){
        User user=new User();
        user.setAccount("ddd");
        user.setPasw("ddd");
        user.setName("测试");
        user.setCreate_time(new Timestamp(new Date().getTime()));
        userDao.add(user);
    }

}
