package com.registration.service;

import com.registration.request.CountryRequest;

import java.util.List;
import java.util.UUID;

public interface CountryService {

    List<CountryRequest> getAllCountries();
    CountryRequest getCountryById(UUID id);
    CountryRequest createCountry(CountryRequest countryRequest);
    CountryRequest updateCountry(UUID id, CountryRequest countryRequest);
    void deleteCountry(UUID id);
}
