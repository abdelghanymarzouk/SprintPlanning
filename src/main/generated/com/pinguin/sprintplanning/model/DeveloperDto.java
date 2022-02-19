package com.pinguin.sprintplanning.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DeveloperDto
 */

public class DeveloperDto  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("name")
  private String name;

  @JsonProperty("developerId")
  private UUID developerId;

  public DeveloperDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * developer name.
   * @return name
  */
  @ApiModelProperty(value = "developer name.")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DeveloperDto developerId(UUID developerId) {
    this.developerId = developerId;
    return this;
  }

  /**
   * Get developerId
   * @return developerId
  */
  @ApiModelProperty(readOnly = true, value = "")

  @Valid

  public UUID getDeveloperId() {
    return developerId;
  }

  public void setDeveloperId(UUID developerId) {
    this.developerId = developerId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeveloperDto developer = (DeveloperDto) o;
    return Objects.equals(this.name, developer.name) &&
        Objects.equals(this.developerId, developer.developerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, developerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeveloperDto {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    developerId: ").append(toIndentedString(developerId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

