package com.registration.api.rest;

import com.registration.constants.RoutConstants;
import com.registration.request.PlaceTypeRequest;
import com.registration.request.RoadQualityRequest;
import com.registration.security.RequiredAdminUser;
import com.registration.service.RoadQualityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.reflections.Reflections.log;

@RestController
@RequestMapping(RoutConstants.BASE_URL + "${platform.version}" + RoutConstants.ROAD_QUALITIES)
public class RoadQualityController {

    @Autowired
    private RoadQualityService roadQualityService;

    @GetMapping
    private List<RoadQualityRequest> getAllRoadQualities() {
        return roadQualityService.getAllRoadQualities();
    }

    @GetMapping("/{id}")
    public RoadQualityRequest getRoadQualityById(@PathVariable UUID id) {
        System.out.println("Received ID: " + id);
        return roadQualityService.getRoadQualityById(id);
    }
    @PostMapping
    @RequiredAdminUser
    public RoadQualityRequest createRoadQuality(@RequestBody @Valid RoadQualityRequest roadQualityRequest) {
        log.info("received request to road quality user");
        RoadQualityRequest roadQuality = roadQualityService.createRoadQuality(roadQualityRequest);
        log.info("Road quality created successfully");
        return roadQuality;
    }
    @PutMapping("/{id}")
    @RequiredAdminUser
    public RoadQualityRequest updateRoadQuality(@PathVariable UUID id, @RequestBody RoadQualityRequest roadQualityRequest) {
        return roadQualityService.updateRoadQuality(id, roadQualityRequest);
    }
    @DeleteMapping("/{id}")
    @RequiredAdminUser
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoadQuality(@PathVariable UUID id) {
        roadQualityService.deleteRoadQuality(id);
    }
}
