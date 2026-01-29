package com.registration.service;

import com.registration.request.LoginRequest;

public interface AuthService {

    String login(LoginRequest loginRequest);
}
