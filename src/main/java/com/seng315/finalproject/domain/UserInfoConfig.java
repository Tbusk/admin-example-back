package com.seng315.finalproject.domain;

import jakarta.annotation.Nonnull;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;

public class UserInfoConfig {

    @Nonnull
    private final String name;
    @Getter
    @Nonnull
    private final Collection<String> authorities;

    public UserInfoConfig(@Nonnull String name, Collection<String> authorities) {
        this.name = name;
        this.authorities = Collections.unmodifiableCollection(authorities);
    }

    @Nonnull
    public String getName() {
        return name;
    }

}
