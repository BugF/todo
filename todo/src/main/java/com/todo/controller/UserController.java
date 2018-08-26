package com.todo.controller;

import com.todo.controller.param.LoginTocken;
import com.todo.service.UserService;
import com.todo.socket.MyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    MyWebSocket myWebSocket;

    String userName=null;
    @RequestMapping(value="/todo/user" ,method = RequestMethod.GET)
    public String addUser(){

        userService.add();
        return "/index.jsp";
    }

    @RequestMapping(value="/user/loginUser" ,method = RequestMethod.POST)
    public  @ResponseBody Map  loginUser(){
        Map<String,Object> obj=new HashMap<>();
        obj.put("status","true");
        obj.put("account",userName);
        return obj;
    }
    @RequestMapping(value="/todo/login" ,method = RequestMethod.GET)
    public String phonelogin(HttpServletRequest request){
        Map<String,Object> obj=new HashMap<>();
        obj.put("type","scan");
        obj.put("tocken",request.getParameter("tocken"));
        try{
        myWebSocket.sendMessage(obj);
        }catch (Exception e){
            return "redirect:/invalidTocken.html";
        }
        return "redirect:/mobileLogin.html?tocken="+request.getParameter("tocken");
    }
    @RequestMapping(value="/todo/login" ,method = RequestMethod.POST)
    public  @ResponseBody Map phonelogin(@RequestBody LoginTocken m){
        Map<String,Object> obj=new HashMap<>();
        Map<String,Object> obj2=new HashMap<>();
        obj.put("type","ok");
        obj.put("account","flx");
        obj.put("passw0rd","111111");
        obj.put("tocken",m.getTocken());
        myWebSocket.sendMessage(obj);
        userName="flx";
        obj2.put("status","true");
        obj2.put("data","success");
        return obj2;

    }
}
