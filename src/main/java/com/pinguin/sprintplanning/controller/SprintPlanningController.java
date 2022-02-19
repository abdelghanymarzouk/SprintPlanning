package com.pinguin.sprintplanning.controller;

import com.pinguin.sprintplanning.domain.model.StoryEntity;
import com.pinguin.sprintplanning.domain.service.PlanService;
import com.pinguin.sprintplanning.model.StoryDto;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SprintPlanningController implements SprintPlanningApiDelegate {

  PlanService planService;

  @Autowired
  public SprintPlanningController (PlanService planService){
    this.planService = planService;
  }

  @Override
  public ResponseEntity<Map<String, List<StoryDto>>> plan() {
    Map<String, List<StoryDto>> plan = planService.plan();
    return ResponseEntity.ok(plan);
  }
}
