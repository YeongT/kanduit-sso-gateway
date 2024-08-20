package com.kanduit.sso.domain.model.email;

import lombok.Getter;
import lombok.NonNull;

import java.util.Optional;

@Getter
public abstract class EmailTemplateParameter {
    @NonNull
    private final String recipientName;

    @NonNull
    private final String recipientAddress;

    public EmailTemplateParameter(String recipientName, @NonNull String recipientAddress) {
        this.recipientName = Optional.ofNullable(recipientName).orElse(recipientAddress);
        this.recipientAddress = recipientAddress;
    }
}