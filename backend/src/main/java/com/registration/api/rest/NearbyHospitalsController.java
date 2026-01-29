package com.registration.api.rest;

import com.registration.constants.RoutConstants;
import com.registration.request.NearbyHospitalsRequest;
import com.registration.security.RequiredAdminUser;
import com.registration.service.NearbyHospitalsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.reflections.Reflections.log;

@RestController
@RequestMapping(RoutConstants.BASE_URL + "${platform.version}" + RoutConstants.NEARBY_HOSPITALS)
public class NearbyHospitalsController {

    @Autowired
    private NearbyHospitalsService nearbyHospitalsService;

    @GetMapping
    public List<NearbyHospitalsRequest> getNearbyHospitals() {
        return nearbyHospitalsService.getNearbyHospitals();
    }

    @GetMapping("/{id}")
    public NearbyHospitalsRequest getNearbyHospitalById(@PathVariable UUID id) {
        System.out.println("Received ID: " + id);
        return nearbyHospitalsService.getNearbyHospitalById(id);
    }
    @PostMapping
    @RequiredAdminUser
    public NearbyHospitalsRequest createNearbyHospital(@RequestBody @Valid NearbyHospitalsRequest nearbyHospitalsRequest) {
        log.info("received request to nearby hospital user");
        NearbyHospitalsRequest nearbyHospitals = nearbyHospitalsService.createNearbyHospital(nearbyHospitalsRequest);
        log.info("nearby hospital created successfully");
        return nearbyHospitals;
    }
    @PutMapping("/{id}")
    @RequiredAdminUser
    public NearbyHospitalsRequest updateNearbyHospital(@PathVariable UUID id, @RequestBody NearbyHospitalsRequest nearbyHospitalsRequest) {
        return nearbyHospitalsService.updateNearbyHospital(id, nearbyHospitalsRequest);
    }
    @DeleteMapping("/{id}")
    @RequiredAdminUser
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNearbyHospital(@PathVariable UUID id) {
        nearbyHospitalsService.deleteNearbyHospital(id);
    }
}
