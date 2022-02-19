package com.pinguin.sprintplanning.domain.service;

import static com.pinguin.sprintplanning.domain.util.Constants.WEEK_CONST;
import static org.mockito.Mockito.when;

import com.pinguin.sprintplanning.domain.model.StoryEntity;
import com.pinguin.sprintplanning.domain.service.impl.PlanServiceImpl;
import com.pinguin.sprintplanning.domain.util.TestData;
import com.pinguin.sprintplanning.model.StoryDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * PlanService Tester.
 *
 * @author Abdelghany Marzouk
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class PlanServiceTest {

  @Mock
  private IssuesService issuesService;
  @Mock
  private DeveloperService developerService;
  private PlanService planService;

  private final TestData testData = new TestData();

  @BeforeEach
  void setup() {
    planService = new PlanServiceImpl(issuesService, developerService);
  }


  @Test
  void testPlan() {
    List<StoryEntity> storyEntities = testData.createStoriesList();

    when(developerService.getNumberOfDevelopers()).thenReturn(5L);
    when(issuesService.getUnassignedStories()).thenReturn(storyEntities);

    Map<String, List<StoryDto>> plan = planService.plan();

    Assertions.assertEquals(3, plan.keySet().size());
    Assertions.assertEquals(9,plan.get(WEEK_CONST+1).size());
    Assertions.assertEquals(10,plan.get(WEEK_CONST+2).size());
    Assertions.assertEquals(1,plan.get(WEEK_CONST+3).size());
  }



} 
