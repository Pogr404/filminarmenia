package com.registration.security;

import com.registration.entity.UserEntity;
import com.registration.enums.Role;
import com.registration.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Base64;


@Slf4j
@Component
public class ThreadContextInterceptor implements HandlerInterceptor {

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {



        if (handler instanceof HandlerMethod handlerMethod) {
            RestTemplate  template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();


            HandlerMethod method = (HandlerMethod) handler;
            RequiredAdminUser methodAnnotation = handlerMethod.getMethodAnnotation(RequiredAdminUser.class);
            if (methodAnnotation != null) {

                String BearerToken = request.getHeader("Authorization");
                if (BearerToken != null && BearerToken.startsWith("Bearer ")) {
                    String token = BearerToken.substring("Bearer ".length());
                    String emailFromToken = getEmailFromToken(token);

                    UserEntity userEntity = userRepository.getByEmail(emailFromToken);
                    if (!userEntity.getRole().equals(Role.ADMIN)) {
                        log.error("");
                        throw new SecurityForbiddenException("Insufficient permission");
                    }
                }

            }
        }
        return true;
    }

    private String getEmailFromToken(String token) {
        String[] split = token.split("\\.");
        String payload = new String(Base64.getUrlDecoder().decode(split[1]));

        String s = payload.split(",")[0].split(":")[1];
        return s.substring(1, s.length() - 1);
    }
}
