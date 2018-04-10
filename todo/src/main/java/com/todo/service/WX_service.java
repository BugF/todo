package com.todo.service;

import com.google.gson.Gson;
import com.todo.controller.param.WX_CreateMenu;

import java.util.ArrayList;
import java.util.List;

public class WX_service {
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
