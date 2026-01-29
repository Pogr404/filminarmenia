package com.registration.api.rest;

import com.registration.constants.RoutConstants;
import com.registration.request.GasStationsRequest;
import com.registration.security.RequiredAdminUser;
import com.registration.service.GasStationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.reflections.Reflections.log;

@RestController
@RequestMapping(RoutConstants.BASE_URL + "${platform.version}" + RoutConstants.GAS_STATION)
public class GasStationController {

    @Autowired
    private GasStationService gasStationService;

    @GetMapping
    public List<GasStationsRequest> getGasStations() {
        return gasStationService.getGasStations();
    }

    @GetMapping("/{id}")
    public GasStationsRequest getGasStationById(@PathVariable UUID id) {
        System.out.println("Received ID: " + id);
        return gasStationService.getGasStationById(id);
    }
    @PostMapping
    @RequiredAdminUser
    public GasStationsRequest createGasStation(@RequestBody @Valid GasStationsRequest gasStationsRequest) {
        log.info("received request to gas station user");
        GasStationsRequest gasStations = gasStationService.createGasStation(gasStationsRequest);
        log.info("gas station created successfully");
        return gasStations;
    }
    @PutMapping("/{id}")
    @RequiredAdminUser
    public GasStationsRequest updateFoodPlace(@PathVariable UUID id, @RequestBody GasStationsRequest gasStationsRequest) {
        return gasStationService.updateGasStation(id, gasStationsRequest);
    }
    @DeleteMapping("/{id}")
    @RequiredAdminUser
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGasStation(@PathVariable UUID id) {
        gasStationService.deleteGasStation(id);
    }
}
