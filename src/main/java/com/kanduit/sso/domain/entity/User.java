package com.kanduit.sso.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    /**
     * TODO/DB: Update column structure for identity verification.
     * <p>
     * The current column structure does not support identity verification.
     * Ensure changes are made to accommodate the required fields for proper verification.
     */
    @Column(unique = true)
    private String identity;

    @Column(length = 100)
    private String displayName;

    @Column
    private LocalDate birthDate;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private LocalDate emailVerifiedAt;

    @Column(length = 3)
    private String countryCode;

    @Column(length = 20)
    private String phoneNumber;

    @Column
    private LocalDate phoneVerifiedAt;

    @Column
    private String hashedPassword;

    @Column
    private LocalDate nextPasswordUpdate;

    @Column(length = 16)
    private String salt;

    public User(@Email String email) {
        this.email = email;
    }
}
