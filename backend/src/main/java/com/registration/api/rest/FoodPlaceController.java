package com.registration.api.rest;

import com.registration.constants.RoutConstants;
import com.registration.request.FoodPlacesRequest;
import com.registration.security.RequiredAdminUser;
import com.registration.service.FoodPlacesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.reflections.Reflections.log;

@RestController
@RequestMapping(RoutConstants.BASE_URL + "${platform.version}" + RoutConstants.FOOD_PLACES)
public class FoodPlaceController {

    @Autowired
    private FoodPlacesService foodPlacesService;

    @GetMapping
    public List<FoodPlacesRequest> getFoodPlaces() {
        return foodPlacesService.getFoodPlaces();
    }

    @GetMapping("/{id}")
    public FoodPlacesRequest getFoodPlaceById(@PathVariable UUID id) {
        System.out.println("Received ID: " + id);
        return foodPlacesService.getFoodPlaceById(id);
    }@PostMapping
    @RequiredAdminUser
    public FoodPlacesRequest createFoodPlace(@RequestBody @Valid FoodPlacesRequest foodPlacesRequest) {
        log.info("received request to food place user");
        FoodPlacesRequest foodPlace = foodPlacesService.createFoodPlace(foodPlacesRequest);
        log.info("food place created successfully");
        return foodPlace;
    }
    @PutMapping("/{id}")
    @RequiredAdminUser
    public FoodPlacesRequest updateFoodPlace(@PathVariable UUID id, @RequestBody FoodPlacesRequest foodPlacesRequest) {
        return foodPlacesService.updateFoodPlace(id, foodPlacesRequest);
    }
    @DeleteMapping("/{id}")
    @RequiredAdminUser
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFoodPlace(@PathVariable UUID id) {
        foodPlacesService.deleteFoodPlace(id);
    }
}
