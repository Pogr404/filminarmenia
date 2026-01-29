package com.registration.entity.placefinder;

import com.registration.constants.DatabaseConstants;
import com.registration.request.Photo360Request;
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
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = DatabaseConstants.PHOTO_360_TABLE_NAME)
public class Photo360 {

    @Id
    @Column(name = "photo_360_id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    private String name;

    private float size;

    private String path;

    private String description;

    public Photo360(Photo360Request request) {
        id = request.getId();
        name = request.getName();

    }

    public Photo360Request toPhoto360Request() {
        return new Photo360Request(id, name);

    }

}
