package com.pinguin.sprintplanning.domain.mapper;


import com.pinguin.sprintplanning.domain.model.DeveloperEntity;
import com.pinguin.sprintplanning.domain.model.IssueEntity;
import com.pinguin.sprintplanning.domain.model.StoryEntity;
import com.pinguin.sprintplanning.model.IssueDto;

public class IssueMapper {

  private IssueMapper() {
  }

  public static IssueDto entityToDto(IssueEntity issueEntity) {
    IssueDto issueDto = new IssueDto();

    issueDto.setIssueId(issueEntity.getIssueId());
    issueDto.setTitle(issueEntity.getTitle());
    issueDto.setCreationDate(issueEntity.getCreationDate());
    issueDto.setDescription(issueEntity.getDescription());

    if (issueEntity.getDeveloper() != null) {
      issueDto.setDeveloperId(issueEntity.getDeveloper().getDeveloperId());
    }

    return issueDto;
  }

  public static IssueEntity dtoToEntity(IssueDto issueDto, IssueEntity issueEntity) {
    issueEntity.setIssueId(issueDto.getIssueId());
    issueEntity.setTitle(issueDto.getTitle());
    issueEntity.setCreationDate(issueDto.getCreationDate());
    issueEntity.setDescription(issueDto.getDescription());

    if (issueDto.getDeveloperId() != null) {
      DeveloperEntity developerEntity = new DeveloperEntity();
      developerEntity.setDeveloperId(issueDto.getDeveloperId());

      issueEntity.setDeveloper(developerEntity);
    }

    return issueEntity;
  }

  public static StoryEntity getStory(IssueDto issueDto) {
    StoryEntity storyEntity = new StoryEntity();

    storyEntity.setIssueId(issueDto.getIssueId());
    storyEntity.setTitle(issueDto.getTitle());
    storyEntity.setCreationDate(issueDto.getCreationDate());
    storyEntity.setDescription(issueDto.getDescription());

    DeveloperEntity developerEntity = new DeveloperEntity();
    developerEntity.setDeveloperId(issueDto.getDeveloperId());

    storyEntity.setDeveloper(developerEntity);

    return storyEntity;
  }


}

