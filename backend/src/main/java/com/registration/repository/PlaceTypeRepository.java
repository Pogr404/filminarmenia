package com.registration.repository;

import com.registration.entity.placefinder.PlaceType;
import com.registration.entity.placefinder.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlaceTypeRepository extends JpaRepository<PlaceType, UUID> {

    @Query("SELECT pt.id FROM PlaceType pt WHERE pt.name = :name")
    UUID findIdByName(@Param("name") String name);
    String findNameById(UUID id);
    Optional<PlaceType> findByName(String name);

    boolean existsByName(String name);



}


