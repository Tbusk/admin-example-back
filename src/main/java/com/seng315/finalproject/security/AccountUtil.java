package com.seng315.finalproject.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AccountUtil {

    public static String getUsername() {
        try {
            Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user instanceof UserDetails) {
                return ((UserDetails) user).getUsername();
            }
        } catch (NullPointerException e) {

            System.out.println("User not logged in.");
        }
        return "Anonymous";
    }
}
