package com.todo.dao;

import com.todo.entity.List;

public interface ListDao {
    void create(List task);
    void update(List task);
    void delete(List task);
    java.util.List<List> listAll();
    java.util.List<List> getById(String id);
}
