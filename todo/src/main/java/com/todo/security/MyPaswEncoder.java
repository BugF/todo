package com.todo.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

public class MyPaswEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex("admin".getBytes()));
    }
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        if(charSequence.length()==s.length())
        return s.equals(charSequence.toString());
        return s.equalsIgnoreCase(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
    }
}
