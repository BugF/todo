package com.todo.security;

import com.todo.dao.UserDao;
import com.todo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LoginUser implements UserDetails {

    @Autowired
    private UserDao userDao;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    private String userName;

    public LoginUser() {
    }

    public LoginUser(String pasw, String userName, Collection<GrantedAuthority> authorities) {
        this.password = pasw;
        this.userName = userName;
        this.authorities=authorities;
    }
    public LoginUser getUser(String userName){
        System.out.println(userDao==null);
        List<User> users= userDao.getByAccount(userName);
        User user=users.get(0);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if(userName.equalsIgnoreCase("admin"))
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new LoginUser(user.getPasw(),userName,authorities);
    }

    private Collection<GrantedAuthority> authorities;

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
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
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
