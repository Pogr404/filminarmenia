package com.registration.service;

import com.registration.entity.placefinder.ClimateZone;
import com.registration.entity.placefinder.NearbyPharmacy;
import com.registration.entity.placefinder.TransportType;
import com.registration.ex.climatezoneexception.ClimateZoneApiException;
import com.registration.ex.climatezoneexception.ClimateZoneNotFoundException;
import com.registration.ex.nearbypharmacyexception.NearbyPharmacyAlreadyExistsException;
import com.registration.ex.nearbypharmacyexception.NearbyPharmacyApiException;
import com.registration.ex.nearbypharmacyexception.NearbyPharmacyNotFoundException;
import com.registration.ex.transporttypeexcpetion.TransportTypeAlreadyExistsException;
import com.registration.ex.transporttypeexcpetion.TransportTypeApiException;
import com.registration.ex.transporttypeexcpetion.TransportTypeNotFoundException;
import com.registration.ex.userexceptions.UserApiException;
import com.registration.ex.userexceptions.UserBadRequestException;
import com.registration.repository.ClimateZoneRepository;
import com.registration.repository.NearbyPharmaciesRepository;
import com.registration.request.ClimateZoneRequest;
import com.registration.request.NearbyPharmaciesRequest;
import com.registration.request.TransportTypeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NearbyPharmaciesSpringJpa implements NearbyPharmaciesService{

    @Autowired
    private NearbyPharmaciesRepository nearbyPharmaciesRepository;


    @Override
    public List<NearbyPharmaciesRequest> getNearbyPharmacies() {
        try {
            return nearbyPharmaciesRepository.findAll()
                    .stream()
                    .map(NearbyPharmacy::toNearbyPharmaciesRequest)
                    .toList();
        } catch (Exception e) {
            throw new NearbyPharmacyApiException("Problem during getting all nearby pharmacies", e);
        }
    }


    @Override
    public NearbyPharmaciesRequest getNearbyPharmacyById(UUID id) {
        NearbyPharmacy nearbyPharmacy = nearbyPharmaciesRepository.findById(id)
                .orElseThrow(() -> new NearbyPharmacyNotFoundException("Nearby pharmacy not found with given ID"));
        return nearbyPharmacy.toNearbyPharmaciesRequest();
    }
    @Override
    public NearbyPharmaciesRequest createNearbyPharmacy(NearbyPharmaciesRequest nearbyPharmaciesRequest) {


        if (nearbyPharmaciesRequest.getId() != null) {
            throw new UserBadRequestException("User ID must be null");
        }

        validateDuplicate(null, nearbyPharmaciesRequest.getName());
        nearbyPharmaciesRequest.setName(nearbyPharmaciesRequest.getName());

        NearbyPharmacy nearbyPharmacy = new NearbyPharmacy(nearbyPharmaciesRequest);

        try {
            nearbyPharmacy = nearbyPharmaciesRepository.save(nearbyPharmacy);
        } catch (Exception e) {
            throw new UserApiException("Problem during creating nearby pharmacy", e);
        }

        NearbyPharmaciesRequest newNearbyPharmacy = nearbyPharmacy.toNearbyPharmaciesRequest();

        return newNearbyPharmacy;
    }

    @Override
    public NearbyPharmaciesRequest updateNearbyPharmacy(UUID id, NearbyPharmaciesRequest nearbyPharmaciesRequest) {
        NearbyPharmacy nearbyPharmacy = nearbyPharmaciesRepository.findById(id)
                .orElseThrow(() -> new NearbyPharmacyNotFoundException("Nearby pharmacy not found with given ID"));

        nearbyPharmacy.setName(nearbyPharmaciesRequest.getName());

        try {
            NearbyPharmacy updated = nearbyPharmaciesRepository.save(nearbyPharmacy);
            return updated.toNearbyPharmaciesRequest();
        } catch (Exception e) {
            throw new NearbyPharmacyApiException("Problem during updating nearby pharmacy", e);
        }

    }

    @Override
    public void deleteNearbyPharmacy(UUID id) {
        nearbyPharmaciesRepository.findById(id).orElseThrow(() ->
                new NearbyPharmacyNotFoundException("Nearby pharmacy not found with given ID"));
        try {
            nearbyPharmaciesRepository.deleteById(id);
        } catch (Exception e) {
            throw new NearbyPharmacyApiException("Problem during deleting nearby pharmacy", e);
        }

    }
    private void validateDuplicate(UUID id, String name) {
        Optional<NearbyPharmacy> existing = nearbyPharmaciesRepository.findByName(name);

        if (existing.isPresent()) {
            NearbyPharmacy nearbyPharmacy = existing.get();

            if (!nearbyPharmacy.getId().equals(id)) {
                throw new NearbyPharmacyAlreadyExistsException(
                        "Nearby pharmacy with name '" + name + "' already exists"
                );
            }
        }
    }
}
