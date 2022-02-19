package com.pinguin.sprintplanning.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.pinguin.sprintplanning.model.IssueDto;
import com.pinguin.sprintplanning.model.PriorityDto;
import com.pinguin.sprintplanning.model.StatusDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BugDto
 */

public class BugDto  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("issueDetails")
  private IssueDto issueDetails = null;

  @JsonProperty("priority")
  private PriorityDto priority = null;

  @JsonProperty("status")
  private StatusDto status = null;

  public BugDto issueDetails(IssueDto issueDetails) {
    this.issueDetails = issueDetails;
    return this;
  }

  /**
   * Get issueDetails
   * @return issueDetails
  */
  @ApiModelProperty(value = "")

  @Valid

  public IssueDto getIssueDetails() {
    return issueDetails;
  }

  public void setIssueDetails(IssueDto issueDetails) {
    this.issueDetails = issueDetails;
  }

  public BugDto priority(PriorityDto priority) {
    this.priority = priority;
    return this;
  }

  /**
   * Get priority
   * @return priority
  */
  @ApiModelProperty(value = "")

  @Valid

  public PriorityDto getPriority() {
    return priority;
  }

  public void setPriority(PriorityDto priority) {
    this.priority = priority;
  }

  public BugDto status(StatusDto status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(value = "")

  @Valid

  public StatusDto getStatus() {
    return status;
  }

  public void setStatus(StatusDto status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BugDto bug = (BugDto) o;
    return Objects.equals(this.issueDetails, bug.issueDetails) &&
        Objects.equals(this.priority, bug.priority) &&
        Objects.equals(this.status, bug.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(issueDetails, priority, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BugDto {\n");
    
    sb.append("    issueDetails: ").append(toIndentedString(issueDetails)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

