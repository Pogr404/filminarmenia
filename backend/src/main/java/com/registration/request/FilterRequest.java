package com.registration.request;


import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterRequest {

    @Hidden
    private UUID id;

    private List<Long> placeTypeId;

    private Long countryId;

    private List<Long> roadQualityId;

    private List<Long> transportTypeId;

    private Integer maxDistanceFromCity;

    private Integer maxDistanceFromGasStation;

    private Integer maxDistanceFromHotel;

    private Integer maxDistanceFromHospital;

    private Integer maxDistanceFromPharmacy;

    private Integer maxDistanceFromFoodPlace;

    private List<String> ClimateZone;
}
