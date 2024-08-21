package com.kanduit.sso.domain.model.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;

import java.util.Optional;

@Getter
public abstract class EmailTemplateParameter {
    @NonNull
    @NotBlank
    private final String recipientName;

    @NonNull
    @NotBlank
    @Email
    private final String recipientAddress;

    public EmailTemplateParameter(String recipientName, @NonNull String recipientAddress) {
        this.recipientName = Optional.ofNullable(recipientName).orElse(recipientAddress);
        this.recipientAddress = recipientAddress;
    }
}