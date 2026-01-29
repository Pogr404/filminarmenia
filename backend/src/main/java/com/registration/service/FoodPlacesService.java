package com.registration.service;

import com.registration.request.FoodPlacesRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface FoodPlacesService {

    List<FoodPlacesRequest> getFoodPlaces();
    FoodPlacesRequest getFoodPlaceById(UUID id);
    FoodPlacesRequest createFoodPlace(@Valid FoodPlacesRequest foodPlacesRequest);
    FoodPlacesRequest updateFoodPlace(UUID id, FoodPlacesRequest foodPlacesRequest);

    void deleteFoodPlace(UUID id);
}
