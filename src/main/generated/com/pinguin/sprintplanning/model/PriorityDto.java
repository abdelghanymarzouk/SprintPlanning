package com.pinguin.sprintplanning.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets Priority
 */
public enum PriorityDto {
  
  CRITICAL("CRITICAL"),
  
  MAJOR("MAJOR"),
  
  MINOR("MINOR");

  private String value;

  PriorityDto(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PriorityDto fromValue(String text) {
    for (PriorityDto b : PriorityDto.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + text + "'");
  }
}

