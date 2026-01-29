package com.registration.entity.placefinder;

import com.registration.constants.DatabaseConstants;
import com.registration.request.RoadQualityRequest;
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
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = DatabaseConstants.ROAD_QUALITY_TABLE_NAME)
public class RoadQuality {

    @Id
    @Column(name = "road_quality_id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    private String name;


    public RoadQuality(RoadQualityRequest request) {
        id = request.getId();
        name = request.getName();

    }

    public RoadQualityRequest toRoadQualityRequest() {
        return new RoadQualityRequest(id, name);
    }

}
