package com.registration.request;

import com.registration.entity.placefinder.*;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceRequest {

    @Hidden
    private UUID id;

    private String name;

    private String coordinates;

    private PlaceType placeType;

    private CountryEntity country;

    private ClimateZone climateZone;

    private NearbyHotels nearbyHotels;

    private NearbyHospitals nearbyHospitals;

    private NearbyPharmacy nearbyPharmacies;

    private GasStation gasStations;

    private FoodPlaces foodPlaces;

    private List<Photo> photos;

    private Photo360 photo360;

    private String description;

    private RoadQuality roadQuality;

    private List<TransportType> transportType;

    private Boolean recommended;

    private Double distanceFromHotel;

    private Double distanceFromHospital;

    private Double distanceFromPharmacy;

    private Double distanceFromGasStation;

    private Double distanceFromFoodPlace;
}
