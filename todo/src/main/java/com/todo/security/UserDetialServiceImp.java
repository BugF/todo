package com.todo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by yf on 2018/10/10.
 */

public class UserDetialServiceImp implements UserDetailsService {

    LoginUser loginUser;

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        UserDetails userDetails= null;
        try {
            userDetails = loginUser.getUser(account);
        } catch (Exception e) {
            throw  new UsernameNotFoundException("没有此用户");
        }
        return userDetails;
    }

}
