package com.kanduit.sso.domain.service;

import com.kanduit.sso.domain.enums.Favicon;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.EnumMap;

@Service
public class FaviconService {
    private final EnumMap<Favicon, @NonNull BufferedImage> imageResources;

    @Autowired
    public FaviconService(@Value("favicons/") @NonNull String rootDirectory) {
        imageResources = new EnumMap<>(Favicon.class);
        for (Favicon favicon : Favicon.values()) {
            ClassPathResource resource = new ClassPathResource(rootDirectory + favicon.getFileName());
            try {
                imageResources.put(favicon, ImageIO.read(resource.getURL()));
            } catch (IOException e) {
                throw new RuntimeException("Could not find or read the file: ", e);
            }
        }
    }

    public String getBase64EncodedImage(@NonNull Favicon favicon) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(imageResources.get(favicon), favicon.getExtension(), byteArrayOutputStream);
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (IOException ex) {
            throw new RuntimeException("Error encoding image to Base64: " + ex.getMessage(), ex);
        }
    }
}