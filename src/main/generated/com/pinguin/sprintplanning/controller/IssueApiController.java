package com.pinguin.sprintplanning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
@RequestMapping("${openapi.sprintPlanningService.base-path:/api/sprint-planning-service}")
public class IssueApiController implements IssueApi {

    private final IssueApiDelegate delegate;

    public IssueApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) IssueApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new IssueApiDelegate() {});
    }

    @Override
    public IssueApiDelegate getDelegate() {
        return delegate;
    }

}
