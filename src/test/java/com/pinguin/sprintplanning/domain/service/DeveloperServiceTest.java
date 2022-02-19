package com.pinguin.sprintplanning.domain.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.pinguin.sprintplanning.domain.exception.DeveloperException;
import com.pinguin.sprintplanning.domain.model.DeveloperEntity;
import com.pinguin.sprintplanning.domain.repository.DeveloperRepository;
import com.pinguin.sprintplanning.domain.service.impl.DeveloperServiceImpl;
import com.pinguin.sprintplanning.domain.util.TestData;
import com.pinguin.sprintplanning.model.DeveloperDto;
import com.pinguin.sprintplanning.model.DevelopersDto;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 * DeveloperService Tester.
 *
 * @author Abdelghany Marzouk
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class DeveloperServiceTest {

  @Mock
  private DeveloperRepository developerRepository;
  private DeveloperService developerService;

  private final TestData testData = new TestData();

  @BeforeEach
  void setup() {
    developerService = new DeveloperServiceImpl(developerRepository);
  }


  @Test
  void testAddDeveloper() {
    DeveloperDto developerDto = testData.createDeveloperDto();
    DeveloperEntity developerEntity = testData.createDeveloperEntity();

    when(developerRepository.findByDeveloperName(any())).thenReturn(Optional.empty());
    when(developerRepository.save(any())).thenReturn(developerEntity);

    UUID developerId = developerService.addDeveloper(developerDto);

    Assertions.assertEquals(developerEntity.getDeveloperId(), developerId);
  }

  @Test
  void testAddDeveloper_WithExistName() {
    DeveloperDto developerDto = testData.createDeveloperDto();
    DeveloperEntity developerEntity = testData.createDeveloperEntity();

    when(developerRepository.findByDeveloperName(any())).thenReturn(Optional.of(developerEntity));

    DeveloperException thrown = assertThrows(DeveloperException.class,
        () -> developerService.addDeveloper(developerDto));

    assertNotNull(thrown);
  }

  @Test
  void testGetDeveloperById() {
    DeveloperEntity developerEntity = testData.createDeveloperEntity();

    when(developerRepository.findById(any())).thenReturn(Optional.of(developerEntity));

    DeveloperDto developerDto = developerService.getDeveloperById(UUID.randomUUID());

    Assertions.assertEquals(developerEntity.getDeveloperId(), developerDto.getDeveloperId());
    Assertions.assertEquals(developerEntity.getDeveloperName(), developerDto.getName());
  }

  @Test
  void testGetDeveloperById_NoDeveloperFound() {

    when(developerRepository.findById(any())).thenReturn(Optional.empty());

    DeveloperException thrown = assertThrows(DeveloperException.class,
        () -> developerService.getDeveloperById(UUID.randomUUID()));

    assertNotNull(thrown);
  }

  @Test
  void testGetNumberOfDevelopers() {

    when(developerRepository.count()).thenReturn(5L);

    long numberOfDevelopers = developerService.getNumberOfDevelopers();

    Assertions.assertEquals(5L, numberOfDevelopers);
  }

  @Test
  void testGetNumberOfDevelopers_NoDevelopers() {

    when(developerRepository.count()).thenReturn(0L);

    DeveloperException thrown = assertThrows(DeveloperException.class,
        () -> developerService.getNumberOfDevelopers());

    assertNotNull(thrown);
  }

  @Test
  void testDeleteDeveloper() {

    doNothing().when(developerRepository).deleteById(any());

    Assertions.assertDoesNotThrow(() -> developerService.deleteDeveloper(UUID.randomUUID()));

  }

  @Test
  void testUpdateDeveloper() {
    DeveloperDto developerDto = testData.createDeveloperDto();
    developerDto.setName("hassan");
    DeveloperEntity developerEntity = testData.createDeveloperEntity();

    when(developerRepository.findById(any())).thenReturn(Optional.of(developerEntity));
    when(developerRepository.findByDeveloperName(any())).thenReturn(Optional.empty());
    when(developerRepository.save(any())).thenReturn(developerEntity);

    Assertions.assertDoesNotThrow(
        () -> developerService.updateDeveloper(UUID.randomUUID(), developerDto));
  }

  @Test
  void testUpdateDeveloper_UpdateWithoutChangeName() {
    DeveloperDto developerDto = testData.createDeveloperDto();
    DeveloperEntity developerEntity = testData.createDeveloperEntity();

    when(developerRepository.findById(any())).thenReturn(Optional.of(developerEntity));

    DeveloperException thrown = assertThrows(DeveloperException.class,
        () -> developerService.updateDeveloper(UUID.randomUUID(), developerDto));

    assertNotNull(thrown);
  }

  @Test
  void testUpdateDeveloper_NoDeveloperFound() {
    DeveloperDto developerDto = testData.createDeveloperDto();
    when(developerRepository.findById(any())).thenReturn(Optional.empty());

    DeveloperException thrown = assertThrows(DeveloperException.class,
        () -> developerService.updateDeveloper(UUID.randomUUID(), developerDto));

    assertNotNull(thrown);
  }

  @Test
  void testUpdateDeveloper_UpdateWithNameAlreadyExist() {
    DeveloperDto developerDto = testData.createDeveloperDto();
    developerDto.setName("hassan");
    DeveloperEntity developerEntity = testData.createDeveloperEntity();

    when(developerRepository.findById(any())).thenReturn(Optional.of(developerEntity));
    when(developerRepository.findByDeveloperName(any())).thenReturn(Optional.of(developerEntity));

    DeveloperException thrown = assertThrows(DeveloperException.class,
        () -> developerService.updateDeveloper(UUID.randomUUID(), developerDto));

    assertNotNull(thrown);
  }

  @Test
  void testGetAllDevelopers() {
    int pageNumber = 1, pageSize = 10;
    DeveloperEntity developerEntity = testData.createDeveloperEntity();
    Page<DeveloperEntity> developerEntityPage = new PageImpl<>(Collections.singletonList(developerEntity));

    when(developerRepository.findAll(any(PageRequest.class))).thenReturn(developerEntityPage);

    DevelopersDto developersDto = developerService.getAllDevelopers(pageNumber, pageSize);

    Assertions.assertEquals(developerEntityPage.getTotalPages(),
        developersDto.getPagination().getTotal());
    Assertions.assertEquals(pageNumber, developersDto.getPagination().getPageNumber());
    Assertions.assertEquals(developerEntityPage.getSize(), developersDto.getPagination().getPageSize());
    Assertions.assertEquals(developerEntityPage.getContent().size(),
        developersDto.getDevelopers().size());
  }
  @Test
  void testGetReceivedMessages_InvalidPageNumber() {
    int pageNumber = -1, pageSize = 10;

    DeveloperException thrown = assertThrows(DeveloperException.class,
        () -> developerService.getAllDevelopers(pageNumber, pageSize));

    assertNotNull(thrown);
  }

  @Test
  void testGetReceivedMessages_InvalidPageSize() {
    int pageNumber = 1, pageSize = 1000;

    DeveloperException thrown = assertThrows(DeveloperException.class,
        () -> developerService.getAllDevelopers(pageNumber, pageSize));

    assertNotNull(thrown);
  }

}
