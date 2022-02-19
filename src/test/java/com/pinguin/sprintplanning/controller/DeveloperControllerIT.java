package com.pinguin.sprintplanning.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinguin.sprintplanning.SprintPlanningApplication;
import com.pinguin.sprintplanning.domain.util.TestData;
import com.pinguin.sprintplanning.model.DeveloperDto;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * DeveloperController Tester.
 *
 * @author Abdelghany Marzouk
 * @version 1.0
 */
@SpringBootTest(classes = {SprintPlanningApplication.class})
class DeveloperControllerIT {


  private static final String DEVELOPER_ENDPOINT = "/api/sprint-planning-service/developer";
  private static final String DEVELOPER_GET_ALL_ENDPOINT = "/api/sprint-planning-service/developer?pageNumber=-1&pageSize=10";
  private static final String DEVELOPER_ENDPOINT_WITH_PARAM = "/api/sprint-planning-service/developer/{developerId}";

  private final TestData testData = new TestData();

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private ObjectMapper objectMapper;

  private MockMvc mockMvc;

  @BeforeEach
  public void beforeEach() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .alwaysDo(print())
        .build();
  }

  @Test
  void testAddDeveloper() throws Exception {
    DeveloperDto developerDto = testData.createDeveloperDto();
    developerDto.setName("newDeveloper");
    String body = objectMapper.writeValueAsString(developerDto);

    RequestBuilder request = testData.requestBuilder(HttpMethod.POST, DEVELOPER_ENDPOINT,
        Optional.of(body), null);
    ResultActions resultActions = mockMvc.perform(request);

    resultActions
        .andExpect(status().isCreated());

  }

  @Test
  void testAddDeveloper_withExistingName() throws Exception {
    DeveloperDto developerDto = testData.createDeveloperDto();
    String body = objectMapper.writeValueAsString(developerDto);

    RequestBuilder request = testData.requestBuilder(HttpMethod.POST, DEVELOPER_ENDPOINT,
        Optional.of(body), null);
    mockMvc.perform(request);

    ResultActions resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isBadRequest());

  }

  @Test
  void testDeleteDeveloper() throws Exception {
    DeveloperDto developerDto = testData.createDeveloperDto();
    developerDto.setName("deleteDeveloper");
    String body = objectMapper.writeValueAsString(developerDto);

    RequestBuilder request = testData.requestBuilder(HttpMethod.POST, DEVELOPER_ENDPOINT,
        Optional.of(body), null);
    ResultActions resultActions = mockMvc.perform(request);
    String developerId  = resultActions.andReturn().getResponse().getContentAsString().replace("\"","");

    request = testData.requestBuilder(HttpMethod.DELETE, DEVELOPER_ENDPOINT_WITH_PARAM,
        Optional.empty(), developerId);

    resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isNoContent());

  }

  @Test
  void testDeleteDeveloper_DeveloperNotFound() throws Exception {
    RequestBuilder request = testData.requestBuilder(HttpMethod.DELETE, DEVELOPER_ENDPOINT_WITH_PARAM,
        Optional.empty(), UUID.randomUUID().toString());

    ResultActions resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isBadRequest());

  }


  @Test
  void testUpdateDeveloper() throws Exception {
    DeveloperDto developerDto = testData.createDeveloperDto();
    developerDto.setName("Moaaz");
    String body = objectMapper.writeValueAsString(developerDto);

    RequestBuilder request = testData.requestBuilder(HttpMethod.POST, DEVELOPER_ENDPOINT,
        Optional.of(body), null);
    ResultActions resultActions = mockMvc.perform(request);
    String developerId  = resultActions.andReturn().getResponse().getContentAsString().replace("\"","");

    developerDto = testData.createDeveloperDto();
    developerDto.setName("updateName");
    body = objectMapper.writeValueAsString(developerDto);

    request = testData.requestBuilder(HttpMethod.PUT, DEVELOPER_ENDPOINT_WITH_PARAM,
        Optional.of(body), developerId);

    resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isNoContent());

  }

  @Test
  void testUpdateDeveloper_DeveloperNotFound() throws Exception {
    DeveloperDto developerDto = testData.createDeveloperDto();
    String body = objectMapper.writeValueAsString(developerDto);

    RequestBuilder request = testData.requestBuilder(HttpMethod.PUT, DEVELOPER_ENDPOINT_WITH_PARAM,
        Optional.of(body), UUID.randomUUID().toString());

    ResultActions resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isBadRequest());

  }

  @Test
  void testGetSpecificDeveloper() throws Exception {
    DeveloperDto developerDto = testData.createDeveloperDto();
    String body = objectMapper.writeValueAsString(developerDto);

    RequestBuilder request = testData.requestBuilder(HttpMethod.POST, DEVELOPER_ENDPOINT,
        Optional.of(body), null);
    ResultActions resultActions = mockMvc.perform(request);
    String developerId  = resultActions.andReturn().getResponse().getContentAsString().replace("\"","");

    request = testData.requestBuilder(HttpMethod.GET, DEVELOPER_ENDPOINT_WITH_PARAM,
        Optional.empty(), developerId);

    resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isOk());

  }

  @Test
  void testGetSpecificDeveloper_DeveloperNotFound() throws Exception {
    RequestBuilder request = testData.requestBuilder(HttpMethod.GET, DEVELOPER_ENDPOINT_WITH_PARAM,
        Optional.empty(), UUID.randomUUID().toString());

    ResultActions resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isBadRequest());

  }

  @Test
  void testGetAllDevelopers() throws Exception {
    RequestBuilder request = testData.requestBuilder(HttpMethod.GET, DEVELOPER_ENDPOINT,
        Optional.empty(), null);

    ResultActions resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isOk());

  }

  @Test
  void testGetAllDevelopers_InvalidPageNumber() throws Exception {
    RequestBuilder request = testData.requestBuilder(HttpMethod.GET, DEVELOPER_GET_ALL_ENDPOINT,
        Optional.empty(), null);

    ResultActions resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isBadRequest());

  }
}
