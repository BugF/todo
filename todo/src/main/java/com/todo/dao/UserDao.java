package com.todo.dao;

import com.todo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    void add(User user);
    List<User> getByOpenId(String openId);
    List<User> getByAccount(String openId);
    int accountIsBind(String account);
    int openidIsBind(String wxOpenId);
    int bingWxOpenId(User user);
    void registerWithWxOpenid(User user);
}
