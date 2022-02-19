package com.pinguin.sprintplanning.domain.service.impl;

import com.pinguin.sprintplanning.domain.exception.DeveloperException;
import com.pinguin.sprintplanning.domain.mapper.DeveloperMapper;
import com.pinguin.sprintplanning.domain.model.DeveloperEntity;
import com.pinguin.sprintplanning.domain.repository.DeveloperRepository;
import com.pinguin.sprintplanning.domain.service.DeveloperService;
import com.pinguin.sprintplanning.model.DeveloperDto;
import com.pinguin.sprintplanning.model.DevelopersDto;
import com.pinguin.sprintplanning.model.PaginationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.pinguin.sprintplanning.domain.util.Constants.MAX_PAGE_SIZE;

@Service
@Slf4j
public class DeveloperServiceImpl implements DeveloperService {


  DeveloperRepository developerRepository;

  @Autowired
  public DeveloperServiceImpl(DeveloperRepository developerRepository) {
    this.developerRepository = developerRepository;
  }

  @Override
  public UUID addDeveloper(DeveloperDto developerDto) {
    Optional<DeveloperEntity> developerEntityOptional = developerRepository
        .findByDeveloperName(developerDto.getName());

    if (developerEntityOptional.isPresent()) {
      throw new DeveloperException("Name already exist");
    }

    DeveloperEntity developerEntity = DeveloperMapper.dtoToEntity(developerDto);
    developerEntity = developerRepository.save(developerEntity);

    return developerEntity.getDeveloperId();
  }

  @Override
  public void deleteDeveloper(UUID developerId) {
    developerRepository.deleteById(developerId);
  }

  @Override
  public void updateDeveloper(UUID developerId, DeveloperDto developerDto) {
    Optional<DeveloperEntity> developerEntityOptional = developerRepository.findById(developerId);

    if (developerEntityOptional.isEmpty()) {
      throw new DeveloperException("Developer not found");
    }

    if (developerEntityOptional.get().getDeveloperName().equalsIgnoreCase(developerDto.getName())) {
      throw new DeveloperException("There is no changes to update");
    }

    developerEntityOptional = developerRepository.findByDeveloperName(developerDto.getName());

    if (developerEntityOptional.isPresent()) {
      throw new DeveloperException("This name already exist");
    }

    DeveloperEntity developerEntity = DeveloperMapper.dtoToEntity(developerDto);
    developerEntity.setDeveloperId(developerId);

    developerRepository.save(developerEntity);
  }

  @Override
  public DevelopersDto getAllDevelopers(Integer pageNumber, Integer pageSize) {
    validatePageNumberAndPageSize(pageNumber, pageSize);

    pageNumber = pageNumber - 1;
    Page<DeveloperEntity> developerEntityPage = developerRepository
        .findAll(PageRequest.of(pageNumber, pageSize));

    return mapAndPrepareResponse(pageNumber, developerEntityPage);
  }

  @Override
  public DeveloperDto getDeveloperById(UUID developerId) {
    Optional<DeveloperEntity> developerEntityOptional = developerRepository.findById(developerId);

    if (developerEntityOptional.isEmpty()) {
      throw new DeveloperException("Developer not found");
    }

    return DeveloperMapper.entityToDto(developerEntityOptional.get());
  }

  @Override
  public long getNumberOfDevelopers() {
    long numOfDevelopers = developerRepository.count();
    if(numOfDevelopers == 0){
      throw new DeveloperException("There si no developers found to plan");
    }
    return numOfDevelopers;
  }

  private void validatePageNumberAndPageSize(Integer pageNumber, Integer pageSize) {
    if (pageNumber != null && pageNumber < 1) {
      throw new DeveloperException("Page number must be greater than or equal to 1");
    }

    if (pageSize != null && pageSize > MAX_PAGE_SIZE) {
      throw new DeveloperException("Page size must be less than or equal to " + MAX_PAGE_SIZE);
    }
  }

  private DevelopersDto mapAndPrepareResponse(Integer pageNumber,
      Page<DeveloperEntity> developerEntityPage) {
    List<DeveloperDto> developerDtoList = developerEntityPage.stream()
        .map(DeveloperMapper::entityToDto).collect(Collectors.toList());

    PaginationDto paginationDto = new PaginationDto();
    paginationDto.setPageNumber(pageNumber + 1);
    paginationDto.setPageSize(developerEntityPage.getSize());
    paginationDto.setTotal(developerEntityPage.getTotalPages());

    DevelopersDto developersDto = new DevelopersDto();
    developersDto.setPagination(paginationDto);
    developersDto.setDevelopers(developerDtoList);
    return developersDto;
  }

}
