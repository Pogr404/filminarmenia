package com.registration.service;

import com.registration.entity.placefinder.NearbyHotels;
import com.registration.ex.nearbyhotelsexception.NearbyHotelsAlreadyExistsException;
import com.registration.ex.nearbyhotelsexception.NearbyHotelsApiException;
import com.registration.ex.nearbyhotelsexception.NearbyHotelsBadRequestException;
import com.registration.ex.nearbyhotelsexception.NearbyHotelsNotFoundException;
import com.registration.repository.NearbyHotelsRepository;
import com.registration.request.NearbyHotelsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NearbyHotelsSpringJpa implements NearbyHotelsService{

    @Autowired
    private NearbyHotelsRepository nearbyHotelsRepository;


    @Override
    public List<NearbyHotelsRequest> getNearbyHotels() {
        try {
            return nearbyHotelsRepository.findAll()
                    .stream()
                    .map(NearbyHotels::toNearbyHotelsRequest)
                    .toList();
        } catch (Exception e) {
            throw new NearbyHotelsApiException("Problem during getting all nearby hotels", e);
        }
    }


    @Override
    public NearbyHotelsRequest getNearbyHotelById(UUID id) {
        NearbyHotels nearbyHotels = nearbyHotelsRepository.findById(id)
                .orElseThrow(() -> new NearbyHotelsNotFoundException("Nearby hotel not found with given ID"));
        return nearbyHotels.toNearbyHotelsRequest();
    }


    @Override
    public NearbyHotelsRequest createNearbyHotel(NearbyHotelsRequest nearbyHotelsRequest) {
        if (nearbyHotelsRequest.getId() != null) {
            throw new NearbyHotelsBadRequestException("Nearby hotel ID must be null");
        }

        // Check for duplicate name
        Optional<NearbyHotels> existing = nearbyHotelsRepository.findByName(nearbyHotelsRequest.getName());
        if (existing.isPresent()) {
            throw new NearbyHotelsAlreadyExistsException(
                    "Nearby hotel with name '" + nearbyHotelsRequest.getName() + "' already exists"
            );
        }

        // Use builder pattern to create entity
        NearbyHotels nearbyHotels = NearbyHotels.builder()
                .name(nearbyHotelsRequest.getName())
                .rate(nearbyHotelsRequest.getRate())
                .website(nearbyHotelsRequest.getWebsite())
                .build();

        try {
            NearbyHotels savedHotel = nearbyHotelsRepository.save(nearbyHotels);
            return savedHotel.toNearbyHotelsRequest();
        } catch (Exception e) {
            throw new NearbyHotelsApiException("Problem during creating nearby hotel", e);
        }
    }

    @Override
    public NearbyHotelsRequest updateNearbyHotel(UUID id, NearbyHotelsRequest nearbyHotelsRequest) {
        NearbyHotels nearbyHotels = nearbyHotelsRepository.findById(id)
                .orElseThrow(() -> new NearbyHotelsNotFoundException("Nearby hotel not found with given ID"));

        // Check for duplicate name (excluding current record)
        validateDuplicate(id, nearbyHotelsRequest.getName());

        nearbyHotels.setName(nearbyHotelsRequest.getName());
        nearbyHotels.setRate(nearbyHotelsRequest.getRate());
        nearbyHotels.setWebsite(nearbyHotelsRequest.getWebsite());

        try {
            NearbyHotels updated = nearbyHotelsRepository.save(nearbyHotels);
            return updated.toNearbyHotelsRequest();
        } catch (Exception e) {
            throw new NearbyHotelsApiException("Problem during updating nearby hotel", e);
        }
    }

    @Override
    public void deleteNearbyHotel(UUID id) {
        nearbyHotelsRepository.findById(id).orElseThrow(() ->
                new NearbyHotelsNotFoundException("Nearby hotel not found with given ID"));
        try {
            nearbyHotelsRepository.deleteById(id);
        } catch (Exception e) {
            throw new NearbyHotelsApiException("Problem during deleting nearby hotel", e);
        }
    }

    private void validateDuplicate(UUID id, String name) {
        Optional<NearbyHotels> existing = nearbyHotelsRepository.findByName(name);

        if (existing.isPresent()) {
            NearbyHotels nearbyHotels = existing.get();

            if (!nearbyHotels.getId().equals(id)) {
                throw new NearbyHotelsAlreadyExistsException(
                        "Nearby hotel with name '" + name + "' already exists"
                );
            }
        }
    }
}