package com.registration.service;

import com.registration.request.PhotoRequest;

import java.util.List;
import java.util.UUID;

public interface PhotoService {

    List<PhotoRequest> getPhotos();
    PhotoRequest getPhotoById(UUID id);
}
