package com.kanduit.sso.domain.enums;

import lombok.Getter;
import lombok.NonNull;

@Getter
public enum Favicon {
    APPLE_ICON("apple-icon.png", Extension.PNG),
    FAVICON_16("favicon-16x16.png", Extension.PNG),
    FAVICON_32("favicon-32x32.png", Extension.PNG),
    FAVICON_96("favicon-96x96.png", Extension.PNG);

    @NonNull
    private final String fileName;

    @NonNull
    private final String extension;

    Favicon(@NonNull String fileName, @NonNull Extension extensionEnum) {
        this.fileName = fileName;
        this.extension = extensionEnum.getExtension();
    }
}