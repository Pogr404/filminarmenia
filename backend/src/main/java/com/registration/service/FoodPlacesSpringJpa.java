package com.registration.service;

import com.registration.entity.placefinder.FoodPlaces;
import com.registration.ex.foodplacesexception.FoodPlaceAlreadyExistsException;
import com.registration.ex.foodplacesexception.FoodPlaceApiException;
import com.registration.ex.foodplacesexception.FoodPlaceNotFoundException;
import com.registration.ex.foodplacesexception.FoodPlacesBadRequestException;
import com.registration.repository.FoodPlacesRepository;
import com.registration.request.FoodPlacesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FoodPlacesSpringJpa implements FoodPlacesService {

    @Autowired
    private FoodPlacesRepository foodPlacesRepository;


    @Override
    public List<FoodPlacesRequest> getFoodPlaces() {
        try {
            return foodPlacesRepository.findAll()
                    .stream()
                    .map(FoodPlaces::toFoodPlacesRequest)
                    .toList();
        } catch (Exception e) {
            throw new FoodPlaceApiException("Problem during getting all food places", e);
        }
    }


    @Override
    public FoodPlacesRequest getFoodPlaceById(UUID id) {
        FoodPlaces foodPlaces = foodPlacesRepository.findById(id)
                .orElseThrow(() -> new FoodPlaceNotFoundException("Food place not found with given ID"));
        return foodPlaces.toFoodPlacesRequest();
    }

    @Override
    public FoodPlacesRequest createFoodPlace(FoodPlacesRequest foodPlacesRequest) {
        if (foodPlacesRequest.getId() != null) {
            throw new FoodPlacesBadRequestException("Gas station ID must be null");
        }

        validateDuplicate(null, foodPlacesRequest.getName());
        foodPlacesRequest.setName(foodPlacesRequest.getName());

        FoodPlaces foodPlaces = new FoodPlaces(foodPlacesRequest);

        try {
            foodPlaces = foodPlacesRepository.save(foodPlaces);
        } catch (Exception e) {
            throw new FoodPlaceApiException("Problem during creating food place", e);
        }

        FoodPlacesRequest newFoodPlace = foodPlaces.toFoodPlacesRequest();

        return newFoodPlace;
    }
    @Override
    public FoodPlacesRequest updateFoodPlace(UUID id, FoodPlacesRequest foodPlacesRequest) {
        FoodPlaces foodPlaces = foodPlacesRepository.findById(id)
                .orElseThrow(() -> new FoodPlaceNotFoundException("Food place not found with given ID"));

        foodPlaces.setName(foodPlacesRequest.getName());

        try {
            FoodPlaces updated = foodPlacesRepository.save(foodPlaces);
            return updated.toFoodPlacesRequest();
        } catch (Exception e) {
            throw new FoodPlaceApiException("Problem during updating food place", e);
        }
    }

    @Override
    public void deleteFoodPlace(UUID id) {
        foodPlacesRepository.findById(id).orElseThrow(() ->
                new FoodPlaceNotFoundException("Food place not found with given ID"));
        try {
            foodPlacesRepository.deleteById(id);
        } catch (Exception e) {
            throw new FoodPlaceApiException("Problem during deleting food place", e);
        }

    }

    private void validateDuplicate(UUID id, String name) {
        Optional<FoodPlaces> existing = foodPlacesRepository.findByName(name);

        if (existing.isPresent()) {
            FoodPlaces foodPlaces= existing.get();

            if (!foodPlaces.getId().equals(id)) {
                throw new FoodPlaceAlreadyExistsException(
                        "Food place with name '" + name + "' already exists"
                );
            }
        }
    }

}
