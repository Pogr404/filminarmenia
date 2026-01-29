package com.registration.service;

import com.registration.request.CountryRequest;
import com.registration.request.TransportTypeRequest;

import java.util.List;
import java.util.UUID;

public interface TransportTypeService {

    List<TransportTypeRequest> getAllTransportTypes();
    TransportTypeRequest getTransportTypeById(UUID id);
    TransportTypeRequest createTransportType(TransportTypeRequest transportTypeRequest);
    TransportTypeRequest updateTransportType(UUID id, TransportTypeRequest transportTypeRequest);
    void deleteTransportType(UUID id);

}
