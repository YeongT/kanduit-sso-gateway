package com.kanduit.sso.config;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NonNull;

import java.util.Optional;

@Getter
public enum APIDeploymentType {
    HEROKU("heroku"),
    PRODUCTION("api"),
    DEVELOPMENT("dev"),
    LOCALHOST(null);

    @NonNull
    private final String alias;

    @Valid
    APIDeploymentType(String alias) {
        this.alias = Optional.ofNullable(alias).orElse("");
    }
}