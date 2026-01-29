package com.registration.service;

import com.registration.request.NearbyHotelsRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface NearbyHotelsService {

    List<NearbyHotelsRequest> getNearbyHotels();
    NearbyHotelsRequest getNearbyHotelById(UUID id);
    NearbyHotelsRequest createNearbyHotel(@Valid NearbyHotelsRequest nearbyHotelsRequest);
    NearbyHotelsRequest updateNearbyHotel(UUID id, NearbyHotelsRequest nearbyHotelsRequest);
    void deleteNearbyHotel(UUID id);
}
