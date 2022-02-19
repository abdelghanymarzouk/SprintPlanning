package com.pinguin.sprintplanning.domain.repository;


import com.pinguin.sprintplanning.domain.model.IssueEntity;
import com.pinguin.sprintplanning.domain.model.StoryEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface IssueRepository<T extends IssueEntity> extends JpaRepository<T, UUID>,
    JpaSpecificationExecutor<T> {

  @Query(value ="select s from stories  s where s.status <> 'COMPLETED' ")
  List<StoryEntity> findUnassignedStories();

}
