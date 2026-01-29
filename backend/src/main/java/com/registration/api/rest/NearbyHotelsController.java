package com.registration.api.rest;

import com.registration.constants.RoutConstants;
import com.registration.request.NearbyHotelsRequest;
import com.registration.security.RequiredAdminUser;
import com.registration.service.NearbyHotelsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.reflections.Reflections.log;

@RestController
@RequestMapping(RoutConstants.BASE_URL + "${platform.version}" + RoutConstants.NEARBY_HOTELS)
public class NearbyHotelsController {

    @Autowired
    private NearbyHotelsService nearbyHotelsService;

    @GetMapping
    public List<NearbyHotelsRequest> getNearbyHotels() {
        return nearbyHotelsService.getNearbyHotels();
    }

    @GetMapping("/{id}")
    public NearbyHotelsRequest getNearbyHotelsById(@PathVariable UUID id) {
        System.out.println("Received ID: " + id);
        return nearbyHotelsService.getNearbyHotelById(id);
    }

    @PostMapping
    @RequiredAdminUser
    public NearbyHotelsRequest createNearbyHotel(@RequestBody @Valid NearbyHotelsRequest nearbyHotelsRequest) {
        log.info("received request to nearby hotel user");
        NearbyHotelsRequest nearbyHotels = nearbyHotelsService.createNearbyHotel(nearbyHotelsRequest);
        log.info("nearby hotel created successfully");
        return nearbyHotels;
    }
    @PutMapping("/{id}")
    @RequiredAdminUser
    public NearbyHotelsRequest updateNearbyHotel(@PathVariable UUID id, @RequestBody NearbyHotelsRequest nearbyHotelsRequest) {
        return nearbyHotelsService.updateNearbyHotel(id, nearbyHotelsRequest);
    }
    @DeleteMapping("/{id}")
    @RequiredAdminUser
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNearbyHotel(@PathVariable UUID id) {
        nearbyHotelsService.deleteNearbyHotel(id);
    }
}
