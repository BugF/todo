package com.todo.controller;

import com.todo.entity.Blog;
import com.todo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value="blog/create" ,method = RequestMethod.POST)
    public @ResponseBody
    Map create(){
        ModelAndView modelAndView=new ModelAndView("json");
        Map<String,Object> map=new HashMap();
        map.put("status","true");
        map.put("datas",blogService.create());
        modelAndView.addObject(map);
        return map;
    }
    @RequestMapping(value="blog/modify" ,method = RequestMethod.POST)
    public @ResponseBody Map modify(@RequestBody Blog blog){
        ModelAndView modelAndView=new ModelAndView("json");
        Map<String,Object> map=new HashMap();
        map.put("status","true");
        map.put("datas",blogService.modify(blog));
        modelAndView.addObject(map);
        System.out.println("ddd");
        return map;
    }
    @RequestMapping(value="blog/list" ,method = RequestMethod.POST)
    public @ResponseBody Map listAll(){
        ModelAndView modelAndView=new ModelAndView("json");
        Map<String,Object> map=new HashMap();
        map.put("status","true");
        map.put("datas",blogService.listAll());
        modelAndView.addObject(map);
        System.out.println("ddd");
        return map;
    }
    @RequestMapping(value="blog/getById" ,method = RequestMethod.POST)
    public @ResponseBody Map getById(@RequestBody String id){
        System.out.println(id);
        ModelAndView modelAndView=new ModelAndView("json");
        Map<String,Object> map=new HashMap();
        map.put("status","true");
        map.put("datas",blogService.getById(id));
        modelAndView.addObject(map);
        return map;
    }
}
