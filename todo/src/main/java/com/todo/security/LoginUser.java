package com.todo.security;

import com.todo.dao.UserDao;
import com.todo.entity.User;
import com.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LoginUser implements UserDetails {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    private String pasw;
    private String userName;

    public LoginUser() {
    }

    public LoginUser(String pasw, String userName) {
        this.pasw = pasw;
        this.userName = userName;
       // this.authorities=
    }
    public LoginUser getUser(String userName){
        User user= userService.getByAccount(userName).get(0);
        User user2= userDao.getByAccount(userName).get(0);
        this.pasw=user.getPasw();
        this.userName=userName;
        return this;
    }

    private Collection<GrantedAuthority> authorities;

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getPasw() {
        return pasw;
    }

    public void setPasw(String pasw) {
        this.pasw = pasw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return pasw;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
