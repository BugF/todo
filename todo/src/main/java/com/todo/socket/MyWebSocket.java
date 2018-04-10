package com.todo.socket;

import com.todo.controller.LoginParam;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

public class MyWebSocket extends TextWebSocketHandler {
    // 线上人数
    private static int count;
    private static CopyOnWriteArraySet<WebSocketSession> set = new CopyOnWriteArraySet();
    private WebSocketSession session;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        this.session = session;
        try{
            set.add(this.session);
        }catch(Exception e) {
            e.printStackTrace();
        }
        this.addOnlineCount();
        System.out.println("目前连接人数：" + getOnlineCount());
    }

    public void afterConnectionClosed(WebSocketSession session,CloseStatus closeStatus) {
        this.session = session;
        set.remove(this.session);
        subOnlineCount();
        System.out.println("目前连接人数：" + getOnlineCount());
    }

    public void handleMessage(WebSocketSession session,WebSocketMessage<?> message){
        System.out.println("text message: "+ session.getId()+ "-"+ message.getPayload());
        try {
            session.sendMessage(new TextMessage(session.getId()));
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
            if (user.getId().equals(loginParam.getTocken())) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(new TextMessage(loginParam.getAccount()));
                    }
                } catch (IOException e) {

                }
            }
        }
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
