package com.pinguin.sprintplanning.domain.service.impl;

import static com.pinguin.sprintplanning.domain.util.Constants.MAX_STORY_POINT_FOR_DEVELOPER_PER_WEEK;
import static com.pinguin.sprintplanning.domain.util.Constants.WEEK_CONST;

import com.pinguin.sprintplanning.domain.mapper.StoryMapper;
import com.pinguin.sprintplanning.domain.model.StoryEntity;
import com.pinguin.sprintplanning.domain.service.DeveloperService;
import com.pinguin.sprintplanning.domain.service.IssuesService;
import com.pinguin.sprintplanning.domain.service.PlanService;
import com.pinguin.sprintplanning.model.StoryDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlanServiceImpl implements PlanService {

  IssuesService issuesService;
  DeveloperService developerService;

  public PlanServiceImpl(IssuesService issuesService,
      DeveloperService developerService) {
    this.issuesService = issuesService;
    this.developerService = developerService;
  }

  private List<StoryEntity> getUnAssignedStories() {
    return issuesService.getUnassignedStories();
  }

  private long numOfDevelopers() {
    return developerService.getNumberOfDevelopers();
  }

  @Override
  public Map<String, List<StoryDto>> plan() {
    Map<String, List<StoryDto>> storiesPlan = new HashMap<>();

    List<StoryEntity> storyEntities = getUnAssignedStories();
    List<StoryDto> storyDtos = storyEntities.stream().map(StoryMapper::entityToDto)
        .collect(Collectors.toList());
    long availablePointsPerWeek = numOfDevelopers() * MAX_STORY_POINT_FOR_DEVELOPER_PER_WEEK;
    int currentWeek = 1, sumStoryPoints = 0;
    Map<Integer, List<StoryDto>> estimatedPointStoryMap = new HashMap<>();
    List<StoryDto> storiesPerWeek = new ArrayList<>();
    List<StoryDto> tempList;
    for (StoryDto storyDto : storyDtos) {

      if (storyDto.getEstimatedPoint() == MAX_STORY_POINT_FOR_DEVELOPER_PER_WEEK) {

        storiesPerWeek.add(storyDto);
        sumStoryPoints += MAX_STORY_POINT_FOR_DEVELOPER_PER_WEEK;

      } else if (estimatedPointStoryMap.get(MAX_STORY_POINT_FOR_DEVELOPER_PER_WEEK - storyDto.getEstimatedPoint()) != null
          && estimatedPointStoryMap.get(MAX_STORY_POINT_FOR_DEVELOPER_PER_WEEK - storyDto.getEstimatedPoint()).size() > 0) {

        storiesPerWeek.add(storyDto);
        storiesPerWeek.add(estimatedPointStoryMap.get(MAX_STORY_POINT_FOR_DEVELOPER_PER_WEEK - storyDto.getEstimatedPoint()).get(0));

        List<StoryDto> storyDtoList = estimatedPointStoryMap.get(MAX_STORY_POINT_FOR_DEVELOPER_PER_WEEK - storyDto.getEstimatedPoint());
        storyDtoList.remove(0);
        estimatedPointStoryMap.put(MAX_STORY_POINT_FOR_DEVELOPER_PER_WEEK - storyDto.getEstimatedPoint(), storyDtoList);

        sumStoryPoints += MAX_STORY_POINT_FOR_DEVELOPER_PER_WEEK;

      } else {
        tempList = new ArrayList<>();

        if (estimatedPointStoryMap.get(storyDto.getEstimatedPoint()) != null) {
          tempList = estimatedPointStoryMap.get(storyDto.getEstimatedPoint());
        }

        tempList.add(storyDto);
        estimatedPointStoryMap.put(storyDto.getEstimatedPoint(), tempList);
      }
      if (sumStoryPoints == availablePointsPerWeek) {
        storiesPlan.put(WEEK_CONST + currentWeek, storiesPerWeek);
        storiesPerWeek = new ArrayList<>();
        sumStoryPoints = 0;
        currentWeek++;
      }
    }

    List<StoryDto> remainingStories = estimatedPointStoryMap.values().stream()
        .flatMap(Collection::stream)
        .sorted(Comparator.comparing(StoryDto::getEstimatedPoint)).collect(Collectors.toList());

    for (StoryDto storyDto : remainingStories) {
      if (sumStoryPoints + storyDto.getEstimatedPoint() <= availablePointsPerWeek) {
        storiesPerWeek.add(storyDto);
        sumStoryPoints += storyDto.getEstimatedPoint();

      } else {
        storiesPlan.put(WEEK_CONST + currentWeek, storiesPerWeek);

        storiesPerWeek = new ArrayList<>();
        sumStoryPoints = 0;

        currentWeek++;
      }
    }
    storiesPlan.put(WEEK_CONST + currentWeek, storiesPerWeek);

    return storiesPlan;
  }


}
