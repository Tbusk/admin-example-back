package com.seng315.finalproject.domain;


import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class UserInfoService {

    @PermitAll
    public UserInfoConfig getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final List<String> authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return new UserInfoConfig(auth.getName(), authorities);
    }

}
