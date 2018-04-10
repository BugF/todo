package com.todo.dao;

import com.todo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    void add(User user);
}
