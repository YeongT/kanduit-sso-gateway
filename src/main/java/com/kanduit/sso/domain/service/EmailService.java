package com.kanduit.sso.domain.service;

import com.nbp.ncp.nes.ApiClient;
import com.nbp.ncp.nes.api.V1Api;
import com.nbp.ncp.nes.auth.Credentials;
import com.nbp.ncp.nes.auth.PropertiesFileCredentialsProvider;
import com.nbp.ncp.nes.marshaller.JsonMarshaller;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {
    @NonNull
    private final V1Api emailAPI;

    public EmailService(@NonNull @Value("configs/email/ncloud.properties") ClassPathResource configFile) {
        try {
            Credentials credentials = new PropertiesFileCredentialsProvider(configFile.getFile().getPath()).getCredentials();
            ApiClient apiClient = new ApiClient.ApiClientBuilder().setCredentials(credentials).setLogging(true).addMarshaller(JsonMarshaller.getInstance()).build();
            this.emailAPI = new V1Api(apiClient);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load ncloud credentials from file", e);
        }
    }

}
