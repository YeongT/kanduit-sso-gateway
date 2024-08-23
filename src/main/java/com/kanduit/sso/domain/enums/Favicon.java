package com.kanduit.sso.domain.enums;

import lombok.Getter;
import lombok.NonNull;

@Getter
public enum Favicon {
    APPLE_ICON("apple-icon.png", "png"),
    FAVICON_16("favicon-16x16.png", "png"),
    FAVICON_32("favicon-32x32.png", "png"),
    FAVICON_96("favicon-96x96.png", "png");

    @NonNull
    private final String fileName;

    @NonNull
    private final String extension;

    Favicon(@NonNull String fileName, @NonNull String extension) {
        this.fileName = fileName;
        this.extension = extension;
    }
}
