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
@Entity(name = "bugs")
public class BugEntity extends IssueEntity {

  @Column(name = "priority")
  private String priority;

  @Column(name = "status")
  private String status;
}
