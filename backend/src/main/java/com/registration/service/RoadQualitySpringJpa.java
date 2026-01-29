package com.registration.service;

import com.registration.entity.placefinder.PlaceType;
import com.registration.entity.placefinder.RoadQuality;
import com.registration.ex.placeexceptions.PlaceNotFoundException;
import com.registration.ex.placetypeexception.PlaceTypeAlreadyExistsException;
import com.registration.ex.placetypeexception.PlaceTypeApiException;
import com.registration.ex.placetypeexception.PlaceTypeNotFoundException;
import com.registration.ex.roadqualityexception.RoadQualityAlreadyExistsException;
import com.registration.ex.roadqualityexception.RoadQualityApiException;
import com.registration.ex.roadqualityexception.RoadQualityNotFoundException;
import com.registration.ex.userexceptions.UserApiException;
import com.registration.ex.userexceptions.UserBadRequestException;
import com.registration.repository.RoadQualityRepository;
import com.registration.request.PlaceTypeRequest;
import com.registration.request.RoadQualityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoadQualitySpringJpa implements RoadQualityService{


    @Autowired
    private RoadQualityRepository roadQualityRepository;


    @Override
    public List<RoadQualityRequest> getAllRoadQualities() {
        try {
            return roadQualityRepository.findAll()
                    .stream()
                    .map(RoadQuality::toRoadQualityRequest)
                    .toList();
        } catch (Exception e) {
            throw new RoadQualityApiException("Problem during getting all road qualities", e);
        }
    }


    @Override
    public RoadQualityRequest getRoadQualityById(UUID id) {
        RoadQuality roadQuality = roadQualityRepository.findById(id)
                .orElseThrow(() -> new RoadQualityNotFoundException("Road Quality not found with given ID"));
        return roadQuality.toRoadQualityRequest();
    }
    @Override
    public RoadQualityRequest createRoadQuality(RoadQualityRequest roadQualityRequest) {


        if (roadQualityRequest.getId() != null) {
            throw new UserBadRequestException("User ID must be null");
        }

        validateDuplicate(null, roadQualityRequest.getName());
        roadQualityRequest.setName(roadQualityRequest.getName());

        RoadQuality roadQuality = new RoadQuality(roadQualityRequest);

        try {
            roadQuality = roadQualityRepository.save(roadQuality);
        } catch (Exception e) {
            throw new UserApiException("Problem during creating road quality", e);
        }

        RoadQualityRequest newRoadQuality= roadQuality.toRoadQualityRequest();

        return newRoadQuality;
    }

    @Override
    public RoadQualityRequest updateRoadQuality(UUID id, RoadQualityRequest roadQualityRequest) {
        RoadQuality roadQuality = roadQualityRepository.findById(id)
                .orElseThrow(() -> new RoadQualityNotFoundException("Road quality not found with given ID"));

        roadQuality.setName(roadQualityRequest.getName());

        try {
            RoadQuality updated = roadQualityRepository.save(roadQuality);
            return updated.toRoadQualityRequest();
        } catch (Exception e) {
            throw new RoadQualityApiException("Problem during updating road quality", e);
        }

    }

    @Override
    public void deleteRoadQuality(UUID id) {
        roadQualityRepository.findById(id).orElseThrow(() ->
                new RoadQualityNotFoundException("Place type not found with given ID"));
        try {
            roadQualityRepository.deleteById(id);
        } catch (Exception e) {
            throw new RoadQualityApiException("Problem during deleting road quality", e);
        }

    }
    private void validateDuplicate(UUID id, String name) {
        Optional<RoadQuality> existing = roadQualityRepository.findByName(name);

        if (existing.isPresent()) {
            RoadQuality roadQuality = existing.get();

            if (!roadQuality.getId().equals(id)) {
                throw new RoadQualityAlreadyExistsException(
                        "Road Quality with name '" + name + "' already exists"
                );
            }
        }
    }

}
