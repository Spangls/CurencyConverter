package ru.mpt.convertor.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, EMPLOYEE, ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
