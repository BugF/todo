package com.todo.controller;

import com.todo.entity.Task;
import com.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;
    @RequestMapping(value="task/insert" ,method = RequestMethod.POST)
    public @ResponseBody Map insert(@RequestBody Task obj){
        System.out.println(null==obj);
        ModelAndView modelAndView=new ModelAndView("json");
        Map<String,String> map=new HashMap();
        taskService.insert(obj);
        map.put("status","true");
        modelAndView.addObject(map);
        System.out.println("ddd");
        return map;
    }
    @RequestMapping(value="task/setOver" ,method = RequestMethod.POST)
    public @ResponseBody Map setOver(@RequestBody Task obj){
        System.out.println(null==obj);
        ModelAndView modelAndView=new ModelAndView("json");
        Map<String,String> map=new HashMap();
        taskService.setOver(obj);
        map.put("status","true");
        modelAndView.addObject(map);
        System.out.println("ddd");
        return map;
    }
    @RequestMapping(value="task/create" ,method = RequestMethod.POST)
    public @ResponseBody Map create(@RequestBody Task obj){
        ModelAndView modelAndView=new ModelAndView("json");
        Map<String,Object> map=new HashMap();
        map.put("status","true");
        map.put("datas", taskService.create(obj));
        modelAndView.addObject(map);
        return map;
    }
    @RequestMapping(value="task/listByUser" ,method = RequestMethod.POST)
    public @ResponseBody Map listByCreator(@RequestBody String user){
        Map<String,Object> map=new HashMap();
        List<Task> list= taskService.listByCreator(user);
        map.put("status","true");
        map.put("data",list);
        System.out.println("ddd");
        return map;
    }
    @RequestMapping(value="task/list" ,method = RequestMethod.POST)
    public @ResponseBody Map list(@RequestBody Task task){
        Map<String,Object> map=new HashMap();
        List<Task> list= taskService.list(task);
        map.put("status","true");
        map.put("datas",list);
        System.out.println("ddd");
        return map;
    }
    @RequestMapping(value="task/delete" ,method = RequestMethod.POST)
    public @ResponseBody Map delete(@RequestBody Task task){
        Map<String,Object> map=new HashMap();
       taskService.delete(task);
        map.put("status","true");
        return map;
    }
   /* @RequestMapping(value="task/listMyToday" ,method = RequestMethod.POST)
    public @ResponseBody Map listMyToday(@RequestBody String user){
        Map<String,Object> map=new HashMap();
        List<Task> list= taskService.listMyToday(user);
        map.put("status","true");
        map.put("data",list);
        System.out.println("ddd");
        return map;
    }*/
}
