package com.pinguin.sprintplanning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
@RequestMapping("${openapi.sprintPlanningService.base-path:/api/sprint-planning-service}")
public class DeveloperApiController implements DeveloperApi {

    private final DeveloperApiDelegate delegate;

    public DeveloperApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) DeveloperApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new DeveloperApiDelegate() {});
    }

    @Override
    public DeveloperApiDelegate getDelegate() {
        return delegate;
    }

}
