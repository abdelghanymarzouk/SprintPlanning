package com.pinguin.sprintplanning.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class IssueEntity {

  @Id
  @Column(name = "issue_id", nullable = false, updatable = false)
  @GeneratedValue
  private UUID issueId;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "creation_Date", nullable = false)
  private LocalDateTime creationDate;

  @ManyToOne(optional = true)
  @JoinColumn(name = "developer_id", referencedColumnName = "developer_id")
  private DeveloperEntity developer;

}
