package com.registration.entity.placefinder;

import com.registration.constants.DatabaseConstants;
import com.registration.request.PlaceTypeRequest;
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
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = DatabaseConstants.PLACE_TYPE_TABLE_NAME)
public class PlaceType {

    @Id
    @Column(name = "place_type_id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    private String name;


    public PlaceType(PlaceTypeRequest request) {
        id = request.getId();
        name = request.getName();

    }

    public PlaceTypeRequest toPlaceTypeRequest() {
        return new PlaceTypeRequest(id, name);

    }
}

