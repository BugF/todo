package com.todo.dao;

import com.todo.entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {
    void create(Blog blog);
    void update(Blog blog);
    void delete(Blog blog);
    List<Blog> listAll();
    List<Blog> getById(String blog);
    List<Blog> listPublish(String creator);
    List<Blog> listDraft(String creator);


}
