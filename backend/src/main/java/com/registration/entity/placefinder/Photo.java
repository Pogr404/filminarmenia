package com.registration.entity.placefinder;


import com.registration.constants.DatabaseConstants;
import com.registration.request.PhotoRequest;
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
@Table(schema = DatabaseConstants.SCHEMA_NAME, name = DatabaseConstants.PHOTOS_TABLE_NAME)
public class Photo {

    @Id
    @Column(name = "photo_id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    private String name;

    private String size;

    private String path;

    public Photo(PhotoRequest request) {
        id = request.getId();
        name = request.getName();

    }

    public PhotoRequest toPhotoRequest() {
        return new PhotoRequest(id, name);

    }

}
