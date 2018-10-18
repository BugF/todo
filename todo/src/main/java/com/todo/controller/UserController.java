package com.todo.controller;

import com.google.gson.Gson;
import com.todo.controller.param.LoginTocken;
import com.todo.entity.User;
import com.todo.service.UserService;
import com.todo.socket.MyWebSocket;
import com.todo.util.ConfigUtil;
import com.todo.util.wx.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    MyWebSocket myWebSocket;

//    String userName=null;
    @RequestMapping(value="/todo/user" ,method = RequestMethod.GET)
    public String addUser(){

        userService.add();
        return "/index.jsp";
    }

    @RequestMapping(value="/login" ,method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        HttpServletRequest request
                        ){

        if (error != null) {
            request.setAttribute("msg","用户名或密码错误");
        }
        if (logout != null) {
            request.setAttribute("msg","退出成功");
        }
        return "/login.jsp";

    }

    @RequestMapping(value="/user/register" ,method = RequestMethod.POST)
    public @ResponseBody Map register(@RequestBody User user){
        Map<String,Object> map=new HashMap<>();
        try {
            userService.registerWithWxOpenid(user);
            map.put("status","true");
        } catch (Exception e) {
            map.put("status","false");
            map.put("message",e.getMessage());
        }
        return map;
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
        obj.put("account",SecurityContextHolder.getContext().getAuthentication().getName());
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
        if(map.containsKey("errcode")){
            return "redirect:/invalidTocken.html";
        }
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
        request.setAttribute("openid",map.get("openid"));
        request.setAttribute("tocken",tocken);
        return "/mobileLogin.jsp";
    }
    @RequestMapping(value="/user/WXopenId" ,method = RequestMethod.GET)
    public String WXopenId(HttpServletRequest request){
        String code=request.getParameter("code");
        String state=request.getParameter("state");

        String data=HttpUtil.doGet("https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" +ConfigUtil.getContextProperty("APPID")+"" +
                "&secret="+ ConfigUtil.getContextProperty("APPSECRET")+"" +
                "&code="+code+
                "&grant_type=authorization_code");
        System.out.println("*************data="+data);
        Map<String,Object> map=new Gson().fromJson(data,Map.class);
        if(map.containsKey("errcode")){
            return "redirect:/invalidTocken.html";
        }
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
        request.setAttribute("openid",map.get("openid"));
        request.setAttribute("tocken",tocken);
        return "/mobileLogin.jsp";
    }
    @RequestMapping(value="/user/bindWX" ,method = RequestMethod.GET)
    public String bindWX(HttpServletRequest request){
        String code=request.getParameter("code");
        String data=HttpUtil.doGet("https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" +ConfigUtil.getContextProperty("APPID")+"" +
                "&secret="+ ConfigUtil.getContextProperty("APPSECRET")+"" +
                "&code="+code+
                "&grant_type=authorization_code");
        System.out.println("*************data="+data);
        Map<String,Object> map=new Gson().fromJson(data,Map.class);
        if(map.containsKey("errcode")){
            return "redirect:/invalidTocken.html";
        }
        System.out.println("*************map="+map.get("openid"));
        request.setAttribute("openid",map.get("openid"));
        if(!userService.wxIsBind(map.get("openid").toString())){
            System.out.println("isBind:= false");
            return "/mobileRegister.jsp";
        }
        else{
            System.out.println("isBind:= true");
            return "/mobileUserInfo.jsp";

        }
    }
    @RequestMapping(value="/todo/login" ,method = RequestMethod.POST)
    public  @ResponseBody Map phonelogin(@RequestBody LoginTocken m){
        Map<String,Object> obj=new HashMap<>();
        Map<String,Object> obj2=new HashMap<>();

        List<User> us=userService.getByOpenId(m.getOpenid());
        if(null!=us && us.size()>0){
            obj.put("type","login-ok");
            obj.put("account",us.get(0).getAccount());
            obj.put("password",us.get(0).getPasw());
            obj.put("tocken",m.getTocken());
            myWebSocket.sendMessage(obj);
            obj2.put("status","true");
            obj2.put("data","success");
            return obj2;
        }else {
            obj2.put("status","false");
            obj2.put("data","出错");
            return obj2;
        }



    }


}
