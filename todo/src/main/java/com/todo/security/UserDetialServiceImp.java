package com.todo.security;

import com.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by yf on 2018/10/10.
 */

@Service
public class UserDetialServiceImp implements UserDetailsService {

    @Autowired
    UserService userService;
    LoginUser loginUser;

    public UserDetails getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        return loginUser.getUser(account);
    }

}
