package com.pinguin.sprintplanning.domain.service;

import com.pinguin.sprintplanning.model.DeveloperDto;
import com.pinguin.sprintplanning.model.DevelopersDto;

import java.util.UUID;

public interface DeveloperService {


  UUID addDeveloper(DeveloperDto developerDto);

  void deleteDeveloper(UUID developerId);

  void updateDeveloper(UUID developerId, DeveloperDto developerDto);

  DevelopersDto getAllDevelopers(Integer pageNumber, Integer pageSize);

  DeveloperDto getDeveloperById(UUID developerId);

  long getNumberOfDevelopers();
}
