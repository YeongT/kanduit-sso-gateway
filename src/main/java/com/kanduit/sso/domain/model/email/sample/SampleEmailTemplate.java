package com.kanduit.sso.domain.model.email.sample;

import com.kanduit.sso.domain.model.email.EmailTemplate;
import com.kanduit.sso.domain.model.email.EmailTemplateParameter;
import com.kanduit.sso.exception.APIException;
import lombok.Getter;
import lombok.NonNull;

@Getter
public final class SampleEmailTemplate implements EmailTemplate {
    // instance should be only one
    @Getter
    private final static SampleEmailTemplate instance;

    static {
        final String templateFilePath = "template/sample.html";
        instance = new SampleEmailTemplate(templateFilePath);
    }

    private final @NonNull Boolean isMailForAdvertising = false;
    private final @NonNull String senderAddress = "";
    private final @NonNull String senderName = "";
    private final @NonNull String subject = "";

    @NonNull
    private final String templateContent;

    private SampleEmailTemplate(@NonNull String templateFilePath) {
        /*
         * use can use EmailTemplate.readTempleFile method to set templateContent
         *
         * example usage:
         * this.templateContent = readTemplateFile(templateFilePath);
         */
        this.templateContent = "{{recipientName}}님, 당신의 이메일 주소는 {{recipientAddress}}입니다."; // for avoid file not found exception, only on sample template
    }

    @Override
    public @NonNull <T extends EmailTemplateParameter> String getBody(@NonNull T t) throws APIException {
        if (t instanceof SampleParameter parameter) {
            return getTemplateContent()
                    .replace("{{recipientName}}", parameter.getRecipientName())
                    .replace("{{recipientAddress}}", parameter.getRecipientAddress());
        }
        throw createInvalidParameterException(SampleParameter.class.getName(), t.getClass().getName());
    }

    public static class SampleParameter extends EmailTemplateParameter {
        public SampleParameter(@NonNull String recipientName, @NonNull String recipientAddress) {
            super(recipientName, recipientAddress);
        }
    }

}
