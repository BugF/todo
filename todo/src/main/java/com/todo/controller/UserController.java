package com.todo.controller;

import com.todo.service.UserService;
import com.todo.socket.MyWebSocket;
import com.todo.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    MyWebSocket myWebSocket;

    @RequestMapping(value="/todo/user" ,method = RequestMethod.GET)
    public String addUser(){

        userService.add();
        return "/index.jsp";
    }
    @RequestMapping(value="/todo/login" ,method = RequestMethod.GET)
    public void phonelogin(HttpServletRequest request){
        System.out.println(ConfigUtil.getContextProperty("id"));
        LoginParam user=new LoginParam();
        user.setAccount("FLX");
        user.setTocken(request.getParameter("tocken"));
        myWebSocket.sendMessage(user);
    }
}
