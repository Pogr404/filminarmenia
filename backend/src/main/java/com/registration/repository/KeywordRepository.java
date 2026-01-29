package com.registration.repository;

import com.registration.entity.placefinder.Keywords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KeywordRepository extends JpaRepository<Keywords, UUID> {

    // Find keywords by name (case-insensitive)
    @Query("SELECT k FROM Keywords k WHERE LOWER(k.name) IN :keywordNames")
    List<Keywords> findByNameInIgnoreCase(@Param("keywordNames") List<String> keywordNames);
}
