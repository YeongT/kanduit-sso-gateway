package com.kanduit.sso.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserInitRequestDTO(@Valid @NotBlank @Email String email) {
}
