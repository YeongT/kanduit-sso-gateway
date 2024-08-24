package com.kanduit.sso;

import com.kanduit.sso.dto.email.EmailSendRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KanduitSsoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanduitSsoGatewayApplication.class, args);

        // initialize EmailSendRequestType(enum)
        EmailSendRequest.init();
    }

}