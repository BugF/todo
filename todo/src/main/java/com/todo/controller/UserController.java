package com.todo.controller;

import com.google.gson.Gson;
import com.todo.controller.param.LoginTocken;
import com.todo.service.UserService;
import com.todo.socket.MyWebSocket;
import com.todo.util.ConfigUtil;
import com.todo.util.wx.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @RequestMapping(value="/user/register" ,method = RequestMethod.GET)
    public @ResponseBody Map register(){
        Map<String,Object> obj=new HashMap<>();

        userService.add();
        return null;
    }
    @RequestMapping(value="user/isLogin" ,method = RequestMethod.GET)
    public @ResponseBody Map isLogin(){
        Map<String,Object> obj=new HashMap<>();
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        if("anonymousUser".equalsIgnoreCase(userName)){
            obj.put("status","false");
        }else{
            obj.put("status","true");
            obj.put("account",userName);
        }
        return obj;
    }
    @RequestMapping(value="user/getUserLoginInfo" ,method = RequestMethod.GET)
    public @ResponseBody Map getUserLoginInfo(){
        Map<String,Object> obj=new HashMap<>();
        String userName=SecurityContextHolder.getContext().getAuthentication().getName();
        if("anonymousUser".equalsIgnoreCase(userName)){
            obj.put("status","true");
        }else{
            obj.put("status","true");
            obj.put("account",userName);
        }
        return obj;
    }

    @RequestMapping(value="/user/loginUser" ,method = RequestMethod.POST)
    public  @ResponseBody Map  loginUser(){
        Map<String,Object> obj=new HashMap<>();
        obj.put("status","true");
        obj.put("account",userName);
        return obj;
    }
    @RequestMapping(value="/user/login" ,method = RequestMethod.GET)
    public String phonelogin(HttpServletRequest request){
        String code=request.getParameter("code");
        String state=request.getParameter("state");

        String data=HttpUtil.doGet("https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" +ConfigUtil.getContextProperty("APPID")+"" +
                "&secret="+ ConfigUtil.getContextProperty("APPSECRET")+"" +
                "&code="+code+
                "&grant_type=authorization_code");
        System.out.println("*************data="+data);
        Map<String,Object> map=new Gson().fromJson(data,Map.class);
        String tocken=state.indexOf("#")>0?state.substring(0,state.indexOf("#")):state;
        System.out.println("**************tocken="+tocken);
        Map<String,Object> obj=new HashMap<>();
        obj.put("type","scan");
        obj.put("tocken",tocken);
        try{
        myWebSocket.sendMessage(obj);
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/invalidTocken.html";
        }
        request.setAttribute("openid",obj.get("openid"));
        request.setAttribute("tocken",tocken);
        return "/mobileLogin.jsp";
    }
    @RequestMapping(value="/todo/login" ,method = RequestMethod.POST)
    public  @ResponseBody Map phonelogin(@RequestBody LoginTocken m){
        Map<String,Object> obj=new HashMap<>();
        Map<String,Object> obj2=new HashMap<>();
        obj.put("type","login-ok");
        obj.put("account","admin");
        obj.put("password","admin");
        obj.put("tocken",m.getTocken());
        myWebSocket.sendMessage(obj);
        userName="flx";
        obj2.put("status","true");
        obj2.put("data","success");
        return obj2;

    }


}
