package com.todo.controller.param;

/**
 * Created by yf on 2018/8/22.
 */
public class LoginTocken {
    private String tocken;
    private String openid;
    private String type;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
