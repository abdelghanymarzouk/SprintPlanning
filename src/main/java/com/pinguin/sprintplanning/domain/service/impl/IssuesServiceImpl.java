package com.pinguin.sprintplanning.domain.service.impl;

import static com.pinguin.sprintplanning.domain.util.Constants.MAX_PAGE_SIZE;

import com.pinguin.sprintplanning.domain.exception.DeveloperException;
import com.pinguin.sprintplanning.domain.exception.IssuesException;
import com.pinguin.sprintplanning.domain.mapper.BugMapper;
import com.pinguin.sprintplanning.domain.mapper.IssueMapper;
import com.pinguin.sprintplanning.domain.mapper.StoryMapper;
import com.pinguin.sprintplanning.domain.model.BugEntity;
import com.pinguin.sprintplanning.domain.model.IssueEntity;
import com.pinguin.sprintplanning.domain.model.StoryEntity;
import com.pinguin.sprintplanning.domain.repository.IssueRepository;
import com.pinguin.sprintplanning.domain.service.IssuesService;
import com.pinguin.sprintplanning.model.BugDto;
import com.pinguin.sprintplanning.model.IssueDto;
import com.pinguin.sprintplanning.model.IssuesDto;
import com.pinguin.sprintplanning.model.PaginationDto;
import com.pinguin.sprintplanning.model.StoryDto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IssuesServiceImpl implements IssuesService {

  IssueRepository issueRepository;

  @Autowired
  IssuesServiceImpl(IssueRepository issueRepository) {
    this.issueRepository = issueRepository;
  }

  @Override
  public UUID addStory(StoryDto storyDto) {
    StoryEntity storyEntity = StoryMapper.dtoToEntity(storyDto);

    if (storyEntity.getEstimatedPoint() > 10 || storyEntity.getEstimatedPoint() < 1) {
      throw new IssuesException("Story point should be with 1 to 10 points");
    }

    storyEntity = (StoryEntity) issueRepository.save(storyEntity);

    return storyEntity.getIssueId();
  }

  @Override
  public UUID addBug(BugDto bugDto) {
    BugEntity bugEntity = BugMapper.dtoToEntity(bugDto);

    bugEntity = (BugEntity) issueRepository.save(bugEntity);

    return bugEntity.getIssueId();
  }

  @Override
  public void deleteIssue(UUID issueId) {
    issueRepository.deleteById(issueId);
  }

  @Override
  public void updateBug(UUID issueId, BugDto bugDto) {
    Optional<BugEntity> bugEntityOptional = issueRepository.findById(issueId);

    if (bugEntityOptional.isEmpty()) {
      throw new IssuesException("Bug not found");
    }

    BugEntity bugEntity = BugMapper.dtoToEntity(bugDto);
    bugEntity.setIssueId(issueId);

    issueRepository.save(bugEntity);
  }

  @Override
  public void updateStory(UUID issueId, StoryDto storyDto) {
    Optional<StoryEntity> storyEntityOptional = issueRepository.findById(issueId);

    if (storyEntityOptional.isEmpty()) {
      throw new IssuesException("Story not found");
    }

    StoryEntity storyEntity = StoryMapper.dtoToEntity(storyDto);
    storyEntity.setIssueId(issueId);

    if (storyEntity.getEstimatedPoint() > 10 || storyEntity.getEstimatedPoint() < 1) {
      throw new IssuesException("Story point should be with 1 to 10 points");
    }

    issueRepository.save(storyEntity);
  }

  @Override
  public IssuesDto getAllIssues(Integer pageNumber, Integer pageSize) {
    validatePageNumberAndPageSize(pageNumber, pageSize);

    pageNumber = pageNumber - 1;
    Page issueEntityPage = issueRepository
        .findAll(PageRequest.of(pageNumber, pageSize));

    return mapAndPrepareResponse(pageNumber, issueEntityPage);

  }

  @Override
  public List<StoryEntity> getUnassignedStories() {
    List<StoryEntity> stories = issueRepository.findUnassignedStories();
    if (stories.size() == 0) {
      throw new IssuesException("There is no new/estimated stories");
    }
    return stories;
  }

  private void validatePageNumberAndPageSize(Integer pageNumber, Integer pageSize) {
    if (pageNumber != null && pageNumber < 1) {
      throw new DeveloperException("Page number must be greater than or equal to 1");
    }

    if (pageSize != null && pageSize > MAX_PAGE_SIZE) {
      throw new DeveloperException("Page size must be less than or equal to " + MAX_PAGE_SIZE);
    }
  }

  private IssuesDto mapAndPrepareResponse(Integer pageNumber, Page<IssueEntity> issueEntityPage) {
    List<IssueDto> issueDtoList = issueEntityPage.stream().map(IssueMapper::entityToDto)
        .collect(Collectors.toList());

    PaginationDto paginationDto = new PaginationDto();
    paginationDto.setPageNumber(pageNumber + 1);
    paginationDto.setPageSize(issueEntityPage.getSize());
    paginationDto.setTotal(issueEntityPage.getTotalPages());

    IssuesDto issuesDto = new IssuesDto();
    issuesDto.setPagination(paginationDto);
    issuesDto.setIssues(issueDtoList);

    return issuesDto;
  }
}
