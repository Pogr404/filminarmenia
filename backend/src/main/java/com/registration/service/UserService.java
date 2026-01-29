package com.registration.service;

import com.registration.request.UserRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserRequest createUser(UserRequest userRequest);
    UserRequest getUserById(UUID userId);
    List<UserRequest> getAllUsers();
    UserRequest updateUser(UUID id, UserRequest userRequest);
    void deleteUser(UUID id);
}
