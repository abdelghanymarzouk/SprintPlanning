package com.pinguin.sprintplanning.domain.service;

import com.pinguin.sprintplanning.domain.model.StoryEntity;
import com.pinguin.sprintplanning.model.BugDto;
import com.pinguin.sprintplanning.model.IssuesDto;
import com.pinguin.sprintplanning.model.StoryDto;

import java.util.List;
import java.util.UUID;

public interface IssuesService {


  UUID addStory(StoryDto storyDto);

  UUID addBug(BugDto bugDto);

  void deleteIssue(UUID issueId);

  void updateBug(UUID issueId, BugDto bugDto);

  void updateStory(UUID issueId, StoryDto storyDto);

  IssuesDto getAllIssues(Integer pageNumber, Integer pageSize);

  List<StoryEntity> getUnassignedStories();
}
