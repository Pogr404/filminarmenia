package com.registration.service;

import com.registration.entity.placefinder.GasStation;
import com.registration.ex.gasstationexception.GasStationAlreadyExistsException;
import com.registration.ex.gasstationexception.GasStationApiException;
import com.registration.ex.gasstationexception.GasStationBadRequestException;
import com.registration.ex.gasstationexception.GasStationNotFoundException;
import com.registration.repository.GasStationsRepository;
import com.registration.request.GasStationsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GasStationSpringJpa implements GasStationService {

    @Autowired
    private GasStationsRepository gasStationsRepository;


    @Override
    public List<GasStationsRequest> getGasStations() {
        try {
            return gasStationsRepository.findAll()
                    .stream()
                    .map(GasStation::toGasStationRequest)
                    .toList();
        } catch (Exception e) {
            throw new GasStationApiException("Problem during getting all gas stations", e);
        }
    }


    @Override
    public GasStationsRequest getGasStationById(UUID id) {
        GasStation gasStation = gasStationsRepository.findById(id)
                .orElseThrow(() -> new GasStationNotFoundException("Gas station not found with given ID"));
        return gasStation.toGasStationRequest();
    }
    @Override
    public GasStationsRequest createGasStation(GasStationsRequest gasStationsRequest) {
        if (gasStationsRequest.getId() != null) {
            throw new GasStationBadRequestException("Gas station ID must be null");
        }

        validateDuplicate(null, gasStationsRequest.getName());
        gasStationsRequest.setName(gasStationsRequest.getName());

        GasStation gasStations = new GasStation(gasStationsRequest);

        try {
            gasStations = gasStationsRepository.save(gasStations);
        } catch (Exception e) {
            throw new GasStationApiException("Problem during creating gas station", e);
        }

        GasStationsRequest newGasStation = gasStations.toGasStationRequest();

        return newGasStation;
    }
    @Override
    public GasStationsRequest updateGasStation(UUID id, GasStationsRequest gasStationsRequest) {
        GasStation gasStations = gasStationsRepository.findById(id)
                .orElseThrow(() -> new GasStationNotFoundException("Gas Station not found with given ID"));

        gasStations.setName(gasStationsRequest.getName());

        try {
            GasStation updated= gasStationsRepository.save(gasStations);
            return updated.toGasStationRequest();
        } catch (Exception e) {
            throw new GasStationApiException("Problem during updating gas station", e);
        }
    }

    @Override
    public void deleteGasStation(UUID id) {
        gasStationsRepository.findById(id).orElseThrow(() ->
                new GasStationNotFoundException("Gas station not found with given ID"));
        try {
            gasStationsRepository.deleteById(id);
        } catch (Exception e) {
            throw new GasStationApiException("Problem during deleting gas station", e);
        }

    }

    private void validateDuplicate(UUID id, String name) {
        Optional<GasStation> existing = gasStationsRepository.findByName(name);

        if (existing.isPresent()) {
            GasStation gasStations = existing.get();

            if (!gasStations.getId().equals(id)) {
                throw new GasStationAlreadyExistsException(
                        "Gas station with name '" + name + "' already exists"
                );
            }
        }
    }
}
