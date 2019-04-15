package com.todo.controller;

import com.todo.controller.param.WXMap;
import com.todo.service.WX_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WXController {
    @Autowired
    WX_service wx_service;
    @RequestMapping(value="/wx/execute" ,method = RequestMethod.POST)
    public @ResponseBody
    Map register(@RequestBody WXMap wxMap){
        Map<String,Object> map=new HashMap<>();
        try {
            wx_service.execute(wxMap);
            map.put("status","true");
        } catch (Exception e) {
            map.put("status","false");
            map.put("message",e.getMessage());
        }
        return map;
    }
    @RequestMapping(value="/wx/test" )
    public @ResponseBody
    Map test(){
        Map<String,Object> map=new HashMap<>();
        try {
            System.out.println("------------");
            map.put("status","true");
        } catch (Exception e) {
            map.put("status","false");
            map.put("message",e.getMessage());
        }
        return map;
    }
}
