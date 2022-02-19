package com.pinguin.sprintplanning.controller;

import com.pinguin.sprintplanning.model.BugDto;
import com.pinguin.sprintplanning.model.IssuesDto;
import com.pinguin.sprintplanning.model.ProblemDto;
import com.pinguin.sprintplanning.model.StoryDto;
import java.util.UUID;
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
 * A delegate to be called by the {@link IssueApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface IssueApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * @see IssueApi#addBug
     */
    default ResponseEntity<Object> addBug(BugDto bugDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * @see IssueApi#addStory
     */
    default ResponseEntity<Object> addStory(StoryDto storyDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * @see IssueApi#deleteIssue
     */
    default ResponseEntity<Void> deleteIssue(UUID issueId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * @see IssueApi#getAllIssues
     */
    default ResponseEntity<IssuesDto> getAllIssues(Integer pageNumber,
        Integer pageSize) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"pagination\" : {    \"total\" : 1,    \"pageNumber\" : 0,    \"pageSize\" : 6  },  \"issues\" : [ {    \"issueId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",    \"developerId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",    \"description\" : \"description\",    \"title\" : \"title\",    \"creationDate\" : \"2000-01-23T04:56:07.000+00:00\"  }, {    \"issueId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",    \"developerId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",    \"description\" : \"description\",    \"title\" : \"title\",    \"creationDate\" : \"2000-01-23T04:56:07.000+00:00\"  } ]}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * @see IssueApi#updateBug
     */
    default ResponseEntity<Void> updateBug(UUID issueId,
        BugDto bugDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * @see IssueApi#updateStory
     */
    default ResponseEntity<Void> updateStory(UUID issueId,
        StoryDto storyDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
