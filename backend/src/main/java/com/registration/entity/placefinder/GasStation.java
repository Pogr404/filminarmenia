package com.registration.entity.placefinder;

import com.registration.constants.DatabaseConstants;
import com.registration.request.GasStationsRequest;
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
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = DatabaseConstants.GAS_STATION_TABLE_NAME)
public class GasStation {

    @Id
    @Column(name = "gas_station_id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    private String name;

    public GasStation(GasStationsRequest request) {
        id = request.getId();
        name = request.getName();

    }

    public GasStationsRequest toGasStationRequest() {
        return new GasStationsRequest(id, name);

    }

}
