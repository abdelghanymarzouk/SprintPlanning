package com.pinguin.sprintplanning.controller;

import com.pinguin.sprintplanning.domain.service.DeveloperService;
import com.pinguin.sprintplanning.model.DeveloperDto;
import com.pinguin.sprintplanning.model.DevelopersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
public class DeveloperController implements DeveloperApiDelegate {

  DeveloperService developerService;

  @Autowired
  public DeveloperController(DeveloperService developerService) {
    this.developerService = developerService;
  }

  @Override
  public ResponseEntity<Object> addDeveloper(DeveloperDto developerDto) {
    UUID developerId = developerService.addDeveloper(developerDto);
    return new ResponseEntity<>(developerId, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> deleteDeveloper(UUID developerId) {
    developerService.deleteDeveloper(developerId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<DevelopersDto> getAllDevelopers(Integer pageNumber, Integer pageSize) {
    DevelopersDto developersDto = developerService.getAllDevelopers(pageNumber, pageSize);
    return ResponseEntity.ok(developersDto);
  }

  @Override
  public ResponseEntity<DeveloperDto> getSpecificDeveloper(UUID developerId) {
    DeveloperDto developerDto = developerService.getDeveloperById(developerId);
    return ResponseEntity.ok(developerDto);
  }

  @Override
  public ResponseEntity<Void> updateDeveloper(UUID developerId, DeveloperDto developerDto) {
    developerService.updateDeveloper(developerId, developerDto);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
