package com.project.todoapp.services;

import com.project.todoapp.entities.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    Task createNewTask(Long taskId);
 
    List<Task> getCompletedTasks();

    Task getOneTask(Long taskId);

    Task updateOneTask(Long taskId, Task newTask);

    Task completeTask(Long taskId);

    Boolean deleteById(Long taskId);
}
