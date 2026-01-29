package com.registration.repository;

import com.registration.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity getByEmail(String email);

    UserEntity getByEmailAndIdNot(String email, UUID id);

}
