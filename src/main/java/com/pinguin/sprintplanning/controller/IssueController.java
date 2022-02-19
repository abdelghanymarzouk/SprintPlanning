package com.pinguin.sprintplanning.controller;

import com.pinguin.sprintplanning.domain.service.IssuesService;
import com.pinguin.sprintplanning.model.BugDto;
import com.pinguin.sprintplanning.model.IssuesDto;
import com.pinguin.sprintplanning.model.StoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
public class IssueController implements IssueApiDelegate {


  IssuesService issuesService;

  @Autowired
  public IssueController(IssuesService issuesService) {
    this.issuesService = issuesService;
  }

  @Override
  public ResponseEntity<Object> addStory(StoryDto storyDto) {
    UUID issueId = issuesService.addStory(storyDto);
    return new ResponseEntity<>(issueId, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Object> addBug(BugDto bugDto) {
    UUID issueId = issuesService.addBug(bugDto);
    return new ResponseEntity<>(issueId, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> deleteIssue(UUID issueId) {
    issuesService.deleteIssue(issueId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<IssuesDto> getAllIssues(Integer pageNumber, Integer pageSize) {
    IssuesDto issuesDto = issuesService.getAllIssues(pageNumber, pageSize);
    return ResponseEntity.ok(issuesDto);
  }

  @Override
  public ResponseEntity<Void> updateBug(UUID issueId, BugDto bugDto) {
    issuesService.updateBug(issueId, bugDto);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Override
  public ResponseEntity<Void> updateStory(UUID issueId, StoryDto storyDto) {
    issuesService.updateStory(issueId, storyDto);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
