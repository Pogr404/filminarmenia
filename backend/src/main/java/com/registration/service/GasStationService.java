package com.registration.service;

import com.registration.request.GasStationsRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface GasStationService {

    List<GasStationsRequest> getGasStations();
    GasStationsRequest getGasStationById(UUID id);
    GasStationsRequest createGasStation(@Valid GasStationsRequest gasStationsRequest);
    GasStationsRequest updateGasStation(UUID id, GasStationsRequest gasStationsRequest);

    void deleteGasStation(UUID id);
}
