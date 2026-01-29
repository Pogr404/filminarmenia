package com.registration.service;

import com.registration.entity.PlaceEntity;
import com.registration.entity.placefinder.*;
import com.registration.ex.climatezoneexception.ClimateZoneAlreadyExistException;
import com.registration.ex.climatezoneexception.ClimateZoneApiException;
import com.registration.ex.climatezoneexception.ClimateZoneBadRequestException;
import com.registration.ex.climatezoneexception.ClimateZoneNotFoundException;
import com.registration.ex.placeexceptions.PlaceApiException;
import com.registration.ex.placeexceptions.PlaceBadRequestException;
import com.registration.ex.placeexceptions.PlaceNotFoundException;
import com.registration.repository.*;
import com.registration.request.ClimateZoneRequest;
import com.registration.request.PlaceRequest;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PlaceSpringJpa implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceTypeRepository placeTypeRepository;

    @Autowired
    private ClimateZoneRepository climateZoneRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RoadQualityRepository roadQualityRepository;

    @Autowired
    private NearbyHotelsRepository nearbyHotelsRepository;

    @Autowired
    private NearbyHospitalsRepository nearbyHospitalsRepository;

    @Autowired
    private NearbyPharmaciesRepository nearbyPharmaciesRepository;

    @Autowired
    private GasStationsRepository gasStationsRepository;

    @Autowired
    private FoodPlacesRepository foodPlacesRepository;

    @Autowired
    private Photo360Repository photo360Repository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private TransportTypeRepository transportTypeRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private PlaceKeywordRepository placeKeywordRepository;

    // --------------------------- getAllPlaces ---------------------------
    @Override
    public List<PlaceRequest> getAllPlaces() {
        List<PlaceRequest> requests = new ArrayList<>();
        try {
            List<PlaceEntity> list = placeRepository.findAll()
                    .stream()
                    .toList();

            for (PlaceEntity place : list) {
                PlaceRequest request = buildPlaceRequest(place);
                if (request == null) {
                    log.error("Failed to convert place {} to response object", place.getId());
                    throw new IllegalStateException("Failed to generate response for place");
                }
                requests.add(request);
            }
            return requests;
        } catch (Exception e) {
            throw new PlaceApiException("Problem during getting all places", e);
        }
    }

    // --------------------------- getPlaceById ---------------------------
    @Override
    @Transactional(readOnly = true)
    public PlaceRequest getPlaceById(UUID placeId) {
        log.info("Fetching place with ID: {}", placeId);

        PlaceEntity placeEntity = placeRepository.findById(placeId)
                .orElseThrow(() -> {
                    log.error("Place not found with ID: {}", placeId);
                    return new PlaceNotFoundException("Place not found with ID: " + placeId);
                });

        PlaceRequest request = buildPlaceRequest(placeEntity);

        if (request == null) {
            log.error("Failed to convert place {} to response object", placeId);
            throw new IllegalStateException("Failed to generate response for place");
        }

        log.debug("Successfully built response for place {}", placeId);
        return request;
    }

    // --------------------------- getRecommendedPlaces ---------------------------
    @Override
    public List<PlaceRequest> getRecommendedPlaces() {
        List<PlaceRequest> requests = new ArrayList<>();
        try {
            List<PlaceEntity> list = placeRepository.findAllByRecommendedTrue()
                    .stream()
                    .toList();

            for (PlaceEntity place : list) {
                PlaceRequest request = buildPlaceRequest(place);
                if (request == null) {
                    log.error("Failed to convert place {} to response object", place.getId());
                    throw new IllegalStateException("Failed to generate response for place");
                }
                requests.add(request);
            }
            return requests;
        } catch (Exception e) {
            throw new PlaceApiException("Problem during getting recommended places", e);
        }
    }

    // --------------------------- searchPlaces ---------------------------
    @Override
    public List<PlaceRequest> searchPlaces(
            UUID placeTypeId,
            UUID countryId,
            UUID roadQualityId,
            UUID nearbyHotelsId,
            UUID nearbyHospitalsId,
            UUID nearbyPharmaciesId,
            UUID gasStationsId,
            UUID foodPlacesId,
            UUID climateZoneId
    ) {
        Specification<PlaceEntity> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (placeTypeId != null) predicates.add(cb.equal(root.get("placeTypeId"), placeTypeId));
            if (countryId != null) predicates.add(cb.equal(root.get("countryId"), countryId));
            if (roadQualityId != null) predicates.add(cb.equal(root.get("roadQualityId"), roadQualityId));
            if (nearbyHotelsId != null) predicates.add(cb.equal(root.get("nearbyHotelsId"), nearbyHotelsId));
            if (nearbyHospitalsId != null) predicates.add(cb.equal(root.get("nearbyHospitalsId"), nearbyHospitalsId));
            if (nearbyPharmaciesId != null) predicates.add(cb.equal(root.get("nearbyPharmaciesId"), nearbyPharmaciesId));
            if (gasStationsId != null) predicates.add(cb.equal(root.get("gasStationsId"), gasStationsId));
            if (foodPlacesId != null) predicates.add(cb.equal(root.get("foodPlacesId"), foodPlacesId));
            if (climateZoneId != null) predicates.add(cb.equal(root.get("climateZoneId"), climateZoneId));

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        List<PlaceEntity> places = placeRepository.findAll(spec);
        return places.stream()
                .map(this::buildPlaceRequest)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // --------------------------- searchPlacesByKeywords ---------------------------
    @Override
    public List<PlaceRequest> searchPlacesByKeywords(List<String> keywordValues) {
        if (keywordValues == null || keywordValues.isEmpty()) {
            return Collections.emptyList();
        }

        try {
            List<String> lowerCaseKeywords = keywordValues.stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

            List<Keywords> matchingKeywords = keywordRepository.findByNameInIgnoreCase(lowerCaseKeywords);

            if (matchingKeywords.isEmpty()) {
                log.info("No keywords found matching: {}", keywordValues);
                return Collections.emptyList();
            }

            List<UUID> keywordIds = matchingKeywords.stream()
                    .map(Keywords::getId)
                    .collect(Collectors.toList());

            log.info("Found {} matching keywords for search terms: {}", keywordIds.size(), keywordValues);

            List<UUID> placeIds = placeKeywordRepository.findPlaceIdsByAllKeywords(
                    keywordIds, keywordIds.size());

            if (placeIds.isEmpty()) {
                log.info("No places found with all keywords: {}", keywordValues);
                return Collections.emptyList();
            }

            List<PlaceEntity> places = placeRepository.findAllById(placeIds);

            List<PlaceRequest> result = places.stream()
                    .map(this::buildPlaceRequest)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            log.info("Found {} places matching keywords: {}", result.size(), keywordValues);
            return result;

        } catch (Exception e) {
            log.error("Error searching places by keywords {}: {}", keywordValues, e.getMessage());
            throw new PlaceApiException("Error searching places by keywords", e);
        }
    }

    @Override
    public PlaceRequest createPlace(PlaceRequest placeRequest) {


        if (placeRequest.getId() != null) {
            throw new PlaceBadRequestException("Place ID must be null");
        }

        validateDuplicate(null, placeRequest.getName());
        placeRequest.setName(placeRequest.getName());
        placeRequest.setCoordinates(placeRequest.getCoordinates());
        placeRequest.setPlaceType(placeRequest.getPlaceType());
        placeRequest.setCountry(placeRequest.getCountry());
        placeRequest.setClimateZone(placeRequest.getClimateZone());
        placeRequest.setNearbyHotels(placeRequest.getNearbyHotels());
        placeRequest.setNearbyHospitals(placeRequest.getNearbyHospitals());
        placeRequest.setNearbyPharmacies(placeRequest.getNearbyPharmacies());
        placeRequest.setGasStations(placeRequest.getGasStations());
        placeRequest.setFoodPlaces(placeRequest.getFoodPlaces());
        placeRequest.setPhotos(placeRequest.getPhotos());
        placeRequest.setPhoto360(placeRequest.getPhoto360());
        placeRequest.setDescription(placeRequest.getDescription());
        placeRequest.setRoadQuality(placeRequest.getRoadQuality());
        placeRequest.setTransportType(placeRequest.getTransportType());
        placeRequest.setRecommended(placeRequest.getRecommended());
        placeRequest.setDistanceFromHotel(placeRequest.getDistanceFromHotel());
        placeRequest.setDistanceFromHospital(placeRequest.getDistanceFromHospital());
        placeRequest.setDistanceFromPharmacy(placeRequest.getDistanceFromPharmacy());
        placeRequest.setDistanceFromGasStation(placeRequest.getDistanceFromGasStation());
        placeRequest.setDistanceFromFoodPlace(placeRequest.getDistanceFromFoodPlace());

        PlaceEntity  place = new PlaceEntity(placeRequest);

        try {
            place = placeRepository.save(place);
        } catch (Exception e) {
            throw new PlaceApiException("Problem during creating place", e);
        }

        PlaceRequest newPlace = place.toPlaceRequest();

        return newPlace;
    }



    @Override
    public PlaceRequest updatePlace(UUID id, PlaceRequest placeRequest) {
        PlaceEntity placeEntity = placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException("Place not found with given ID"));

        placeEntity.setName(placeRequest.getName());
        placeEntity.setCoordinates(placeRequest.getCoordinates());
        placeEntity.setPlaceTypeId(placeEntity.getPlaceTypeId());
        placeEntity.setCountryId(placeEntity.getCountryId());
        placeEntity.setClimateZoneId(placeEntity.getClimateZoneId());
        placeEntity.setNearbyHotelsId(placeEntity.getNearbyHotelsId());
        placeEntity.setNearbyHospitalsId(placeEntity.getNearbyHospitalsId());
        placeEntity.setNearbyPharmaciesId(placeEntity.getNearbyPharmaciesId());
        placeEntity.setGasStationsId(placeEntity.getGasStationsId());
        placeEntity.setFoodPlacesId(placeEntity.getFoodPlacesId());
        placeEntity.setPhotosId(placeEntity.getPhotosId());
        placeEntity.setPhoto360Id(placeEntity.getPhoto360Id());
        placeEntity.setDescription(placeRequest.getDescription());
        placeEntity.setRoadQualityId(placeEntity.getRoadQualityId());
        placeEntity.setTransportTypeId(placeEntity.getTransportTypeId());
        placeEntity.setRecommended(placeRequest.getRecommended());
        placeEntity.setDistanceFromHotel(placeRequest.getDistanceFromHotel());
        placeEntity.setDistanceFromHospital(placeRequest.getDistanceFromHospital());
        placeEntity.setDistanceFromPharmacy(placeRequest.getDistanceFromPharmacy());
        placeEntity.setDistanceFromGasStation(placeRequest.getDistanceFromGasStation());
        placeEntity.setDistanceFromFoodPlace(placeRequest.getDistanceFromFoodPlace());

        try {
            PlaceEntity updated = placeRepository.save(placeEntity);
            return updated.toPlaceRequest();
        } catch (Exception e) {
            throw new PlaceApiException("Problem during updating place", e);
        }
    }

    @Override
    public void deletePlace(UUID id) {
        placeRepository.findById(id).orElseThrow(() ->
                new PlaceNotFoundException("Place not found with given ID"));
        try {
            placeRepository.deleteById(id);
        } catch (Exception e) {
            throw new PlaceApiException("Problem during deleting place", e);
        }
    }

    // --------------------------- Helper Methods ---------------------------
    private PlaceRequest buildPlaceRequest(PlaceEntity place) {
        PlaceType placeType = safeFindAndUnwrap(placeTypeRepository, place.getPlaceTypeId(), "PlaceType");
        ClimateZone climateZone = safeFindAndUnwrap(climateZoneRepository, place.getClimateZoneId(), "ClimateZone");
        CountryEntity country = safeFindAndUnwrap(countryRepository, place.getCountryId(), "Country");
        Photo360 photo360 = safeFindAndUnwrap(photo360Repository, place.getPhoto360Id(), "Photo360");
        RoadQuality roadQuality = safeFindAndUnwrap(roadQualityRepository, place.getRoadQualityId(), "RoadQuality");
        NearbyHotels nearbyHotels = safeFindAndUnwrap(nearbyHotelsRepository, place.getNearbyHotelsId(), "NearbyHotels");
        NearbyHospitals nearbyHospitals = safeFindAndUnwrap(nearbyHospitalsRepository, place.getNearbyHospitalsId(), "NearbyHospitals");
        NearbyPharmacy nearbyPharmacy = safeFindAndUnwrap(nearbyPharmaciesRepository, place.getNearbyPharmaciesId(), "NearbyPharmacy");
        GasStation gasStation = safeFindAndUnwrap(gasStationsRepository, place.getGasStationsId(), "GasStation");
        FoodPlaces foodPlaces = safeFindAndUnwrap(foodPlacesRepository, place.getFoodPlacesId(), "FoodPlaces");

        List<Photo> photos = safeFindAndConvertToList(photoRepository, place.getPhotosId(), "Photo");
        List<TransportType> transportTypes = safeFindAndConvertToList(transportTypeRepository, place.getTransportTypeId(), "TransportType");

        return place.toPlaceRequest(
                Optional.ofNullable(placeType),
                Optional.ofNullable(climateZone),
                Optional.ofNullable(country),
                Optional.ofNullable(photo360),
                Optional.ofNullable(roadQuality),
                Optional.ofNullable(nearbyHotels),
                Optional.ofNullable(nearbyHospitals),
                Optional.ofNullable(nearbyPharmacy),
                Optional.ofNullable(gasStation),
                Optional.ofNullable(foodPlaces),
                Optional.of(photos),
                Optional.of(transportTypes)
        );
    }

    private <T> T safeFindAndUnwrap(JpaRepository<T, UUID> repository, UUID id, String entityName) {
        if (id == null) {
            log.debug("{} ID is null - skipping lookup", entityName);
            return null;
        }

        try {
            return repository.findById(id)
                    .orElseGet(() -> {
                        log.warn("{} with ID {} not found in database", entityName, id);
                        return null;
                    });
        } catch (Exception e) {
            log.error("Error fetching {} with ID {}: {}", entityName, id, e.getMessage());
            return null;
        }
    }

    private <T> List<T> safeFindAndConvertToList(JpaRepository<T, UUID> repository, UUID id, String entityName) {
        if (id == null) {
            log.debug("{} ID is null - returning empty list", entityName);
            return Collections.emptyList();
        }

        try {
            return repository.findById(id)
                    .map(Collections::singletonList)
                    .orElseGet(() -> {
                        log.warn("{} with ID {} not found - returning empty list", entityName, id);
                        return Collections.emptyList();
                    });
        } catch (Exception e) {
            log.error("Error fetching {} with ID {}: {}", entityName, id, e.getMessage());
            return Collections.emptyList();
        }
    }

    private void validateDuplicate(UUID id, String name) {
        Optional<PlaceEntity> existing = placeRepository.findByName(name);

        if (existing.isPresent()) {
            PlaceEntity placeEntity = existing.get();

            if (!placeEntity.getId().equals(id)) {
                throw new ClimateZoneAlreadyExistException(
                        "ClimateZone with name '" + name + "' already exists"
                );
            }
        }
    }
}
