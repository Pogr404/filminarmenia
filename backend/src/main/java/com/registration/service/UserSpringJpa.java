package com.registration.service;

import com.registration.entity.UserEntity;
import com.registration.enums.Role;
import com.registration.enums.Status;
import com.registration.ex.userexceptions.UserAlreadyExistException;
import com.registration.ex.userexceptions.UserApiException;
import com.registration.ex.userexceptions.UserBadRequestException;
import com.registration.ex.userexceptions.UserNotFoundException;
import com.registration.helper.EmailSender;
import com.registration.helper.UserCodeGenerator;
import com.registration.repository.UserRepository;
import com.registration.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserSpringJpa implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserRequest createUser(UserRequest userRequest) {


        if (userRequest.getId() != null) {
            throw new UserBadRequestException("User ID must be null");
        }

        String password = userRequest.getPassword();
        validateFields(userRequest);
        validatePassword(password);
        validateDuplicate(null, userRequest.getEmail());
        userRequest.setPassword(passwordEncoder.encode(password));

        UserEntity userEntity = new UserEntity(userRequest);
        String verifyCode = UserCodeGenerator.generateVerifyCode();
        userEntity.setVerifyCode(verifyCode);
        userEntity.setRole(Role.USER);
        userEntity.setStatus(Status.INACTIVE);

        try {
            userEntity = userRepository.save(userEntity);
            emailSender.send(userRequest.getEmail(), "", "Verify code is: " + verifyCode);
        } catch (Exception e) {
            throw new UserApiException("Problem during creating user", e);
        }

        UserRequest newUser = userEntity.toUserRequest();
        removeHiddenFields(newUser);

        return newUser;
    }

    @Override
    public UserRequest getUserById(UUID userId) {


        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with given ID"));
        UserRequest userRequest = userEntity.toUserRequest();
        removeHiddenFields(userRequest);
        return userRequest;
    }

    @Override
    public List<UserRequest> getAllUsers() {
        try {
            return userRepository.findAll()
                    .stream()
                    .map(UserEntity::toUserRequest)
                    .peek(this::removeHiddenFields)
                    .toList();
        } catch (Exception e) {
            throw new UserApiException("Problem during getting all users", e);
        }
    }

    @Override
    public UserRequest updateUser(UUID id, UserRequest userRequest) {

        validateFields(userRequest);
        validatePassword(userRequest.getPassword());
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found with given ID"));
        userRequest.setVerifyCode(userEntity.getVerifyCode());
        userRequest.setResetToken(userEntity.getResetToken());
        userRequest.setRole(userEntity.getRole());
        userRequest.setStatus(userEntity.getStatus());
        userRequest.setId(id);
        try {
            userRequest = userRepository.save(new UserEntity(userRequest)).toUserRequest();
            removeHiddenFields(userRequest);
        } catch (Exception e) {
            throw new UserApiException("Problem during updating user", e);
        }
        return userEntity.toUserRequest();
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found with given ID"));
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserApiException("Problem during deleting user", e);
        }
    }

    private void validateDuplicate(UUID id, String email) {
        UserEntity userEntity;
        try {
            if (id == null) {
                userEntity = userRepository.getByEmail(email);
            } else {
                userEntity = userRepository.getByEmailAndIdNot(email, id);
            }
        } catch (Exception e) {
            throw new UserApiException("Problem during creating user");
        }
        if (userEntity != null) {
            throw new UserAlreadyExistException("User already exists");
        }
    }

    private void validatePassword(String password) {

        if (password == null) {
            throw new UserBadRequestException("Password can not be null");
        }

        if (password.length() < 8) {
            throw new UserBadRequestException("Password must be at least 8 characters");
        }

        int countOfDigits = 0;
        int countOfUppercase = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isDigit(c)) {
                countOfDigits++;
            } else if (Character.isUpperCase(c)) {
                countOfUppercase++;
            }
        }
        if (countOfDigits < 1 || countOfUppercase < 1) {
            throw new UserBadRequestException("Password must contain minimum 1 digit and 1 uppercase character");
        }
    }

    private void validateFields(UserRequest userRequest) {

        if (userRequest.getRole() != null || userRequest.getStatus() != null || userRequest.getResetToken() != null
                || userRequest.getVerifyCode() != null) {
            throw new UserBadRequestException("User request contains unsupported fields");
        }
    }

    public void removeHiddenFields(UserRequest userRequest) {

        userRequest.setPassword(null);
        userRequest.setStatus(null);
        userRequest.setResetToken(null);
        userRequest.setVerifyCode(null);
    }
}
