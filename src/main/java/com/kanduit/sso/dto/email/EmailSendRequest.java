package com.kanduit.sso.dto.email;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kanduit.sso.domain.model.email.EmailTemplate;
import com.kanduit.sso.domain.model.email.sample.SampleEmailTemplate;
import com.kanduit.sso.dto.response.APIResponseStatus;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude
public enum EmailSendRequest {
    SAMPLE(APIResponseStatus.SUCCESS, SampleEmailTemplate.getInstance());

    // authentication
//    AUTHENTICATION_USER_REGISTER_REQUEST(APIResponseStatus.SUCCESS, null),
//    AUTHENTICATION_USER_EMAIL_CHANGE(APIResponseStatus.SUCCESS, null);
//    AUTHENTICATION_USER_PASSWORD_RESET(APIResponseStatus.SUCCESS, null);
//
//  // alert notification
//    ALERT_USER_REGISTER_COMPLETED(APIResponseStatus.SUCCESS, null);
//    ALERT_USER_EMAIL_CHANGED(APIResponseStatus.SUCCESS, null);
//    ALERT_USER_PASSWORD_CHANGED(APIResponseStatus.SUCCESS, null);

    @NonNull
    private final APIResponseStatus responseStatusOnSuccess;

    @NonNull
    @Valid
    private final EmailTemplate template;

    EmailSendRequest(@NonNull APIResponseStatus responseStatus, @NonNull EmailTemplate template) {
        this.responseStatusOnSuccess = responseStatus;
        this.template = template;
    }

    public static void init() {
    }
}
