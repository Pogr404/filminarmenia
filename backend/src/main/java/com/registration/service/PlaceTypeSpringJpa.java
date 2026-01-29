package com.registration.service;

import com.registration.entity.placefinder.PlaceType;
import com.registration.entity.placefinder.TransportType;
import com.registration.ex.placeexceptions.PlaceNotFoundException;
import com.registration.ex.placetypeexception.PlaceTypeAlreadyExistsException;
import com.registration.ex.placetypeexception.PlaceTypeApiException;
import com.registration.ex.placetypeexception.PlaceTypeNotFoundException;
import com.registration.ex.transporttypeexcpetion.TransportTypeAlreadyExistsException;
import com.registration.ex.transporttypeexcpetion.TransportTypeApiException;
import com.registration.ex.transporttypeexcpetion.TransportTypeNotFoundException;
import com.registration.ex.userexceptions.UserApiException;
import com.registration.ex.userexceptions.UserBadRequestException;
import com.registration.repository.PlaceTypeRepository;
import com.registration.request.PlaceTypeRequest;
import com.registration.request.TransportTypeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlaceTypeSpringJpa implements PlaceTypeService {


    @Autowired
    private PlaceTypeRepository placeTypeRepository;


    @Override
    public List<PlaceTypeRequest> getAllPlaceTypes() {
        try {
            return placeTypeRepository.findAll()
                    .stream()
                    .map(com.registration.entity.placefinder.PlaceType::toPlaceTypeRequest)
                    .toList();
        } catch (Exception e) {
            throw new PlaceTypeApiException("Problem during getting all places", e);
        }
    }

    @Override
    public PlaceTypeRequest getPlaceTypeById(UUID id) {
        PlaceType placeEntity = placeTypeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException("Place not found with given ID"));
        return placeEntity.toPlaceTypeRequest();
    }
    @Override
    public PlaceTypeRequest createPlaceType(PlaceTypeRequest placeTypeRequest) {


        if (placeTypeRequest.getId() != null) {
            throw new UserBadRequestException("User ID must be null");
        }

        validateDuplicate(null, placeTypeRequest.getName());
        placeTypeRequest.setName(placeTypeRequest.getName());

        PlaceType placeType = new PlaceType(placeTypeRequest);

        try {
            placeType = placeTypeRepository.save(placeType);
        } catch (Exception e) {
            throw new UserApiException("Problem during creating place type", e);
        }

        PlaceTypeRequest newPlaceType = placeType.toPlaceTypeRequest();

        return newPlaceType;
    }

    @Override
    public PlaceTypeRequest updatePlaceType(UUID id, PlaceTypeRequest placeTypeRequest) {
        PlaceType placeType = placeTypeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException("Place type not found with given ID"));

        placeType.setName(placeTypeRequest.getName());

        try {
            PlaceType updated = placeTypeRepository.save(placeType);
            return updated.toPlaceTypeRequest();
        } catch (Exception e) {
            throw new PlaceTypeApiException("Problem during updating place type", e);
        }

    }

    @Override
    public void deletePlaceType(UUID id) {
        placeTypeRepository.findById(id).orElseThrow(() ->
                new PlaceTypeNotFoundException("Place type not found with given ID"));
        try {
            placeTypeRepository.deleteById(id);
        } catch (Exception e) {
            throw new PlaceTypeApiException("Problem during deleting place type", e);
        }

    }
    private void validateDuplicate(UUID id, String name) {
        Optional<PlaceType> existing = placeTypeRepository.findByName(name);

        if (existing.isPresent()) {
            PlaceType placeType = existing.get();

            if (!placeType.getId().equals(id)) {
                throw new PlaceTypeAlreadyExistsException(
                        "Place type with name '" + name + "' already exists"
                );
            }
        }
    }
}
