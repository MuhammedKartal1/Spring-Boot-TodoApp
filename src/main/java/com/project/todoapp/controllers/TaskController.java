package com.project.todoapp.controllers;

import com.project.todoapp.entities.Task;
import com.project.todoapp.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Long taskId){
        return taskService.createNewTask(taskId);
    }

    @GetMapping("/completed")
    public List<Task> getOneTask(){
        return taskService.getCompletedTasks();
    }

    @GetMapping("/{taskId}")
    public Task getTask(@RequestBody Long taskId){
        return taskService.getOneTask(taskId);
    }

    @PutMapping("/{taskId}")
    public Task updateOneTask(@RequestBody Long taskId, @RequestBody Task newTask){
        return taskService.updateOneTask(taskId,newTask);
    }

    @PutMapping("/{taskId}/complete")
    public Task completeTask(@PathVariable Long taskId){
        return taskService.completeTask(taskId);
    }

    @DeleteMapping("/{taskId}")
    public Boolean deleteOneTask(@PathVariable Long taskId){
        return taskService.deleteById(taskId);
    }


}
