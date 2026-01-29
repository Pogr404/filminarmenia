package com.registration.entity.searchingwith;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filter {

    @Id
    @Column(name = "filter_id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    @ElementCollection
    private List<Long> placeTypeId;

    private Long countryId;

    @ElementCollection
    private List<Long> roadQualityId;

    @ElementCollection
    private List<Long> transportTypeId;

    private Integer maxDistanceFromCity;

    private Integer maxDistanceFromGasStation;

    private Integer maxDistanceFromHotel;

    private Integer maxDistanceFromHospital;

    private Integer maxDistanceFromPharmacy;

    private Integer maxDistanceFromFoodPlace;

    @ElementCollection
    private List<String> ClimateZone;

}