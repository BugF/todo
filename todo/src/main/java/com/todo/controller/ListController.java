package com.todo.controller;

import com.todo.service.ListService;
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
public class ListController {
    @Autowired
    private ListService listService;
    @RequestMapping(value="list/create" ,method = RequestMethod.POST)
    public @ResponseBody Map insert(@RequestBody String title){
        ModelAndView modelAndView=new ModelAndView("json");
        Map<String,Object> map=new HashMap();
        map.put("status","true");
        map.put("datas",listService.create(title));
        modelAndView.addObject(map);
        System.out.println("ddd");
        return map;
    }
    @RequestMapping(value="list/update" ,method = RequestMethod.POST)
    public @ResponseBody Map insert(@RequestBody com.todo.entity.List list){
        ModelAndView modelAndView=new ModelAndView("json");
        Map<String,Object> map=new HashMap();
        map.put("status","true");
        map.put("datas",listService.update(list));
        modelAndView.addObject(map);
        System.out.println("ddd");
        return map;
    }
    @RequestMapping(value="list/delete" ,method = RequestMethod.POST)
    public @ResponseBody Map delete(@RequestBody com.todo.entity.List list){
        ModelAndView modelAndView=new ModelAndView("json");
        Map<String,Object> map=new HashMap();
        listService.delete(list);
        map.put("status","true");
        modelAndView.addObject(map);
        System.out.println("ddd");
        return map;
    }
    @RequestMapping(value="list/listAll" ,method = RequestMethod.POST)
    public @ResponseBody Map listAll(){
        ModelAndView modelAndView=new ModelAndView("json");
        Map<String,Object> map=new HashMap();
        map.put("status","true");
        map.put("datas",listService.listAll());
        modelAndView.addObject(map);
        return map;
    } @RequestMapping(value="list/getById" ,method = RequestMethod.POST)
    public @ResponseBody Map getById(@RequestBody String id){
        ModelAndView modelAndView=new ModelAndView("json");
        Map<String,Object> map=new HashMap();
        List<com.todo.entity.List> ls=listService.getById(id);
        map.put("status","true");
        map.put("datas",ls.size()==1?ls.get(0):null);
        modelAndView.addObject(map);
        return map;
    }
  /*  @RequestMapping(value="task/listByUser" ,method = RequestMethod.POST)
    public @ResponseBody Map listByCreator(@RequestBody String user){
        Map<String,Object> map=new HashMap();
        List<Task> list= taskService.listByCreator(user);
        map.put("status","true");
        map.put("data",list);
        System.out.println("ddd");
        return map;
    }*/
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
