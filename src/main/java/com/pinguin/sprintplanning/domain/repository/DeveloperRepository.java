package com.pinguin.sprintplanning.domain.repository;

import com.pinguin.sprintplanning.domain.model.DeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface DeveloperRepository extends JpaRepository<DeveloperEntity, UUID>,
    JpaSpecificationExecutor<DeveloperEntity> {

  Optional<DeveloperEntity> findByDeveloperName(String developerName);
}
