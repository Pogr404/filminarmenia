package com.registration.service;

import com.registration.entity.placefinder.RoadQuality;
import com.registration.request.RoadQualityRequest;
import com.registration.request.TransportTypeRequest;

import java.util.List;
import java.util.UUID;

public interface RoadQualityService {

    List<RoadQualityRequest> getAllRoadQualities();
    RoadQualityRequest getRoadQualityById(UUID id);
    RoadQualityRequest createRoadQuality(RoadQualityRequest roadQualityRequest);
    RoadQualityRequest updateRoadQuality(UUID id, RoadQualityRequest roadQualityRequest);
    void deleteRoadQuality(UUID id);
}
