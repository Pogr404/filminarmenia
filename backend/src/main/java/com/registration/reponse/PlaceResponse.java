package com.registration.reponse;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceResponse {

    private UUID id;
    private String name;
    private String coordinates;
    private String description;
    private Boolean recommended;

    private UUID placeTypeId;
    private UUID countryId;
    private UUID climateZoneId;
    private UUID photo360Id;
    private UUID roadQualityId;
    private UUID nearbyHotelsId;
    private UUID nearbyHospitalsId;
    private UUID nearbyPharmaciesId;
    private UUID gasStationsId;
    private UUID foodPlacesId;
    private UUID photosId;
    private UUID transportTypeId;
    private List<String> keywords;
}

