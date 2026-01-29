package com.registration.repository;

import com.registration.entity.PlaceEntity;
import com.registration.entity.placefinder.ClimateZone;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, UUID> {



    List<PlaceEntity> findAllByRecommendedTrue();

 //   @Query("SELECT p FROM PlaceEntity p " +
 //           "LEFT JOIN FETCH p.placeType " +
 //           "LEFT JOIN FETCH p.country " +
 //           "LEFT JOIN FETCH p.climateZone " +
 //           "LEFT JOIN FETCH P."
 //           "WHERE ...")
    List<PlaceEntity> findAll(Specification<PlaceEntity> spec);
    Optional<PlaceEntity> findByName(String name);
//    Optional<PlaceEntity> findByKeywords(List<String> keywords);
}
