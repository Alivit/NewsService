package ru.clevertec.news.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, SUBSCRIBER, JOURNALIST;

    @Override
    public String getAuthority() {
        return name();
    }
}
