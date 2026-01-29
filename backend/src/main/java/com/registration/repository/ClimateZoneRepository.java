package com.registration.repository;

import com.registration.entity.placefinder.ClimateZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClimateZoneRepository extends JpaRepository<ClimateZone, UUID> {
    @Query("SELECT cz.id FROM ClimateZone cz WHERE cz.name = :name")
    UUID findIdByName(@Param("name") String name);

    String findNameById(UUID id);

    Optional<ClimateZone> findByName(String name);

    boolean existsByName(String name);

}

