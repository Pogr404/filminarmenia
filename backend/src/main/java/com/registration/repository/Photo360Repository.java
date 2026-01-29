package com.registration.repository;

import com.registration.entity.placefinder.Photo360;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Photo360Repository extends JpaRepository<Photo360, UUID> {
}
