package com.registration.service;

import com.registration.request.ClimateZoneRequest;
import com.registration.request.PlaceRequest;

import java.util.List;
import java.util.UUID;

public interface PlaceService {

    List<PlaceRequest> getAllPlaces();
    PlaceRequest getPlaceById(UUID placeId);
    List<PlaceRequest> getRecommendedPlaces();
    List<PlaceRequest> searchPlaces(
            UUID placeTypeId,
            UUID countryId,
            UUID roadQualityId,
            UUID nearbyHotelsId,
            UUID nearbyHospitalsId,
            UUID nearbyPharmaciesId,
            UUID gasStationsId,
            UUID foodPlacesId,
            UUID climateZoneId
    );

    List<PlaceRequest> searchPlacesByKeywords(List<String> keywordValues);
    PlaceRequest createPlace(PlaceRequest placeRequest);
    PlaceRequest updatePlace(UUID id, PlaceRequest placeRequest);
    void deletePlace(UUID id);

//    PlaceRequest getPlacesByKeyWords(List<String> keywords); // Kept commented as in original
}
