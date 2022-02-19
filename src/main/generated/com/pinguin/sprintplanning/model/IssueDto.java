package com.pinguin.sprintplanning.model;

import java.time.LocalDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * IssueDto
 */

public class IssueDto  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("title")
  private String title;

  @JsonProperty("issueId")
  private UUID issueId;

  @JsonProperty("description")
  private String description;

  @JsonProperty("creationDate")
  private LocalDateTime creationDate;

  @JsonProperty("developerId")
  private UUID developerId;

  public IssueDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Title of the issue.
   * @return title
  */
  @ApiModelProperty(value = "Title of the issue.")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public IssueDto issueId(UUID issueId) {
    this.issueId = issueId;
    return this;
  }

  /**
   * Get issueId
   * @return issueId
  */
  @ApiModelProperty(readOnly = true, value = "")

  @Valid

  public UUID getIssueId() {
    return issueId;
  }

  public void setIssueId(UUID issueId) {
    this.issueId = issueId;
  }

  public IssueDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Issue description.
   * @return description
  */
  @ApiModelProperty(value = "Issue description.")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public IssueDto creationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Get creationDate
   * @return creationDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public IssueDto developerId(UUID developerId) {
    this.developerId = developerId;
    return this;
  }

  /**
   * Get developerId
   * @return developerId
  */
  @ApiModelProperty(value = "")

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
    IssueDto issue = (IssueDto) o;
    return Objects.equals(this.title, issue.title) &&
        Objects.equals(this.issueId, issue.issueId) &&
        Objects.equals(this.description, issue.description) &&
        Objects.equals(this.creationDate, issue.creationDate) &&
        Objects.equals(this.developerId, issue.developerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, issueId, description, creationDate, developerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IssueDto {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    issueId: ").append(toIndentedString(issueId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
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

