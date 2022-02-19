package com.pinguin.sprintplanning.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets Status
 */
public enum StatusDto {
  
  NEW("NEW"),
  
  ESTIMATED("ESTIMATED"),
  
  COMPLETED("COMPLETED"),
  
  VERIFIED("VERIFIED"),
  
  RESOLVED("RESOLVED");

  private String value;

  StatusDto(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static StatusDto fromValue(String text) {
    for (StatusDto b : StatusDto.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + text + "'");
  }
}

