package com.pinguin.sprintplanning.domain.util;

import com.pinguin.sprintplanning.domain.model.DeveloperEntity;
import com.pinguin.sprintplanning.domain.model.StoryEntity;
import com.pinguin.sprintplanning.model.BugDto;
import com.pinguin.sprintplanning.model.DeveloperDto;
import com.pinguin.sprintplanning.model.IssueDto;
import com.pinguin.sprintplanning.model.PriorityDto;
import com.pinguin.sprintplanning.model.StatusDto;
import com.pinguin.sprintplanning.model.StoryDto;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class TestData {

  public List<StoryEntity> createStoriesList() {
    List<StoryEntity> stories = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      StoryEntity storyEntity = new StoryEntity();
      storyEntity.setEstimatedPoint(i % 10 + 1);
      storyEntity.setStatus(StatusDto.NEW.toString());
      stories.add(storyEntity);
    }
    return stories;
  }

  public DeveloperDto createDeveloperDto() {
    DeveloperDto developerDto = new DeveloperDto();
    developerDto.setName("Andre");

    return developerDto;
  }

  public DeveloperEntity createDeveloperEntity() {
    DeveloperEntity developerEntity = new DeveloperEntity();

    developerEntity.setDeveloperName("Andre");
    developerEntity.setDeveloperId(UUID.randomUUID());

    return developerEntity;
  }

  public RequestBuilder requestBuilder(HttpMethod httpMethod, String path, Optional<String> body,
     String paramValue) {
    MockHttpServletRequestBuilder method = null;
    if (httpMethod.matches(HttpMethod.POST.name())) {
      method = MockMvcRequestBuilders.post(path);
    } else if (httpMethod.matches(HttpMethod.GET.name())) {
      if(paramValue != null){
        method = MockMvcRequestBuilders.get(path,paramValue);
      }else {
        method = MockMvcRequestBuilders.get(path);
      }
    } else if (httpMethod.matches(HttpMethod.PUT.name())) {
      method = MockMvcRequestBuilders.put(path,paramValue);
    } else if (httpMethod.matches(HttpMethod.DELETE.name())) {
      method = MockMvcRequestBuilders.delete(path,paramValue);
    }

    method.contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(StandardCharsets.UTF_8.name())
        .content(body.orElse(""));

    return method;
  }

  public StoryDto createStoryDto() {
    StoryDto storyDto = new StoryDto();

    storyDto.setEstimatedPoint(10);
    storyDto.setStatus(StatusDto.NEW);

    IssueDto issueDto = new IssueDto();
    issueDto.setTitle("title");
    issueDto.setDescription("desc");
    issueDto.setCreationDate(LocalDateTime.now());

    storyDto.setIssueDetails(issueDto);

    return storyDto;
  }

  public BugDto createBugDto() {
    BugDto bugDto = new BugDto();

    bugDto.setStatus(StatusDto.NEW);
    bugDto.setPriority(PriorityDto.MAJOR);

    IssueDto issueDto = new IssueDto();
    issueDto.setTitle("title");
    issueDto.setDescription("desc");
    issueDto.setCreationDate(LocalDateTime.now());

    bugDto.setIssueDetails(issueDto);

    return bugDto;
  }
}
