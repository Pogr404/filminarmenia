package com.registration.repository;

import com.registration.entity.placefinder.PlaceType;
import com.registration.entity.placefinder.RoadQuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoadQualityRepository extends JpaRepository<RoadQuality, UUID> {
    @Query("SELECT rq.id FROM RoadQuality rq WHERE rq.name = :name")
    UUID findIdByName(@Param("name") String name);
    String findNameById(UUID id);
    Optional<RoadQuality> findByName(String name);

    boolean existsByName(String name);
}
