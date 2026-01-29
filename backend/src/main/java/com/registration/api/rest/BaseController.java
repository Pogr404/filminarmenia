package com.registration.api.rest;

import com.registration.security.SecurityUtils;
import com.registration.localization.I18n;
import com.registration.localization.UsersLocale;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Locale;

/**
 * The base class for all controllers.
 * Contains common fields and methods.
 */
public class BaseController implements Serializable {

    @Autowired
    protected transient I18n i18n;


    protected String getHeaderLanguage(HttpServletRequest request) {
        return request.getHeader(SecurityUtils.ACCEPT_LANGUAGE_HEADER);
    }

    protected Locale getUserLocale(HttpServletRequest request) {
        String language = getHeaderLanguage(request);
        Locale locale = UsersLocale.getJavaLocale(language);
        return locale != null ? locale : UsersLocale.defaultJavaLocale();
    }

}
