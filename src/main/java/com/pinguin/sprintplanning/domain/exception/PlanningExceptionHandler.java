package com.pinguin.sprintplanning.domain.exception;

import com.pinguin.sprintplanning.model.ProblemDto;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@ControllerAdvice
public class PlanningExceptionHandler extends ResponseEntityExceptionHandler {

  public static final String TITLE = "Planning service Error";

  @ExceptionHandler(DeveloperException.class)
  public ResponseEntity<Object> handleException(DeveloperException exception) {

    ProblemDto problem = getProblemDto(exception, HttpStatus.BAD_REQUEST);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
  }

  @ExceptionHandler(IssuesException.class)
  public ResponseEntity<Object> handleException(IssuesException exception) {

    ProblemDto problem = getProblemDto(exception, HttpStatus.BAD_REQUEST);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<Object> handleException(EmptyResultDataAccessException exception) {

    ProblemDto problem = getProblemDto(exception, HttpStatus.BAD_REQUEST);
    problem.setDetail("Developer Not Found");

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<Object> handleConstraintViolationException(
      ConstraintViolationException ex) {

    ProblemDto problem = getProblemDto(ex, HttpStatus.BAD_REQUEST);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(problem);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<Object> handleIllegalArgumentExceptionException(
      IllegalArgumentException ex) {

    ProblemDto problem = getProblemDto(ex, HttpStatus.BAD_REQUEST);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(problem);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handleException(
      Exception ex) {

    ProblemDto problem = getProblemDto(ex, HttpStatus.BAD_REQUEST);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(problem);
  }

  private ProblemDto getProblemDto(Exception ex, HttpStatus badRequest) {

    ProblemDto problem = new ProblemDto();

    problem.setStatus(badRequest.value());
    problem.setInstance(badRequest.getReasonPhrase());
    problem.setType(badRequest.getReasonPhrase());
    problem.setDetail(ex.getMessage());
    problem.setTitle(TITLE);

    return problem;
  }

}
