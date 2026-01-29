package com.registration.api.rest;

import com.registration.constants.RoutConstants;
import com.registration.request.Photo360Request;
import com.registration.service.Photo360Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(RoutConstants.BASE_URL + "${platform.version}" + RoutConstants.PHOTO360)
public class Photo360Controller {

    @Autowired
    private Photo360Service photo360Service;

    @GetMapping
    public List<Photo360Request> getPhoto360() {
        return photo360Service.getPhoto360();
    }

    @GetMapping("/{id}")
    public Photo360Request getPhoto360ById(@PathVariable UUID id) {
        System.out.println("Received ID: " + id);
        return photo360Service.getPhoto360ById(id);
    }
}
