package com.registration.api.rest;

import com.registration.constants.RoutConstants;
import com.registration.request.CountryRequest;
import com.registration.request.NearbyPharmaciesRequest;
import com.registration.request.PlaceTypeRequest;
import com.registration.security.RequiredAdminUser;
import com.registration.service.CountryService;
import com.registration.service.NearbyPharmaciesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.reflections.Reflections.log;

@RestController
@RequestMapping(RoutConstants.BASE_URL + "${platform.version}" + RoutConstants.NEARBY_PHARMACY)
public class NearbyPharmacyController {

    @Autowired
    private NearbyPharmaciesService nearbyPharmaciesService;

    @GetMapping
    public List<NearbyPharmaciesRequest> getNearbyPharmacies() {
        return nearbyPharmaciesService.getNearbyPharmacies();
    }

    @GetMapping("/{id}")
    public NearbyPharmaciesRequest getNearbyPharmacyById(@PathVariable UUID id) {
        System.out.println("Received ID: " + id);
        return nearbyPharmaciesService.getNearbyPharmacyById(id);
    }
    @PostMapping
    @RequiredAdminUser
    public NearbyPharmaciesRequest createNearbyPharmacy(@RequestBody @Valid NearbyPharmaciesRequest nearbyPharmaciesRequest) {
        log.info("received request to nearby pharmacy user");
        NearbyPharmaciesRequest nearbyPharmacy = nearbyPharmaciesService.createNearbyPharmacy(nearbyPharmaciesRequest);
        log.info("Nearby Pharmacy created successfully");
        return nearbyPharmacy;
    }
    @PutMapping("/{id}")
    @RequiredAdminUser
    public NearbyPharmaciesRequest updateNearbyPharmacy(@PathVariable UUID id, @RequestBody NearbyPharmaciesRequest nearbyPharmaciesRequest) {
        return nearbyPharmaciesService.updateNearbyPharmacy(id, nearbyPharmaciesRequest);
    }
    @DeleteMapping("/{id}")
    @RequiredAdminUser
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNearbyPharmacy(@PathVariable UUID id) {
        nearbyPharmaciesService.deleteNearbyPharmacy(id);
    }
}
