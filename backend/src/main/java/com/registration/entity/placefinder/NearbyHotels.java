package com.registration.entity.placefinder;

import com.registration.constants.DatabaseConstants;
import com.registration.request.NearbyHotelsRequest;
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
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = DatabaseConstants.NEARBY_HOTELS_TABLE_NAME)
public class NearbyHotels {

    @Id
    @Column(name = "nearby_hotels_id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    private String name;

    private Float rate;

    private String website;

    public NearbyHotelsRequest toNearbyHotelsRequest() {
        return NearbyHotelsRequest.builder()
                .id(this.id)
                .name(this.name)
                .rate(this.rate)
                .website(this.website)
                .build();
    }
}