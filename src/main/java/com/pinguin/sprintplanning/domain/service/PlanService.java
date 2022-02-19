package com.pinguin.sprintplanning.domain.service;

import com.pinguin.sprintplanning.domain.model.StoryEntity;

import com.pinguin.sprintplanning.model.StoryDto;
import java.util.List;
import java.util.Map;

public interface PlanService {

  Map<String, List<StoryDto>> plan();
}
