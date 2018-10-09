package com.todo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class TokenUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String token)
            throws UsernameNotFoundException {
        if (token.equalsIgnoreCase("N/A")) {
            return null;
        }

        return null;
    }

//    public void setTokenManager(TokenManager tm) {
//        this.tokenManager = tm;
//    }
}
