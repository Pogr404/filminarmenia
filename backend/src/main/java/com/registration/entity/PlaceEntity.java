package com.registration.entity;

import com.registration.constants.DatabaseConstants;
import com.registration.entity.placefinder.*;
import com.registration.request.PlaceRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = DatabaseConstants.PLACE_TABLE_NAME)
public class PlaceEntity {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "coordinates")
    private String coordinates;

    @Column(name = "place_type_id")
    private UUID placeTypeId;

    @Column(name = "country_id")
    private UUID countryId;

    @Column(name = "climate_zone_id")
    private UUID climateZoneId;

    @Column(name = "photo_360_id")
    private UUID photo360Id;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "road_quality_id")
    private UUID roadQualityId;

    @Column(name = "recommended")
    private Boolean recommended;

    @Column(name = "nearby_hotels_id")
    private UUID nearbyHotelsId;

    @Column(name = "distance_from_hotel")
    private Double distanceFromHotel;

    @Column(name = "nearby_hospitals_id")
    private UUID nearbyHospitalsId;

    @Column(name = "distance_from_hospital")
    private Double distanceFromHospital;

    @Column(name = "nearby_pharmacies_id")
    private UUID nearbyPharmaciesId;

    @Column(name = "distance_from_pharmacy")
    private Double distanceFromPharmacy;

    @Column(name = "gas_stations_id")
    private UUID gasStationsId;

    @Column(name = "distance_from_gas_station")
    private Double distanceFromGasStation;

    @Column(name = "food_places_id")
    private UUID foodPlacesId;

    @Column(name = "distance_from_food_place")
    private Double distanceFromFoodPlace;

    @Column(name = "photos_id")
    private UUID photosId;

    @Column(name = "transport_type_id")
    private UUID transportTypeId;

    // ✅ Added mapping to PlaceKeyword (join table)
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaceKeyword> placeKeywords = new ArrayList<>();

    public PlaceEntity(PlaceRequest request) {
        this.id = request.getId();
        this.name = request.getName();
        this.coordinates = request.getCoordinates();
        this.description = request.getDescription();
        this.recommended = request.getRecommended();

        this.placeTypeId = request.getPlaceType() != null ? request.getPlaceType().getId() : null;
        this.countryId = request.getCountry() != null ? request.getCountry().getId() : null;
        this.climateZoneId = request.getClimateZone() != null ? request.getClimateZone().getId() : null;
        this.photo360Id = request.getPhoto360() != null ? request.getPhoto360().getId() : null;
        this.roadQualityId = request.getRoadQuality() != null ? request.getRoadQuality().getId() : null;
        this.nearbyHotelsId = request.getNearbyHotels() != null ? request.getNearbyHotels().getId() : null;
        this.distanceFromHotel = (request.getDistanceFromHotel() != null) ? request.getDistanceFromHotel() : null;
        this.nearbyHospitalsId = request.getNearbyHospitals() != null ? request.getNearbyHospitals().getId() : null;
        this.distanceFromHospital = (request.getDistanceFromHospital() != null) ? request.getDistanceFromHospital() : null;
        this.nearbyPharmaciesId = request.getNearbyPharmacies() != null ? request.getNearbyPharmacies().getId() : null;
        this.distanceFromPharmacy = (request.getDistanceFromPharmacy() != null) ? request.getDistanceFromPharmacy() : null;
        this.gasStationsId = request.getGasStations() != null ? request.getGasStations().getId() : null;
        this.distanceFromGasStation = (request.getDistanceFromGasStation() != null) ? request.getDistanceFromGasStation() : null;
        this.foodPlacesId = request.getFoodPlaces() != null ? request.getFoodPlaces().getId() : null;
        this.distanceFromFoodPlace = (request.getDistanceFromFoodPlace() != null) ? request.getDistanceFromFoodPlace() : null;
        this.photosId = request.getPhotos() != null && !request.getPhotos().isEmpty()
                ? request.getPhotos().get(0).getId() : null;
        this.transportTypeId = request.getTransportType() != null && !request.getTransportType().isEmpty()
                ? request.getTransportType().get(0).getId() : null;
    }

    public PlaceRequest toPlaceRequest(Optional<PlaceType> placeType,
                                       Optional<ClimateZone> climateZone,
                                       Optional<CountryEntity> countryEntity,
                                       Optional<Photo360> photo360,
                                       Optional<RoadQuality> roadQuality,
                                       Optional<NearbyHotels> nearbyHotels,
                                       Optional<NearbyHospitals> nearbyHospitals,
                                       Optional<NearbyPharmacy> nearbyPharmacy,
                                       Optional<GasStation> gasStation,
                                       Optional<FoodPlaces> foodPlaces,
                                       Optional<List<Photo>> photos,
                                       Optional<List<TransportType>> transportTypes) {
        return PlaceRequest.builder()
                .id(id)
                .name(name)
                .coordinates(coordinates)
                .description(description)
                .recommended(recommended)
                .placeType(PlaceType.builder().id(placeType.get().getId()).name(placeType.get().getName()).build())
                .climateZone(ClimateZone.builder().id(climateZone.get().getId()).name(climateZone.get().getName()).build())
                .country(CountryEntity.builder().id(countryEntity.get().getId()).name(countryEntity.get().getName()).build())
                .photo360(Photo360.builder().id(photo360.get().getId()).name(photo360.get().getName()).size(photo360.get().getSize()).path(photo360.get().getPath()).description(photo360.get().getDescription()).build())
                .roadQuality(RoadQuality.builder().id(roadQuality.get().getId()).name(roadQuality.get().getName()).build())
                .nearbyHotels(NearbyHotels.builder().id(nearbyHotels.get().getId()).name(nearbyHotels.get().getName()).rate(nearbyHotels.get().getRate()).website(nearbyHotels.get().getWebsite()).build())
                .distanceFromHotel(distanceFromHotel)
                .nearbyHospitals(NearbyHospitals.builder().id(nearbyHospitals.get().getId()).name(nearbyHospitals.get().getName()).build())
                .distanceFromHospital(distanceFromHospital)
                .nearbyPharmacies(NearbyPharmacy.builder().id(nearbyPharmacy.get().getId()).name(nearbyPharmacy.get().getName()).build())
                .distanceFromPharmacy(distanceFromPharmacy)
                .gasStations(GasStation.builder().id(gasStation.get().getId()).name(gasStation.get().getName()).build())
                .distanceFromGasStation(distanceFromGasStation)
                .foodPlaces(FoodPlaces.builder().id(foodPlaces.get().getId()).name(foodPlaces.get().getName()).build())
                .distanceFromFoodPlace(distanceFromFoodPlace)
                .photos(photos.orElse(List.of()).stream().map(photo -> Photo.builder()
                        .id(photo.getId())
                        .name(photo.getName())
                        .size(photo.getSize())
                        .path(photo.getPath())
                        .build()).toList())
                .transportType(transportTypes.orElse(List.of()).stream().map(transportType -> TransportType.builder()
                        .id(transportType.getId())
                        .name(transportType.getName())
                        .build()).toList())
                .build();
    }

    public PlaceRequest toPlaceRequest() {
        return PlaceRequest.builder()
                .id(id)
                .name(name)
                .coordinates(coordinates)
                .description(description)
                .recommended(recommended)
                .placeType(placeTypeId != null ? PlaceType.builder().id(placeTypeId).build() : null)
                .country(countryId != null ? CountryEntity.builder().id(countryId).build() : null)
                .climateZone(climateZoneId != null ? ClimateZone.builder().id(climateZoneId).build() : null)
                .photo360(photo360Id != null ? Photo360.builder().id(photo360Id).build() : null)
                .roadQuality(roadQualityId != null ? RoadQuality.builder().id(roadQualityId).build() : null)
                .nearbyHotels(nearbyHotelsId != null ? NearbyHotels.builder().id(nearbyHotelsId).build() : null)
                .nearbyHospitals(nearbyHospitalsId != null ? NearbyHospitals.builder().id(nearbyHospitalsId).build() : null)
                .nearbyPharmacies(nearbyPharmaciesId != null ? NearbyPharmacy.builder().id(nearbyPharmaciesId).build() : null)
                .gasStations(gasStationsId != null ? GasStation.builder().id(gasStationsId).build() : null)
                .foodPlaces(foodPlacesId != null ? FoodPlaces.builder().id(foodPlacesId).build() : null)
                .photos(photosId != null ? List.of(Photo.builder().id(photosId).build()) : List.of())
                .transportType(transportTypeId != null ? List.of(TransportType.builder().id(transportTypeId).build()) : List.of())
                .build();
    }
}
