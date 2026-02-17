package com.gms.gms.appsecurity.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("securityService")
public class SecurityExpressionService {
    public String currentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public boolean isOwner(String username) {
        return currentUsername().equals(username);
    }

    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .anyMatch(a ->
                        a.getAuthority().equals("ROLE_ADMIN") ||
                                a.getAuthority().equals("ROLE_SUPER_ADMIN")
                );
    }

    public boolean isEmployer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .anyMatch(a ->
                                a.getAuthority().equals("ROLE_ADMIN") ||
                                a.getAuthority().equals("ROLE_SUPER_ADMIN") ||
                                a.getAuthority().equals("EMPLOYER")
                );
    }
}
