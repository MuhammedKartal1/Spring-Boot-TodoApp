package com.project.todoapp.repositories;

import com.project.todoapp.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {


}
