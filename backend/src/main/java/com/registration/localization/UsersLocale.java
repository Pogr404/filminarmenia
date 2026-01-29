package com.registration.localization;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An enum containing supported locales.
 */
public enum UsersLocale {

    HY_AM("hy", "AM"),
    EN_GB("en", "GB"),
    RU_RU("ru", "RU");

    private String lang;
    private String country;
    private String locale;

    private static Locale armenian = new Locale("ru", "RU");

    private static final Locale defaultLocale;
    private static final Map<String, Locale> localeMap;

    UsersLocale(String lang, String country) {
        this.lang = lang;
        this.country = country;
        this.locale = lang + "_" + country;
    }

    static {
        localeMap = new HashMap<>(UsersLocale.values().length);

        for(UsersLocale demoLocale : UsersLocale.values()) {
            localeMap.put(demoLocale.getLang(), new Locale(demoLocale.getLang(), demoLocale.getCountry()));
        }

        defaultLocale = new Locale(EN_GB.getLang(), EN_GB.getCountry());

    }

    public static Locale getJavaLocale(String locale) {
        return localeMap.get(locale);
    }

    public String getLocale() {
        return locale;
    }

    public static UsersLocale fromString(String text) {
        if(text != null && !text.isEmpty()) {
            for(UsersLocale demoLocale : UsersLocale.values()) {

                if(text.equalsIgnoreCase(demoLocale.locale)) {
                    return demoLocale;
                }
            }
        }

        return null;
    }

    public static List<String> locales() {
        return Arrays.stream(UsersLocale.values())
                .map(UsersLocale::getLocale)
                .collect(Collectors.toList());
    }

    public static List<Locale> javaLocales() {
        return Arrays.stream(values())
                .map(demoLocale -> new Locale(demoLocale.getLang(), demoLocale.getCountry()))
                .collect(Collectors.toList());
    }

    public String getLang() {
        return lang;
    }

    public String getCountry() {
        return country;
    }

    public static UsersLocale defaultLocale() {
        return EN_GB;
    }

    public static Locale defaultJavaLocale() {
        return armenian;
    }

    @Override
    public String toString() {
        return locale;
    }
}
