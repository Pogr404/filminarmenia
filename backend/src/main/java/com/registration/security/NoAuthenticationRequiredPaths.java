package com.registration.security;

import org.springframework.http.HttpMethod;

import java.util.HashSet;
import java.util.LinkedHashMap;

public class NoAuthenticationRequiredPaths {

    public LinkedHashMap<HttpMethod, HashSet<String>> getExcludePaths() {

        return null;
    }
}
