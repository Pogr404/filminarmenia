package com.registration.service;

import com.registration.entity.UserEntity;
import com.registration.ex.userexceptions.UserApiException;
import com.registration.ex.userexceptions.UserBadRequestException;
import com.registration.repository.UserRepository;
import com.registration.request.LoginRequest;
import com.registration.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthSpringJpa implements AuthService {

    @Autowired
    @Qualifier("userAuthenticationManager")
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public String login(LoginRequest loginRequest) {
        String token = null;

        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword(), new ArrayList<>()));

            String email = authenticate.getName();
            UserEntity userEntity = userRepository.getByEmail(email);
            userEntity.setPassword("");
            token = jwtUtil.creatToken(userEntity);

        } catch (BadCredentialsException e) {
            throw new UserBadRequestException("Invalid username or password");
        } catch (Exception e) {
            throw new UserApiException("Problem during getting token");
        }
        return token;
    }
}
