package com.todo.controller.param;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by fulixiu on 2018-04-10.
 */
public class WX_CreateMenu {
    private List<Button> button;
    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }

    public static class Button{
        private String type;
        private String name;
        private String key;
        private String media_id;
        private List<SubButton> sub_buttons;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getMedia_id() {
            return media_id;
        }

        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }

        public List<SubButton> getSub_buttons() {
            return sub_buttons;
        }

        public void setSub_buttons(List<SubButton> sub_buttons) {
            this.sub_buttons = sub_buttons;
        }
    }
    public static class SubButton{
        private String type;
        private String name;
        private String url;
        private String key;
        private String appid;
        private String pagepath;
        private List<SubButton> sub_buttons;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPagepath() {
            return pagepath;
        }

        public void setPagepath(String pagepath) {
            this.pagepath = pagepath;
        }

        public List<SubButton> getSub_buttons() {
            return sub_buttons;
        }

        public void setSub_buttons(List<SubButton> sub_buttons) {
            this.sub_buttons = sub_buttons;
        }
    }
}
