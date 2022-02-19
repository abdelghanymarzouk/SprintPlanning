package com.pinguin.sprintplanning.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinguin.sprintplanning.SprintPlanningApplication;
import com.pinguin.sprintplanning.domain.util.TestData;
import com.pinguin.sprintplanning.model.DeveloperDto;
import com.pinguin.sprintplanning.model.StoryDto;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(OrderAnnotation.class)
class SprintPlanningControllerIT {


  private static final String PLAN_ENDPOINT = "/api/sprint-planning-service/plan";
  private static final String DEVELOPER_ENDPOINT = "/api/sprint-planning-service/developer";
  private static final String STORY_ENDPOINT = "/api/sprint-planning-service/story";


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
  @Order(1)
  void testPlan_WithoutStories() throws Exception {
    RequestBuilder request = testData
        .requestBuilder(HttpMethod.GET, PLAN_ENDPOINT, Optional.empty(), null);
    ResultActions resultActions = mockMvc.perform(request);

    resultActions
        .andExpect(status().isBadRequest());

  }

  @Test
  @Order(2)
  void testPlan_WithoutDevelopers() throws Exception {
    StoryDto storyDto = testData.createStoryDto();
    String body = objectMapper.writeValueAsString(storyDto);

    RequestBuilder request = testData
        .requestBuilder(HttpMethod.POST, STORY_ENDPOINT, Optional.of(body), null);
    mockMvc.perform(request);

    request = testData
        .requestBuilder(HttpMethod.GET, PLAN_ENDPOINT, Optional.empty(), null);
    ResultActions resultActions = mockMvc.perform(request);

    resultActions
        .andExpect(status().isBadRequest());

  }

  @Test
  @Order(3)
  void testPlan() throws Exception {
    DeveloperDto developerDto = testData.createDeveloperDto();
    String body = objectMapper.writeValueAsString(developerDto);

    RequestBuilder request = testData
        .requestBuilder(HttpMethod.POST, DEVELOPER_ENDPOINT, Optional.of(body),
            null);
    mockMvc.perform(request);

    StoryDto storyDto = testData.createStoryDto();
    body = objectMapper.writeValueAsString(storyDto);

    request = testData
        .requestBuilder(HttpMethod.POST, STORY_ENDPOINT, Optional.of(body), null);
    mockMvc.perform(request);

    request = testData
        .requestBuilder(HttpMethod.GET, PLAN_ENDPOINT, Optional.empty(), null);
    ResultActions resultActions = mockMvc.perform(request);

    resultActions
        .andExpect(status().isOk());

  }

}
