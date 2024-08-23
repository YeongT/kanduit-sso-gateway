package com.kanduit.sso.domain.enums;

import lombok.Getter;
import lombok.NonNull;

@Getter
public enum Extension {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    BMP("bmp"),
    GIF("gif"),
    WBMP("wbmp");

    @NonNull
    private final String extension;

    Extension(@NonNull String extension) {
        this.extension = extension;
    }
}