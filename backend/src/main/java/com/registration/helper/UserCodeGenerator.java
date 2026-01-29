package com.registration.helper;

import org.apache.commons.lang3.RandomStringUtils;

public class UserCodeGenerator {

    public static String generateVerifyCode() {
        return RandomStringUtils.random(6, true, true);
    }
}
