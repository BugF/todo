package com.todo.util;

import org.springframework.util.DigestUtils;

import java.util.UUID;

public class Util {
    public static String buildID() {
        return UUID.randomUUID().toString();
    }

    public static String encryptPasw(String pasw){
        return DigestUtils.md5DigestAsHex(pasw.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(encryptPasw("pasw"));
    }

}
