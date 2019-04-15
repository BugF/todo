package com.todo.service;

import com.google.gson.Gson;
import com.todo.controller.param.WXMap;
import com.todo.controller.param.WX_CreateMenu;
import com.todo.util.wx.HttpUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WX_service {
    private String createMenuURL
            ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    private String getMenuUrl="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    public String execute(WXMap wxMap) throws Exception {
        //String tocken=WXTocken.getTocken();
        String tocken="";
        if(wxMap.getMethod().equalsIgnoreCase("buildMenu")){
           return HttpUtil.doPost(createMenuURL.replace("ACCESS_TOKEN",tocken),wxMap.getParams());
        }else if(wxMap.getMethod().equalsIgnoreCase("getMenu")){
            return HttpUtil.doPost(getMenuUrl.replace("ACCESS_TOKEN",tocken),wxMap.getParams());
        }
        else return "";
    }
    public static void main(String[] args) {
        WX_CreateMenu createMenu=new WX_CreateMenu();
        WX_CreateMenu.Button btn=new WX_CreateMenu.Button();
        btn.setName("t1");
        WX_CreateMenu.SubButton subButton=new WX_CreateMenu.SubButton();
        WX_CreateMenu.SubButton subButton2=new WX_CreateMenu.SubButton();
        List<WX_CreateMenu.SubButton> ls=new ArrayList<>();
        List<WX_CreateMenu.SubButton> ls2=new ArrayList<>();
        subButton.setName("sub1");
        subButton2.setName("sub2");
        ls2.add(subButton2);
        subButton.setSub_buttons(ls2);
        ls.add(subButton);
        btn.setSub_buttons(ls);
        List<WX_CreateMenu.Button> ls22=new ArrayList<>();
        ls22.add(btn);
        createMenu.setButton(ls22);
        System.out.println(new Gson().toJson(createMenu));

    }



}
