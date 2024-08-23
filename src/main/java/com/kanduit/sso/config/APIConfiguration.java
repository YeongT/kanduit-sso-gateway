package com.kanduit.sso.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "api")
@PropertySource("classpath:configs/api_config.properties")
public class APIConfiguration {
    @NotNull
    private Integer port;

    @NotNull
    @NotBlank
    private String host;

    @NotNull
    private APIDeploymentType deployment;

    public @NonNull String createAPIUrlString(@NonNull String uri) {
        if (deployment == APIDeploymentType.LOCALHOST)
            return "http://localhost:%d/%s".formatted(port, uri);
        return "https://" + deployment.getAlias() + "." + host + "/" + uri;
    }

    public @NonNull String getCorpUrl() {
        return "https://corp." + host + "/";
    }
}
