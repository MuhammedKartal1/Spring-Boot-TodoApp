package com.project.todoapp.services;

import com.project.todoapp.dto.TaskDto;
import com.project.todoapp.entities.Task;

import java.util.List;
import java.util.Optional;


public interface TaskService {
    List<TaskDto> getAllTasks(Optional<Long> userId);

    TaskDto createNewTask(TaskDto newTaskDto);
 
    List<TaskDto> getCompletedTasks(Optional<Long> userId);

    TaskDto getOneTask(Long taskId);

    TaskDto updateOneTask(Long taskId, TaskDto newTask);

    TaskDto completeTask(Long taskId);

    Boolean deleteById(Long taskId);
}
