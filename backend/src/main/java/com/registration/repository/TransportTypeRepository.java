package com.registration.repository;

import com.registration.entity.placefinder.CountryEntity;
import com.registration.entity.placefinder.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransportTypeRepository extends JpaRepository<TransportType, UUID> {
    @Query("SELECT c.id FROM TransportType c WHERE c.name = :name")
    UUID findIdByName(@Param("name") String name);
    String findNameById(UUID id);
    Optional<TransportType> findByName(String name);

    boolean existsByName(String name);
}
