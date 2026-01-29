package com.registration.service;

import com.registration.request.NearbyPharmaciesRequest;
import com.registration.request.PlaceTypeRequest;

import java.util.List;
import java.util.UUID;

public interface NearbyPharmaciesService {

    List<NearbyPharmaciesRequest> getNearbyPharmacies();
    NearbyPharmaciesRequest getNearbyPharmacyById(UUID id);
    NearbyPharmaciesRequest createNearbyPharmacy(NearbyPharmaciesRequest nearbyPharmaciesRequest);
    NearbyPharmaciesRequest updateNearbyPharmacy(UUID id, NearbyPharmaciesRequest nearbyPharmaciesRequest);
    void deleteNearbyPharmacy(UUID id);
}
