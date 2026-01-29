package com.registration.api.rest;

import com.registration.constants.RoutConstants;
import com.registration.request.CountryRequest;
import com.registration.request.TransportTypeRequest;
import com.registration.security.RequiredAdminUser;
import com.registration.service.TransportTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.reflections.Reflections.log;

@RestController
@RequestMapping(RoutConstants.BASE_URL + "${platform.version}" + RoutConstants.TRANSPORT_TYPES)
public class TransportTypeController {

    @Autowired
    private TransportTypeService transportTypeService;

    @GetMapping
    public List<TransportTypeRequest> getAllTransportTypes() {
        return transportTypeService.getAllTransportTypes();
    }

    @GetMapping("/{id}")
    public TransportTypeRequest getTransportTypeById(@PathVariable UUID id) {
        System.out.println("Received ID: " + id);
        return transportTypeService.getTransportTypeById(id);
    }
    @PostMapping
    @RequiredAdminUser
    public TransportTypeRequest createTransportType(@RequestBody @Valid TransportTypeRequest transportTypeRequest) {
        log.info("received request to transport type user");
        TransportTypeRequest transportType = transportTypeService.createTransportType(transportTypeRequest);
        log.info("Transport type created successfully");
        return transportType;
    }
    @PutMapping("/{id}")
    @RequiredAdminUser
    public TransportTypeRequest updateTransportType(@PathVariable UUID id, @RequestBody TransportTypeRequest transportTypeRequest) {
        return transportTypeService.updateTransportType(id, transportTypeRequest);
    }
    @DeleteMapping("/{id}")
    @RequiredAdminUser
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransportType(@PathVariable UUID id) {
        transportTypeService.deleteTransportType(id);
    }
}
