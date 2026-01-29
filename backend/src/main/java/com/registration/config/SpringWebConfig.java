package com.registration.config;


import com.registration.util.Constants;
import com.registration.localization.I18n;
import com.registration.localization.UsersLocale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

/**
 * Spring managed Spring MVC configuration.
 *
 */
@Configuration
@EnableWebMvc
public class SpringWebConfig extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

    private static final List<Locale> LOCALES = UsersLocale.javaLocales();


    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    @Override
    public Locale resolveLocale(jakarta.servlet.http.HttpServletRequest request) {
        String headerLang = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
        return headerLang == null || headerLang.isEmpty()
                ? UsersLocale.defaultJavaLocale()
                : Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
    }

    /**
     * Creates {@link ResourceBundleMessageSource} for accessing resource bundles using specified base names.
     *
     * @return the ResourceBundleMessageSource
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("i18n/messages");
        source.setDefaultEncoding(Constants.UTF8);
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    /**
     * Creates the {@link I18n} instance wrapped with Spring MessagesSource.
     *
     * @return the I18n
     */
    @Bean
    public I18n i18n() {
        I18n i18n = new I18n();
        i18n.setMessageSource(messageSource());
        return i18n;
    }

}
