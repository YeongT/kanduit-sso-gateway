package com.kanduit.sso.domain.model.email;

import com.kanduit.sso.dto.response.APIResponseStatus;
import com.kanduit.sso.exception.APIException;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public interface EmailTemplate {
    @NonNull
    Boolean getIsMailForAdvertising();

    @NonNull
    @NotBlank
    String getSenderAddress();

    @NonNull
    @NotBlank
    String getSenderName();

    @NonNull
    @NotBlank
    String getSubject();

    @NonNull
    @NotBlank
    String getTemplateContent();

    default String readTemplateFile(@NonNull String templateFilePath) {
        ClassPathResource resource = new ClassPathResource(templateFilePath);
        try (InputStream inputStream = resource.getInputStream();
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
            return contentBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("error occurred while reading template file", e);
        }
    }

    @NonNull
    <T extends EmailTemplateParameter> String getBody(T t) throws APIException;

    @NonNull
    default APIException createInvalidParameterException(@NonNull String requiredClassName, @NonNull String suppliedClassName) {
        return new APIException(APIResponseStatus.MAIL_TEMPLATE_CREATE_FAILED, new RuntimeException("Invalid parameter provided to getBody method. Please ensure that the correct parameter type is used")) {
            public APIException addComment(@NonNull String comment) {
                this.getBody().addComment(comment);
                return this;
            }
        }.addComment("Expected a parameter of type '{%s}' but received '{%s}'.".formatted(requiredClassName, suppliedClassName));
    }
}