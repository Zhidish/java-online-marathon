package com.softserve.itacademy.web_security;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
    private long id;
    private String authority;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
