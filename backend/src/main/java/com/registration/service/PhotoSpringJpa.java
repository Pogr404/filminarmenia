package com.registration.service;

import com.registration.entity.placefinder.Photo;
import com.registration.ex.photoexception.PhotoApiException;
import com.registration.ex.photoexception.PhotoNotFoundException;
import com.registration.repository.PhotoRepository;
import com.registration.request.PhotoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PhotoSpringJpa implements PhotoService{

    @Autowired
    private PhotoRepository photoRepository;


    @Override
    public List<PhotoRequest> getPhotos() {
        try {
            return photoRepository.findAll()
                    .stream()
                    .map(Photo::toPhotoRequest)
                    .toList();
        } catch (Exception e) {
            throw new PhotoApiException("Problem during getting all photos", e);
        }
    }


    @Override
    public PhotoRequest getPhotoById(UUID id) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new PhotoNotFoundException("Photo not found with given ID"));
        return photo.toPhotoRequest();
    }
}
