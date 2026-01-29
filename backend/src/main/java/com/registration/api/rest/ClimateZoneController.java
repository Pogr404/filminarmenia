package com.registration.api.rest;

import com.registration.constants.RoutConstants;
import com.registration.request.ClimateZoneRequest;
import com.registration.security.RequiredAdminUser;
import com.registration.service.ClimateZoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.reflections.Reflections.log;

@RestController
@RequestMapping(RoutConstants.BASE_URL + "${platform.version}" + RoutConstants.CLIMATE_ZONES)
public class ClimateZoneController {

    @Autowired
    private ClimateZoneService climateZoneService;

    @GetMapping
    public List<ClimateZoneRequest> getAllClimateZones() {
        return climateZoneService.getClimateZones();
    }

    @GetMapping("/{id}")
    public ClimateZoneRequest getClimatezoneById(@PathVariable UUID id) {
        System.out.println("Received ID: " + id);
        return climateZoneService.getClimateZoneById(id);
    }

    @PostMapping
    @RequiredAdminUser
    public ClimateZoneRequest createClimateZone(@RequestBody @Valid ClimateZoneRequest climateZoneRequest) {
        log.info("received request to climate zone user");
        ClimateZoneRequest climateZone = climateZoneService.createClimateZone(climateZoneRequest);
        log.info("Climate zone created successfully");
        return climateZone;
    }


    @PutMapping("/{id}")
    @RequiredAdminUser
    public ClimateZoneRequest updateClimateZone(@PathVariable UUID id, @RequestBody ClimateZoneRequest climateZoneRequest) {
        return climateZoneService.updateClimateZone(id, climateZoneRequest);
    }

    @DeleteMapping("/{id}")
    @RequiredAdminUser
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClimateZone(@PathVariable UUID id) {
        climateZoneService.deleteClimateZone(id);
    }
}
