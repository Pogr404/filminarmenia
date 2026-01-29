package com.registration.api.rest;

import com.registration.constants.RoutConstants;
import com.registration.localization.UsersLocale;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
@RequestMapping(RoutConstants.BASE_URL + "${user.service.version}/test")
public class LanguageTestController extends BaseController {

    @GetMapping("/language-test")
    public String testLanguage(HttpServletRequest request) {
//        Locale locale = getUserLocale(request);
        Locale locale = UsersLocale.defaultJavaLocale();
        String msg =  i18n.msg("error.invalid_username_password", locale);

        return msg;
    }

}
