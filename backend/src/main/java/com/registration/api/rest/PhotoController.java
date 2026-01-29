package com.registration.api.rest;

import com.registration.constants.RoutConstants;
import com.registration.request.PhotoRequest;
import com.registration.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(RoutConstants.BASE_URL + "${platform.version}" + RoutConstants.PHOTO)
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public List<PhotoRequest> getPhotos() {
        return photoService.getPhotos();
    }

    @GetMapping("/{id}")
    public PhotoRequest getPhotoById(@PathVariable UUID id) {
        System.out.println("Received ID: " + id);
        return photoService.getPhotoById(id);
    }
}
