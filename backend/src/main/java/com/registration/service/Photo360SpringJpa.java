package com.registration.service;

import com.registration.entity.placefinder.Photo360;
import com.registration.ex.photo360exception.Photo360ApiException;
import com.registration.ex.photo360exception.Photo360NotFoundException;
import com.registration.repository.Photo360Repository;
import com.registration.request.Photo360Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class Photo360SpringJpa implements Photo360Service{

    @Autowired
    private Photo360Repository photo360Repository;


    @Override
    public List<Photo360Request> getPhoto360() {
        try {
            return photo360Repository.findAll()
                    .stream()
                    .map(Photo360::toPhoto360Request)
                    .toList();
        } catch (Exception e) {
            throw new Photo360ApiException("Problem during getting all photo 360s", e);
        }
    }


    @Override
    public Photo360Request getPhoto360ById(UUID id) {
        Photo360 photo360 = photo360Repository.findById(id)
                .orElseThrow(() -> new Photo360NotFoundException("Photo 360 not found with given ID"));
        return photo360.toPhoto360Request();
    }
}
