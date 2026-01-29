package com.registration.service;

import com.registration.request.PlaceTypeRequest;
import com.registration.request.TransportTypeRequest;

import java.util.List;
import java.util.UUID;

public interface PlaceTypeService {

    List<PlaceTypeRequest> getAllPlaceTypes();
    PlaceTypeRequest getPlaceTypeById(UUID id);
    PlaceTypeRequest createPlaceType(PlaceTypeRequest placeTypeRequest);
    PlaceTypeRequest updatePlaceType(UUID id, PlaceTypeRequest placeTypeRequest);
    void deletePlaceType(UUID id);
}
