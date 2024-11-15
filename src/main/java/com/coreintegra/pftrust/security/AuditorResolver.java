package com.coreintegra.pftrust.security;

import com.coreintegra.pftrust.entities.administration.User;
import com.coreintegra.pftrust.services.administration.UserService;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;


public class AuditorResolver implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null) {
            return Optional.empty();
        }

        if(authentication.getPrincipal() instanceof User){
            User user = (User) authentication.getPrincipal();
            return Optional.of(user);
        }

        return Optional.empty();
    }

}
