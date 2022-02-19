package com.pinguin.sprintplanning.domain.mapper;


import com.pinguin.sprintplanning.domain.model.IssueEntity;
import com.pinguin.sprintplanning.domain.model.StoryEntity;
import com.pinguin.sprintplanning.model.IssueDto;
import com.pinguin.sprintplanning.model.StatusDto;
import com.pinguin.sprintplanning.model.StoryDto;

public class StoryMapper {

  private StoryMapper() {
  }

  public static StoryDto entityToDto(StoryEntity storyEntity) {
    StoryDto storyDto = new StoryDto();

    storyDto.setStatus(StatusDto.fromValue(storyEntity.getStatus()));
    storyDto.setEstimatedPoint(storyEntity.getEstimatedPoint());

    IssueDto issueDto = IssueMapper.entityToDto(storyEntity);
    storyDto.setIssueDetails(issueDto);

    return storyDto;
  }

  public static StoryEntity dtoToEntity(StoryDto storyDto) {

    IssueEntity issueEntity = new StoryEntity();

    StoryEntity storyEntity = (StoryEntity) IssueMapper
        .dtoToEntity(storyDto.getIssueDetails(), issueEntity);

    storyEntity.setStatus(storyDto.getStatus().toString());
    storyEntity.setEstimatedPoint(storyDto.getEstimatedPoint());

    return storyEntity;
  }

}

