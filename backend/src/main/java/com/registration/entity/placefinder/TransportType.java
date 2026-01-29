package com.registration.entity.placefinder;

import com.registration.constants.DatabaseConstants;
import com.registration.request.TransportTypeRequest;
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
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = DatabaseConstants.TRANSPORT_TYPE_TABLE_NAME)
public class TransportType {

    @Id
    @Column(name = "transport_type_id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    private String name;


    public TransportType(TransportTypeRequest request) {
        id = request.getId();
        name = request.getName();

    }

    public TransportTypeRequest toTransportTypeRequest() {
        return new TransportTypeRequest(id, name);
    }

}
