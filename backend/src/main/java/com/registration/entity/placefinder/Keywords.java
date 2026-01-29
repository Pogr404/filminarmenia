package com.registration.entity.placefinder;

import com.registration.constants.DatabaseConstants;
import com.registration.request.KeywordsRequest;
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
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = DatabaseConstants.KEYWORDS_TABLE_NAME)
public class Keywords {

    @Id
    @Column(name = "keyword_id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    @Column(name = "name")
    private String name;

    // Removed the incorrect @ManyToOne relationship with PlaceEntity
    // Keywords should not directly reference places - that's handled by PlaceKeyword junction table

    public Keywords(KeywordsRequest request) {
        id = request.getId();
        name = request.getName();
    }

    public KeywordsRequest toKeywordsRequest() {
        return new KeywordsRequest(id, name);
    }
}
