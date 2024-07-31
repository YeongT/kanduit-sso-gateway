package com.kanduit.sso.controller;

import com.kanduit.sso.domain.service.UserService;
import com.kanduit.sso.dto.request.UserInitRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/init")
    public ResponseEntity<String> initUser(@RequestBody @Valid UserInitRequestDTO request) {
        if (!userService.initUser(request)) {
            return new ResponseEntity<>("EMAIL EXIST", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
