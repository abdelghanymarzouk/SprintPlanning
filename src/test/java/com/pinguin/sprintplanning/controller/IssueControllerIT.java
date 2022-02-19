package com.pinguin.sprintplanning.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinguin.sprintplanning.SprintPlanningApplication;
import com.pinguin.sprintplanning.domain.util.TestData;
import com.pinguin.sprintplanning.model.BugDto;
import com.pinguin.sprintplanning.model.DeveloperDto;
import com.pinguin.sprintplanning.model.StatusDto;
import com.pinguin.sprintplanning.model.StoryDto;
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
 * IssueController Tester.
 *
 * @author Abdelghany Marzouk
 * @version 1.0
 */
@SpringBootTest(classes = {SprintPlanningApplication.class})
class IssueControllerIT {


  private static final String BUG_ENDPOINT = "/api/sprint-planning-service/bug";
  private static final String BUG_ENDPOINT_WITH_PARAM = "/api/sprint-planning-service/bug/{bugId}";
  private static final String STORY_ENDPOINT = "/api/sprint-planning-service/story";
  private static final String DELETE_ISSUE_ENDPOINT = "/api/sprint-planning-service/deleteIssue/{issueId}";
  private static final String STORY_ENDPOINT_WITH_PARAM = "/api/sprint-planning-service/story/{storyId}";
  private static final String GET_ALL_ISSUES_ENDPOINT = "/api/sprint-planning-service/getAllIssues?pageNumber=1&pageSize=10";
  private static final String GET_ALL_ISSUES_ENDPOINT_WITH_INVALID_PAGE_SIZE = "/api/sprint-planning-service/getAllIssues?pageNumber=1&pageSize=77";

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
  void testAddBug() throws Exception {
    BugDto bugDto = testData.createBugDto();
    String body = objectMapper.writeValueAsString(bugDto);

    RequestBuilder request = testData.requestBuilder(HttpMethod.POST, BUG_ENDPOINT,
        Optional.of(body), null);
    ResultActions resultActions = mockMvc.perform(request);

    resultActions
        .andExpect(status().isCreated());

  }

  @Test
  void testAddStory() throws Exception {
    StoryDto storyDto = testData.createStoryDto();
    String body = objectMapper.writeValueAsString(storyDto);

    RequestBuilder request = testData.requestBuilder(HttpMethod.POST, STORY_ENDPOINT,
        Optional.of(body), null);
    ResultActions resultActions = mockMvc.perform(request);

    resultActions
        .andExpect(status().isCreated());

  }

  @Test
  void testAddStory_InvalidEstimatedPoints() throws Exception {
    StoryDto storyDto = testData.createStoryDto();
    storyDto.setEstimatedPoint(15);
    String body = objectMapper.writeValueAsString(storyDto);

    RequestBuilder request = testData.requestBuilder(HttpMethod.POST, STORY_ENDPOINT,
        Optional.of(body), null);
    ResultActions resultActions = mockMvc.perform(request);

    resultActions
        .andExpect(status().isBadRequest());

  }

  @Test
  void testDeleteIssue() throws Exception {
    StoryDto storyDto = testData.createStoryDto();
    String body = objectMapper.writeValueAsString(storyDto);

    RequestBuilder request = testData.requestBuilder(HttpMethod.POST, STORY_ENDPOINT,
        Optional.of(body), null);
    ResultActions resultActions = mockMvc.perform(request);
    String issueId = resultActions.andReturn().getResponse().getContentAsString().replace("\"", "");

    request = testData.requestBuilder(HttpMethod.DELETE, DELETE_ISSUE_ENDPOINT,
        Optional.empty(), issueId);

    resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isNoContent());

  }

  @Test
  void testDeleteIssue_IssueNotFound() throws Exception {
    RequestBuilder request = testData.requestBuilder(HttpMethod.DELETE, DELETE_ISSUE_ENDPOINT,
        Optional.empty(), UUID.randomUUID().toString());

    ResultActions resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isBadRequest());

  }


  @Test
  void testUpdateBug() throws Exception {
    BugDto bugDto = testData.createBugDto();
    String body = objectMapper.writeValueAsString(bugDto);

    RequestBuilder request = testData.requestBuilder(HttpMethod.POST, BUG_ENDPOINT,
        Optional.of(body), null);
    ResultActions resultActions = mockMvc.perform(request);
    String issueId = resultActions.andReturn().getResponse().getContentAsString().replace("\"", "");

    bugDto = testData.createBugDto();
    bugDto.setStatus(StatusDto.ESTIMATED);
    body = objectMapper.writeValueAsString(bugDto);

    request = testData.requestBuilder(HttpMethod.PUT, BUG_ENDPOINT_WITH_PARAM,
        Optional.of(body), issueId);

    resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isNoContent());

  }

  @Test
  void testUpdateBug_BugNotFound() throws Exception {
    DeveloperDto developerDto = testData.createDeveloperDto();
    String body = objectMapper.writeValueAsString(developerDto);

    RequestBuilder request = testData.requestBuilder(HttpMethod.PUT, BUG_ENDPOINT_WITH_PARAM,
        Optional.of(body), UUID.randomUUID().toString());

    ResultActions resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isBadRequest());

  }

  @Test
  void testUpdateStory() throws Exception {
    StoryDto storyDto = testData.createStoryDto();
    String body = objectMapper.writeValueAsString(storyDto);

    RequestBuilder request = testData.requestBuilder(HttpMethod.POST, STORY_ENDPOINT,
        Optional.of(body), null);
    ResultActions resultActions = mockMvc.perform(request);
    String issueId = resultActions.andReturn().getResponse().getContentAsString().replace("\"", "");

    storyDto = testData.createStoryDto();
    storyDto.setStatus(StatusDto.COMPLETED);
    body = objectMapper.writeValueAsString(storyDto);

    request = testData.requestBuilder(HttpMethod.PUT, STORY_ENDPOINT_WITH_PARAM,
        Optional.of(body), issueId);

    resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isNoContent());

  }

  @Test
  void testUpdateStory_StoryNotFound() throws Exception {
    StoryDto storyDto = testData.createStoryDto();
    String body = objectMapper.writeValueAsString(storyDto);

    RequestBuilder request = testData.requestBuilder(HttpMethod.PUT, STORY_ENDPOINT_WITH_PARAM,
        Optional.of(body), UUID.randomUUID().toString());

    ResultActions resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isBadRequest());

  }

  @Test
  void testGetAllIssues() throws Exception {
    RequestBuilder request = testData.requestBuilder(HttpMethod.GET, GET_ALL_ISSUES_ENDPOINT,
        Optional.empty(), null);

    ResultActions resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isOk());

  }

  @Test
  void testGetAllDevelopers_InvalidPageNumber() throws Exception {
    RequestBuilder request = testData
        .requestBuilder(HttpMethod.GET, GET_ALL_ISSUES_ENDPOINT_WITH_INVALID_PAGE_SIZE,
            Optional.empty(), null);

    ResultActions resultActions = mockMvc.perform(request);
    resultActions
        .andExpect(status().isBadRequest());

  }
}
