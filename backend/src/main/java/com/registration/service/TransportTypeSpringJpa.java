package com.registration.service;

import com.registration.entity.placefinder.CountryEntity;
import com.registration.entity.placefinder.PlaceType;
import com.registration.entity.placefinder.TransportType;
import com.registration.ex.climatezoneexception.ClimateZoneApiException;
import com.registration.ex.climatezoneexception.ClimateZoneNotFoundException;
import com.registration.ex.countryexception.CountryAlreadyExistException;
import com.registration.ex.countryexception.CountryApiException;
import com.registration.ex.countryexception.CountryNotFoundException;
import com.registration.ex.placeexceptions.PlaceNotFoundException;
import com.registration.ex.transporttypeexcpetion.TransportTypeAlreadyExistsException;
import com.registration.ex.transporttypeexcpetion.TransportTypeApiException;
import com.registration.ex.transporttypeexcpetion.TransportTypeNotFoundException;
import com.registration.ex.userexceptions.UserApiException;
import com.registration.ex.userexceptions.UserBadRequestException;
import com.registration.repository.TransportTypeRepository;
import com.registration.request.CountryRequest;
import com.registration.request.PlaceTypeRequest;
import com.registration.request.TransportTypeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransportTypeSpringJpa implements TransportTypeService{

    @Autowired
    private TransportTypeRepository transportTypeRepository;


    @Override
    public List<TransportTypeRequest> getAllTransportTypes() {
        try {
            return transportTypeRepository.findAll()
                    .stream()
                    .map(TransportType::toTransportTypeRequest)
                    .toList();
        } catch (Exception e) {
            throw new TransportTypeApiException("Problem during getting all transports", e);
        }
    }


    @Override
    public TransportTypeRequest getTransportTypeById(UUID id) {
        TransportType transportType = transportTypeRepository.findById(id)
                .orElseThrow(() -> new TransportTypeNotFoundException("Transport type not found with given ID"));
        return transportType.toTransportTypeRequest();
    }
    @Override
    public TransportTypeRequest createTransportType(TransportTypeRequest transportTypeRequest) {


        if (transportTypeRequest.getId() != null) {
            throw new UserBadRequestException("User ID must be null");
        }

        validateDuplicate(null, transportTypeRequest.getName());
        transportTypeRequest.setName(transportTypeRequest.getName());

        TransportType transportType = new TransportType(transportTypeRequest);

        try {
            transportType = transportTypeRepository.save(transportType);
        } catch (Exception e) {
            throw new UserApiException("Problem during creating transport type", e);
        }

        TransportTypeRequest newTransportType = transportType.toTransportTypeRequest();

        return newTransportType;
    }

    @Override
    public TransportTypeRequest updateTransportType(UUID id, TransportTypeRequest transportTypeRequest) {
        TransportType transportType = transportTypeRepository.findById(id)
                .orElseThrow(() -> new TransportTypeNotFoundException("Transport type not found with given ID"));

        transportType.setName(transportTypeRequest.getName());

        try {
            TransportType updated = transportTypeRepository.save(transportType);
            return updated.toTransportTypeRequest();
        } catch (Exception e) {
            throw new TransportTypeApiException("Problem during updating transport type", e);
        }

    }

    @Override
    public void deleteTransportType(UUID id) {
        transportTypeRepository.findById(id).orElseThrow(() ->
                new TransportTypeNotFoundException("Transport type not found with given ID"));
        try {
            transportTypeRepository.deleteById(id);
        } catch (Exception e) {
            throw new TransportTypeApiException("Problem during deleting transport type", e);
        }

    }
    private void validateDuplicate(UUID id, String name) {
        Optional<TransportType> existing = transportTypeRepository.findByName(name);

        if (existing.isPresent()) {
            TransportType transportType = existing.get();

            if (!transportType.getId().equals(id)) {
                throw new TransportTypeAlreadyExistsException(
                        "Transport type with name '" + name + "' already exists"
                );
            }
        }
    }

}
