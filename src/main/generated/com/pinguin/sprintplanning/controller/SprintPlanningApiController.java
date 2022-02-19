package com.pinguin.sprintplanning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
@RequestMapping("${openapi.sprintPlanningService.base-path:/api/sprint-planning-service}")
public class SprintPlanningApiController implements SprintPlanningApi {

    private final SprintPlanningApiDelegate delegate;

    public SprintPlanningApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) SprintPlanningApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new SprintPlanningApiDelegate() {});
    }

    @Override
    public SprintPlanningApiDelegate getDelegate() {
        return delegate;
    }

}
