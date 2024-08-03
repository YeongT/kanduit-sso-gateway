package com.kanduit.sso.controller;

import com.kanduit.sso.domain.factory.ResponseFactory;
import com.kanduit.sso.domain.service.UserService;
import com.kanduit.sso.dto.request.UserInitRequestDTO;
import com.kanduit.sso.dto.response.ApplicationResponseStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kanduit.sso.utils.response.APIResponseUtil.createResponse;

@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    private final UserService userService;
    private final ResponseFactory responseFactory;

    @Autowired
    public UserController(UserService userService, ResponseFactory responseFactory) {
        this.userService = userService;
        this.responseFactory = responseFactory;
    }

    @PostMapping("/init")
    public ResponseEntity<?> initUser(@RequestBody @Valid UserInitRequestDTO request) {
        if (!userService.initUser(request)) {
            return createResponse(responseFactory.createResponseWithStatus(ApplicationResponseStatus.USER_ALREADY_EXISTS));
        }
        return createResponse(responseFactory.createResponseWithStatus(ApplicationResponseStatus.USER_INITIALIZATION_SUCCESS));
    }
}
