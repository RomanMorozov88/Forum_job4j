package ru.job4j.forum.service;

import org.springframework.security.core.GrantedAuthority;

/**
 * Т.к сложные роли тут не нужны-
 * напмсана этот простой enum.
 */
public enum Roles implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
