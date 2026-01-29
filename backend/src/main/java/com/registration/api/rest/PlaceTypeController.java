package com.registration.api.rest;


import com.registration.constants.RoutConstants;
import com.registration.request.PlaceTypeRequest;
import com.registration.request.TransportTypeRequest;
import com.registration.security.RequiredAdminUser;
import com.registration.service.PlaceTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.reflections.Reflections.log;

@RestController
@RequestMapping(RoutConstants.BASE_URL + "${platform.version}" + RoutConstants.PLACE_TYPES)
public class PlaceTypeController {

    @Autowired
    private PlaceTypeService placeTypeService;


    @GetMapping
    public List<PlaceTypeRequest> getAllPlaceTypes() {
        return placeTypeService.getAllPlaceTypes();
    }

    @GetMapping("/{id}")
    public PlaceTypeRequest getPlaceTypeById(@PathVariable UUID id) {
        System.out.println("Received ID: " + id);
        return placeTypeService.getPlaceTypeById(id);
    }
    @PostMapping
    @RequiredAdminUser
    public PlaceTypeRequest createPlaceType(@RequestBody @Valid PlaceTypeRequest placeTypeRequest) {
        log.info("received request to place type user");
        PlaceTypeRequest placeType = placeTypeService.createPlaceType(placeTypeRequest);
        log.info("Place type created successfully");
        return placeType;
    }
    @PutMapping("/{id}")
    @RequiredAdminUser
    public PlaceTypeRequest updatePlaceType(@PathVariable UUID id, @RequestBody PlaceTypeRequest placeTypeRequest) {
        return placeTypeService.updatePlaceType(id, placeTypeRequest);
    }
    @DeleteMapping("/{id}")
    @RequiredAdminUser
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlaceType(@PathVariable UUID id) {
        placeTypeService.deletePlaceType(id);
    }
}
