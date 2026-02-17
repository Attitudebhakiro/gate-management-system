package com.gms.gms.constants;


import com.gms.gms.appsecurity.entity.User;
import com.gms.gms.appsecurity.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public record CurrentUser(
        UserRepository userRepository
) {
    public static String currentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
