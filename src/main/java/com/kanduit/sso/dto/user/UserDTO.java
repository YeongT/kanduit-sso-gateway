package com.kanduit.sso.dto.user;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserDTO {
    @NonNull
    private InfoDTO userData;

    @NonNull
    private EmailDTO emailData;

    @NonNull
    private PasswordDTO passwordData;

    @NonNull
    private PhoneDTO phoneData;

    public record InfoDTO(String displayName, LocalDate birthDate) {
    }

    public record PasswordDTO(String hashedPassword, LocalDate nextPasswordUpdate) {
    }

    public record EmailDTO(@Email String address, LocalDate verifiedAt) {
    }

    public record PhoneDTO(String countryCode, String number, LocalDate verifiedAt) {
    }
}
