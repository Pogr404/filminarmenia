package com.registration.repository;

import com.registration.entity.placefinder.Keywords;
import com.registration.entity.placefinder.PlaceKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlaceKeywordRepository extends JpaRepository<PlaceKeyword, UUID> {

    List<Keywords> findByPlaceId(UUID placeId);

    // Find places that have ALL the specified keywords
    @Query("SELECT DISTINCT pk.place.id FROM PlaceKeyword pk WHERE pk.keyword.id IN :keywordIds " +
            "GROUP BY pk.place.id HAVING COUNT(DISTINCT pk.keyword.id) = :keywordCount")
    List<UUID> findPlaceIdsByAllKeywords(@Param("keywordIds") List<UUID> keywordIds,
                                         @Param("keywordCount") long keywordCount);

    // Find places that have ANY of the specified keywords
    @Query("SELECT DISTINCT pk.place.id FROM PlaceKeyword pk WHERE pk.keyword.id IN :keywordIds")
    List<UUID> findPlaceIdsByAnyKeywords(@Param("keywordIds") List<UUID> keywordIds);
}
