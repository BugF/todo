package com.todo.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Util {
    public static String buildID() {
        return UUID.randomUUID().toString();
    }

    public static String encryptStr(String pasw) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BASE64Encoder base64en = new BASE64Encoder();
        String SecretKey = null;
        try {
            SecretKey = base64en.encode(md5.digest(pasw.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(SecretKey);
        return SecretKey;
    }

}
