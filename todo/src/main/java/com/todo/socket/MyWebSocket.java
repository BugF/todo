package com.todo.socket;

import com.google.gson.Gson;
import com.todo.controller.LoginParam;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class MyWebSocket extends TextWebSocketHandler {
    // 线上人数
    private static int count;
    private static CopyOnWriteArraySet<WebSocketSession> set = new CopyOnWriteArraySet();
    private static ConcurrentHashMap<String,WebSocketSession> map=new ConcurrentHashMap<>();

    private WebSocketSession session;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        this.session = session;
        if(null==this.session.getAttributes().get("account")||
                this.session.getAttributes().get("account").toString().isEmpty()){
            map.put(this.session.getId(),this.session);
        }else {
            map.put(this.session.getAttributes().get("account").toString(),this.session);
        }
        addOnlineCount();
    }

    public void afterConnectionClosed(WebSocketSession session,CloseStatus closeStatus) {
        this.session = session;
        if(0==this.session.getAttributes().size()||
                null==this.session.getAttributes().get("account")||
                this.session.getAttributes().get("account").toString().isEmpty()){
            map.remove(this.session.getId());
        }else {
            map.remove(this.session.getAttributes().get("account").toString());
        }
//        set.remove(this.session);

        subOnlineCount();
        System.out.println("目前连接人数：" + getOnlineCount());
    }

    public void handleMessage(WebSocketSession session,WebSocketMessage<?> message){
        this.session = session;
        System.out.println("text message: "+ session.getId()+ "-"+ message.getPayload());
        Map<String,Object> obj=new HashMap<>();
        if(null==this.session.getAttributes().get("account")||
                this.session.getAttributes().get("account").toString().isEmpty()){
//            map.put(this.session.getId(),this.session);
            obj.put("tocken",this.session.getId());
        }else {
            obj.put("tocken",this.session.getAttributes().get("account").toString());
            //map.put(this.session.getAttributes().get("account").toString(),this.session);
        }
      //  obj.put("tocken",session.getId());
        obj.put("type","onLogin");
        try {
            session.sendMessage(new TextMessage(new Gson().toJson(obj)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*for(WebSocketSession ssion : set) {
            try {
                String s=ssion.getAttributes().get("SESSION_USERNAME").toString();
                System.out.println(s);
                ssion.sendMessage(new TextMessage(s));
            }catch(IOException e) {
                e.printStackTrace();
            }
        }*/
    }
    public void sendMessage(LoginParam loginParam){
        for (WebSocketSession user : set) {
            if (user.getAttributes().get("account").toString().equals(loginParam.getTocken())) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(new TextMessage(loginParam.getAccount()));
                    }
                } catch (IOException e) {

                }
            }
        }
    }
    public void sendMessage(Map loginParam){
        WebSocketSession user=map.get(loginParam.get("tocken"));
        if (user.isOpen()) {
            try {
                user.sendMessage(new TextMessage(new Gson().toJson(loginParam)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//            if (null!=user.getAttributes().get("SESSION_USERNAME")
//                    &&user.getAttributes().get("SESSION_USERNAME")
//                    .toString().equals(loginParam.get("tocken"))) {
//                try {
//                    if (user.isOpen()) {
//                        user.sendMessage(new TextMessage(new Gson().toJson(loginParam)));
//                    }
//                } catch (IOException e) {
//
//                }
//            }else if(user.getId().equals(loginParam.get("tocken"))){
//                try {
//                    if (user.isOpen()) {
//                        user.sendMessage(new TextMessage(new Gson().toJson(loginParam)));
//                    }
//                } catch (IOException e) {
//
//                }
//            }

    }


    public static int getOnlineCount() {
        return count;
    }

    public static void addOnlineCount() {
        count++;
    }

    public static void subOnlineCount() {
        count--;
    }
}
