package com.pinguin.sprintplanning.domain.mapper;


import com.pinguin.sprintplanning.domain.model.BugEntity;
import com.pinguin.sprintplanning.domain.model.IssueEntity;
import com.pinguin.sprintplanning.model.BugDto;
import com.pinguin.sprintplanning.model.IssueDto;
import com.pinguin.sprintplanning.model.PriorityDto;
import com.pinguin.sprintplanning.model.StatusDto;

public class BugMapper {

  private BugMapper() {
  }

  public static BugDto entityToDto(BugEntity bugEntity) {
    BugDto bugDto = new BugDto();

    bugDto.setStatus(StatusDto.fromValue(bugEntity.getStatus()));
    bugDto.setPriority(PriorityDto.fromValue(bugEntity.getPriority()));

    IssueDto issueDto = IssueMapper.entityToDto(bugEntity);
    bugDto.setIssueDetails(issueDto);

    return bugDto;
  }

  public static BugEntity dtoToEntity(BugDto bugDto) {
    IssueEntity issueEntity = new BugEntity();

    BugEntity bugEntity = (BugEntity) IssueMapper
        .dtoToEntity(bugDto.getIssueDetails(), issueEntity);

    bugEntity.setStatus(bugDto.getStatus().toString());
    bugEntity.setPriority(bugDto.getPriority().toString());

    return bugEntity;
  }

}

