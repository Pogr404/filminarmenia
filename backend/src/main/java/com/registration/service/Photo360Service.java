package com.registration.service;

import com.registration.request.Photo360Request;

import java.util.List;
import java.util.UUID;

public interface Photo360Service {

    List<Photo360Request> getPhoto360();
    Photo360Request getPhoto360ById(UUID id);
}
