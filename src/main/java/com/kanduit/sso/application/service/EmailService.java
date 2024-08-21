package com.kanduit.sso.application.service;

import com.kanduit.sso.domain.model.email.EmailTemplateParameter;
import com.kanduit.sso.dto.email.EmailSendRequest;
import com.kanduit.sso.exception.APIException;
import com.nbp.ncp.nes.ApiClient;
import com.nbp.ncp.nes.ApiResponse;
import com.nbp.ncp.nes.api.V1Api;
import com.nbp.ncp.nes.auth.Credentials;
import com.nbp.ncp.nes.auth.PropertiesFileCredentialsProvider;
import com.nbp.ncp.nes.marshaller.JsonMarshaller;
import com.nbp.ncp.nes.model.EmailSendRequestRecipients;
import com.nbp.ncp.nes.model.EmailSendResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {
    @NonNull
    private static final String X_NCP_LANG = "ko-KR";

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

    public EmailSendRequestRecipients createRecipient(EmailTemplateParameter parameter) {
        return new EmailSendRequestRecipients().address(parameter.getRecipientAddress()).name(parameter.getRecipientName());
    }

    public <T extends EmailTemplateParameter> ApiResponse<EmailSendResponse> send(@NonNull EmailSendRequest requestType, @NonNull T parameter) throws APIException {
        return emailAPI.mailsPost(new com.nbp.ncp.nes.model.EmailSendRequest()
                        .advertising(requestType.getTemplate().getIsMailForAdvertising())
                        .addRecipientsItem(createRecipient(parameter))
                        .title(requestType.getTemplate().getSubject())
                        .senderName(requestType.getTemplate().getSenderName())
                        .senderAddress(requestType.getTemplate().getSenderAddress())
                        .body(requestType.getTemplate().getBody(parameter))
                , X_NCP_LANG);
    }

}
