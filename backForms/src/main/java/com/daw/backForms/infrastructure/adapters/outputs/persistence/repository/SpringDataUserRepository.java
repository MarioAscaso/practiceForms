package com.daw.backForms.infrastructure.adapters.outputs.persistence.repository;

import com.daw.backForms.infrastructure.adapters.outputs.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}