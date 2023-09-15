package com.demonstration.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demonstration.entities.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {

}