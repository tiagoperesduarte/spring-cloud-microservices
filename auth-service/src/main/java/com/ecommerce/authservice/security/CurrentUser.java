package com.ecommerce.authservice.security;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CurrentUser implements UserDetails {
    private final String id;
    private final String username;

    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public CurrentUser(String id, String username) {
        this(id, username, null);
    }

    public CurrentUser(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;

        this.authorities = Collections.emptySet();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public static CurrentUser fromContext() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (CurrentUser) authentication.getPrincipal();
        } catch (Exception ex) {
            throw new InvalidJwtException("Expired or invalid JWT token");
        }
    }
}
