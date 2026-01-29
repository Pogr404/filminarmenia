package com.registration.api.rest;

import com.registration.constants.RoutConstants;
import com.registration.request.CountryRequest;
import com.registration.security.RequiredAdminUser;
import com.registration.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.reflections.Reflections.log;

@RestController
@RequestMapping(RoutConstants.BASE_URL + "${platform.version}" + RoutConstants.COUNTRIES)
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<CountryRequest> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{id}")
    public CountryRequest getCountryById(@PathVariable UUID id) {
        System.out.println("Received ID: " + id);
        return countryService.getCountryById(id);
    }
    @PostMapping
    @RequiredAdminUser
    public CountryRequest createCountry(@RequestBody @Valid CountryRequest countryRequest) {
        log.info("received request to country user");
        CountryRequest country = countryService.createCountry(countryRequest);
        log.info("Country created successfully");
        return country;
    }
    @PutMapping("/{id}")
    @RequiredAdminUser
    public CountryRequest updateCountry(@PathVariable UUID id, @RequestBody CountryRequest countryRequest) {
        return countryService.updateCountry(id, countryRequest);
    }
    @DeleteMapping("/{id}")
    @RequiredAdminUser
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCountry(@PathVariable UUID id) {
        countryService.deleteCountry(id);
    }
}
