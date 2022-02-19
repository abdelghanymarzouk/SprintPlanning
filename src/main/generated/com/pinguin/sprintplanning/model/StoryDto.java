package com.pinguin.sprintplanning.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.pinguin.sprintplanning.model.IssueDto;
import com.pinguin.sprintplanning.model.StatusDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * StoryDto
 */

public class StoryDto  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("issueDetails")
  private IssueDto issueDetails = null;

  @JsonProperty("estimatedPoint")
  private Integer estimatedPoint;

  @JsonProperty("status")
  private StatusDto status = null;

  public StoryDto issueDetails(IssueDto issueDetails) {
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

  public StoryDto estimatedPoint(Integer estimatedPoint) {
    this.estimatedPoint = estimatedPoint;
    return this;
  }

  /**
   * Estimated point value for the story.
   * @return estimatedPoint
  */
  @ApiModelProperty(value = "Estimated point value for the story.")


  public Integer getEstimatedPoint() {
    return estimatedPoint;
  }

  public void setEstimatedPoint(Integer estimatedPoint) {
    this.estimatedPoint = estimatedPoint;
  }

  public StoryDto status(StatusDto status) {
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
    StoryDto story = (StoryDto) o;
    return Objects.equals(this.issueDetails, story.issueDetails) &&
        Objects.equals(this.estimatedPoint, story.estimatedPoint) &&
        Objects.equals(this.status, story.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(issueDetails, estimatedPoint, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StoryDto {\n");
    
    sb.append("    issueDetails: ").append(toIndentedString(issueDetails)).append("\n");
    sb.append("    estimatedPoint: ").append(toIndentedString(estimatedPoint)).append("\n");
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

