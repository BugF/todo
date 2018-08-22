package com.todo.dao;

import com.todo.entity.WX_User;
import org.springframework.stereotype.Repository;

@Repository
public interface WX_UserDao {
    void add(WX_User user);
    WX_User find(String openid);
}
