package com.registration.service;

import com.registration.entity.placefinder.NearbyHospitals;
import com.registration.ex.nearbyhospitalsexception.NearbyHospitalsAlreadyExistsException;
import com.registration.ex.nearbyhospitalsexception.NearbyHospitalsApiException;
import com.registration.ex.nearbyhospitalsexception.NearbyHospitalsBadRequestException;
import com.registration.ex.nearbyhospitalsexception.NearbyHospitalsNotFoundException;
import com.registration.repository.NearbyHospitalsRepository;
import com.registration.request.NearbyHospitalsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NearbyHospitalsSpringJpa implements NearbyHospitalsService{

    @Autowired
    private NearbyHospitalsRepository nearbyHospitalsRepository;


    @Override
    public List<NearbyHospitalsRequest> getNearbyHospitals() {
        try {
            return nearbyHospitalsRepository.findAll()
                    .stream()
                    .map(NearbyHospitals::toNearbyHospitalsRequest)
                    .toList();
        } catch (Exception e) {
            throw new NearbyHospitalsApiException("Problem during getting all nearby hospitals", e);
        }
    }


    @Override
    public NearbyHospitalsRequest getNearbyHospitalById(UUID id) {
        NearbyHospitals nearbyHospitals = nearbyHospitalsRepository.findById(id)
                .orElseThrow(() -> new NearbyHospitalsNotFoundException("Nearby hospital not found with given ID"));
        return nearbyHospitals.toNearbyHospitalsRequest();
    }
    @Override
    public NearbyHospitalsRequest createNearbyHospital(NearbyHospitalsRequest nearbyHospitalsRequest) {
        if (nearbyHospitalsRequest.getId() != null) {
            throw new NearbyHospitalsBadRequestException("User ID must be null");
        }

        validateDuplicate(null, nearbyHospitalsRequest.getName());
        nearbyHospitalsRequest.setName(nearbyHospitalsRequest.getName());

        NearbyHospitals nearbyHospitals = new NearbyHospitals(nearbyHospitalsRequest);

        try {
            nearbyHospitals = nearbyHospitalsRepository.save(nearbyHospitals);
        } catch (Exception e) {
            throw new NearbyHospitalsApiException("Problem during creating nearby hospital", e);
        }

        NearbyHospitalsRequest newNearbyHospital= nearbyHospitals.toNearbyHospitalsRequest();

        return newNearbyHospital;
    }
    @Override
    public NearbyHospitalsRequest updateNearbyHospital(UUID id, NearbyHospitalsRequest nearbyHospitalsRequest) {
        NearbyHospitals nearbyHospitals = nearbyHospitalsRepository.findById(id)
                .orElseThrow(() -> new NearbyHospitalsNotFoundException("Nearby hospital not found with given ID"));

        nearbyHospitals.setName(nearbyHospitalsRequest.getName());

        try {
            NearbyHospitals updated = nearbyHospitalsRepository.save(nearbyHospitals);
            return updated.toNearbyHospitalsRequest();
        } catch (Exception e) {
            throw new NearbyHospitalsApiException("Problem during updating nearby hospital", e);
        }
    }

    @Override
    public void deleteNearbyHospital(UUID id) {
        nearbyHospitalsRepository.findById(id).orElseThrow(() ->
                new NearbyHospitalsNotFoundException("Nearby hospital not found with given ID"));
        try {
            nearbyHospitalsRepository.deleteById(id);
        } catch (Exception e) {
            throw new NearbyHospitalsApiException("Problem during deleting nearby hospital", e);
        }

    }

    private void validateDuplicate(UUID id, String name) {
        Optional<NearbyHospitals> existing = nearbyHospitalsRepository.findByName(name);

        if (existing.isPresent()) {
            NearbyHospitals nearbyHospitals = existing.get();

            if (!nearbyHospitals.getId().equals(id)) {
                throw new NearbyHospitalsAlreadyExistsException(
                        "Nearby hospital with name '" + name + "' already exists"
                );
            }
        }
    }
}
