package com.registration.api.rest;

import com.registration.constants.RoutConstants;
import com.registration.request.LoginRequest;
import com.registration.service.AuthService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequestMapping(RoutConstants.BASE_URL + RoutConstants.AUTH + RoutConstants.AUTH_ADMIN)
public class AdminAuthController {

    @Autowired
    private AuthService service;

    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest) {
        return service.login(loginRequest);
    }
}
