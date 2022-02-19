package com.pinguin.sprintplanning.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.pinguin.sprintplanning.model.DeveloperDto;
import com.pinguin.sprintplanning.model.PaginationDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DevelopersDto
 */

public class DevelopersDto  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("pagination")
  private PaginationDto pagination = null;

  @JsonProperty("developers")
  @Valid
  private List<DeveloperDto> developers = null;

  public DevelopersDto pagination(PaginationDto pagination) {
    this.pagination = pagination;
    return this;
  }

  /**
   * Get pagination
   * @return pagination
  */
  @ApiModelProperty(value = "")

  @Valid

  public PaginationDto getPagination() {
    return pagination;
  }

  public void setPagination(PaginationDto pagination) {
    this.pagination = pagination;
  }

  public DevelopersDto developers(List<DeveloperDto> developers) {
    this.developers = developers;
    return this;
  }

  public DevelopersDto addDevelopersItem(DeveloperDto developersItem) {
    if (this.developers == null) {
      this.developers = new ArrayList<>();
    }
    this.developers.add(developersItem);
    return this;
  }

  /**
   * Get developers
   * @return developers
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<DeveloperDto> getDevelopers() {
    return developers;
  }

  public void setDevelopers(List<DeveloperDto> developers) {
    this.developers = developers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DevelopersDto developers = (DevelopersDto) o;
    return Objects.equals(this.pagination, developers.pagination) &&
        Objects.equals(this.developers, developers.developers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pagination, developers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DevelopersDto {\n");
    
    sb.append("    pagination: ").append(toIndentedString(pagination)).append("\n");
    sb.append("    developers: ").append(toIndentedString(developers)).append("\n");
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

