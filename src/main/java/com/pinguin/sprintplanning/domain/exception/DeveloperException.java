package com.pinguin.sprintplanning.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DeveloperException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final String errorMessage;

  public DeveloperException(String errorMessage) {
    super(errorMessage);
    this.errorMessage = errorMessage;

  }

}
