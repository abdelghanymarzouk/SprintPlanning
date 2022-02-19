package com.pinguin.sprintplanning.controller;

import com.pinguin.sprintplanning.model.DeveloperDto;
import com.pinguin.sprintplanning.model.DevelopersDto;
import com.pinguin.sprintplanning.model.ProblemDto;
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
 * A delegate to be called by the {@link DeveloperApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface DeveloperApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * @see DeveloperApi#addDeveloper
     */
    default ResponseEntity<Object> addDeveloper(DeveloperDto developerDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * @see DeveloperApi#deleteDeveloper
     */
    default ResponseEntity<Void> deleteDeveloper(UUID developerId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * @see DeveloperApi#getAllDevelopers
     */
    default ResponseEntity<DevelopersDto> getAllDevelopers(Integer pageNumber,
        Integer pageSize) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"pagination\" : {    \"total\" : 1,    \"pageNumber\" : 0,    \"pageSize\" : 6  },  \"developers\" : [ {    \"developerId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",    \"name\" : \"name\"  }, {    \"developerId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",    \"name\" : \"name\"  } ]}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * @see DeveloperApi#getSpecificDeveloper
     */
    default ResponseEntity<DeveloperDto> getSpecificDeveloper(UUID developerId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"developerId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\",  \"name\" : \"name\"}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * @see DeveloperApi#updateDeveloper
     */
    default ResponseEntity<Void> updateDeveloper(UUID developerId,
        DeveloperDto developerDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
