package com.pinguin.sprintplanning.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.pinguin.sprintplanning.model.IssueDto;
import com.pinguin.sprintplanning.model.PaginationDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * IssuesDto
 */

public class IssuesDto  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("pagination")
  private PaginationDto pagination = null;

  @JsonProperty("issues")
  @Valid
  private List<IssueDto> issues = null;

  public IssuesDto pagination(PaginationDto pagination) {
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

  public IssuesDto issues(List<IssueDto> issues) {
    this.issues = issues;
    return this;
  }

  public IssuesDto addIssuesItem(IssueDto issuesItem) {
    if (this.issues == null) {
      this.issues = new ArrayList<>();
    }
    this.issues.add(issuesItem);
    return this;
  }

  /**
   * Get issues
   * @return issues
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<IssueDto> getIssues() {
    return issues;
  }

  public void setIssues(List<IssueDto> issues) {
    this.issues = issues;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IssuesDto issues = (IssuesDto) o;
    return Objects.equals(this.pagination, issues.pagination) &&
        Objects.equals(this.issues, issues.issues);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pagination, issues);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IssuesDto {\n");
    
    sb.append("    pagination: ").append(toIndentedString(pagination)).append("\n");
    sb.append("    issues: ").append(toIndentedString(issues)).append("\n");
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

