package com.pinguin.sprintplanning.controller;

import com.pinguin.sprintplanning.model.StoryDto;
import java.util.List;
import com.pinguin.sprintplanning.model.ProblemDto;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link SprintPlanningApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface SprintPlanningApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * @see SprintPlanningApi#plan
     */
    default ResponseEntity<Map<String, List<StoryDto>>> plan() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
