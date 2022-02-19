package com.pinguin.sprintplanning.domain.mapper;


import com.pinguin.sprintplanning.domain.model.DeveloperEntity;
import com.pinguin.sprintplanning.model.DeveloperDto;

import java.util.Optional;

public class DeveloperMapper {

  private DeveloperMapper() {
  }

  public static DeveloperEntity dtoToEntity(DeveloperDto developerDto) {
    DeveloperEntity developerEntity = new DeveloperEntity();

    developerEntity.setDeveloperName(developerDto.getName());

    return developerEntity;
  }

  public static DeveloperDto entityToDto(DeveloperEntity developerEntity) {
    DeveloperDto developerDto = new DeveloperDto();

    developerDto.setName(developerEntity.getDeveloperName());
    developerDto.setDeveloperId(developerEntity.getDeveloperId());

    return developerDto;
  }
}

