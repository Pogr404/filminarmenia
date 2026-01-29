package com.registration.entity.placefinder;

import com.registration.constants.DatabaseConstants;
import com.registration.request.ClimateZoneRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = DatabaseConstants.CLIMATE_ZONE_TABLE_NAME)
public class ClimateZone {

    @Id
    @Column(name = "climate_zone_id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    private String name;


    public ClimateZone(ClimateZoneRequest request) {
        id = request.getId();
        name = request.getName();

    }

    public ClimateZoneRequest toClimateZoneRequest() {
        return new ClimateZoneRequest(id, name);

    }
}



///   Arctic,
//    Sub_arctic,
//    Temperate,
//    Equatorial,
//    Sub_equatorial,
//    Tropical,
//    Sub_tropical,
//    Sub_antarctic,
//    Antarctic