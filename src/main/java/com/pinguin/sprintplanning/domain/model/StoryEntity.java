package com.pinguin.sprintplanning.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "stories")
public class StoryEntity extends IssueEntity {

  @Column(name = "estimated_Point")
  private Integer estimatedPoint;

  @Column(name = "status")
  private String status;

}
