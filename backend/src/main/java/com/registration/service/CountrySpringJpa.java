package com.registration.service;

import com.registration.entity.placefinder.CountryEntity;
import com.registration.ex.countryexception.CountryAlreadyExistException;
import com.registration.ex.countryexception.CountryApiException;
import com.registration.ex.countryexception.CountryBadRequestException;
import com.registration.ex.countryexception.CountryNotFoundException;
import com.registration.repository.CountryRepository;
import com.registration.request.CountryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CountrySpringJpa implements CountryService{

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<CountryRequest> getAllCountries() {
        try {
            return countryRepository.findAll()
                    .stream()
                    .map(com.registration.entity.placefinder.CountryEntity::toCountryRequest)
                    .toList();
        } catch (Exception e) {
            throw new CountryApiException("Problem during getting all countries", e);
        }
    }


    @Override
    public CountryRequest getCountryById(UUID id) {
        CountryEntity countryEntity = countryRepository.findById(id)
                .orElseThrow(() -> new CountryApiException("Country not found with given ID"));
        return countryEntity.toCountryRequest();
    }

    @Override
    public CountryRequest createCountry(CountryRequest countryRequest) {


        if (countryRequest.getId() != null) {
            throw new CountryBadRequestException("Country ID must be null");
        }

        validateDuplicate(null, countryRequest.getName());
        countryRequest.setName(countryRequest.getName());

        CountryEntity country= new CountryEntity(countryRequest);

        try {
            country = countryRepository.save(country);
        } catch (Exception e) {
            throw new CountryApiException("Problem during creating country", e);
        }

        CountryRequest newCountry = country.toCountryRequest();

        return newCountry;
    }

    @Override
    public CountryRequest updateCountry(UUID id, CountryRequest countryRequest) {
        CountryEntity countryEntity= countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException("Country not found with given ID"));

        countryEntity.setName(countryRequest.getName());

        try {
            CountryEntity updated = countryRepository.save(countryEntity);
            return updated.toCountryRequest();
        } catch (Exception e) {
            throw new CountryApiException("Problem during updating countries", e);
        }

    }

    @Override
    public void deleteCountry(UUID id) {
        countryRepository.findById(id).orElseThrow(() ->
                new CountryNotFoundException("Country not found with given ID"));
        try {
            countryRepository.deleteById(id);
        } catch (Exception e) {
            throw new CountryApiException("Problem during deleting country", e);
        }

    }
    private void validateDuplicate(UUID id, String name) {
        Optional<CountryEntity> existing = countryRepository.findByName(name);

        if (existing.isPresent()) {
            CountryEntity countryEntity= existing.get();

            if (!countryEntity.getId().equals(id)) {
                throw new CountryAlreadyExistException(
                        "Country with name '" + name + "' already exists"
                );
            }
        }
    }
}
