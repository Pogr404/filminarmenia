package com.registration.entity.placefinder;

import com.registration.constants.DatabaseConstants;
import com.registration.request.CountryRequest;
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
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = DatabaseConstants.COUNTRY_TABLE_NAME)
public class CountryEntity {

    @Id
    @Column(name = "country_id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    private String name;


    public CountryEntity(CountryRequest request) {
        id = request.getId();
        name = request.getName();

    }

    public CountryRequest toCountryRequest() {
        return new CountryRequest(id, name);

    }
}
