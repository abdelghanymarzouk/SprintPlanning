package com.pinguin.sprintplanning.domain.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter

@Entity(name = "developers")
public class DeveloperEntity {

  @Id
  @Column(name = "developer_id", nullable = false, updatable = false)
  @GeneratedValue
  private UUID developerId;

  @Column(name = "developer_name", nullable = false)
  private String developerName;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "developer")
  private List<IssueEntity> issues;
}
