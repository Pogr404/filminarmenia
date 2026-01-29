package com.registration.service;

import com.registration.entity.placefinder.ClimateZone;
import com.registration.ex.climatezoneexception.ClimateZoneAlreadyExistException;
import com.registration.ex.climatezoneexception.ClimateZoneApiException;
import com.registration.ex.climatezoneexception.ClimateZoneBadRequestException;
import com.registration.ex.climatezoneexception.ClimateZoneNotFoundException;
import com.registration.repository.ClimateZoneRepository;
import com.registration.request.ClimateZoneRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClimateZoneSpringJpa implements ClimateZoneService{

    @Autowired
    private ClimateZoneRepository climateZoneRepository;


    @Override
    public List<ClimateZoneRequest> getClimateZones() {
        try {
            return climateZoneRepository.findAll()
                    .stream()
                    .map(ClimateZone::toClimateZoneRequest)
                    .toList();
        } catch (Exception e) {
            throw new ClimateZoneApiException("Problem during getting all climate zones", e);
        }
    }


    @Override
    public ClimateZoneRequest getClimateZoneById(UUID id) {
        ClimateZone climateZone = climateZoneRepository.findById(id)
                .orElseThrow(() -> new ClimateZoneNotFoundException("Climate zone not found with given ID"));
        return climateZone.toClimateZoneRequest();
    }


    @Override
    public ClimateZoneRequest createClimateZone(ClimateZoneRequest climateZoneRequest) {


        if (climateZoneRequest.getId() != null) {
            throw new ClimateZoneBadRequestException("Climate zone ID must be null");
        }

        validateDuplicate(null, climateZoneRequest.getName());
        climateZoneRequest.setName(climateZoneRequest.getName());

        ClimateZone  climateZone = new ClimateZone(climateZoneRequest);

        try {
            climateZone = climateZoneRepository.save(climateZone);
        } catch (Exception e) {
            throw new ClimateZoneApiException("Problem during creating climate zone", e);
        }

        ClimateZoneRequest newClimateZone = climateZone.toClimateZoneRequest();

        return newClimateZone;
    }



    @Override
    public ClimateZoneRequest updateClimateZone(UUID id, ClimateZoneRequest climateZoneRequest) {
        ClimateZone climateZone = climateZoneRepository.findById(id)
                .orElseThrow(() -> new ClimateZoneNotFoundException("Climate Zone not found with given ID"));

        climateZone.setName(climateZoneRequest.getName());

        try {
            ClimateZone updated = climateZoneRepository.save(climateZone);
            return updated.toClimateZoneRequest();
        } catch (Exception e) {
            throw new ClimateZoneApiException("Problem during updating climate zone", e);
        }
    }


    @Override
    public void deleteClimateZone(UUID id) {
        climateZoneRepository.findById(id).orElseThrow(() ->
                new ClimateZoneNotFoundException("Climate zone not found with given ID"));
        try {
            climateZoneRepository.deleteById(id);
        } catch (Exception e) {
            throw new ClimateZoneApiException("Problem during deleting climate zone", e);
        }
    }



    private void validateDuplicate(UUID id, String name) {
        Optional<ClimateZone> existing = climateZoneRepository.findByName(name);

        if (existing.isPresent()) {
            ClimateZone climateZone = existing.get();

            if (!climateZone.getId().equals(id)) {
                throw new ClimateZoneAlreadyExistException(
                        "ClimateZone with name '" + name + "' already exists"
                );
            }
        }
    }
}
