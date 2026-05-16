package lk.tech.moonbffuser.utils;

import lk.tech.moonbffuser.dto.security.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserProvider {

    public AuthUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof AuthUser user)) {
            throw new IllegalStateException("No authenticated user found");
        }

        return user;
    }
}