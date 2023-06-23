package com.project.todoapp.services.impl;

import com.project.todoapp.dto.TaskDto;
import com.project.todoapp.entities.Task;
import com.project.todoapp.entities.User;
import com.project.todoapp.repositories.TaskRepository;
import com.project.todoapp.services.TaskService;
import com.project.todoapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {


    final TaskRepository taskRepository;

    final UserService userService;

    private TaskDto converter(Task task){
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setCompleted(task.getCompleted());
        taskDto.setUserId(task.getUser().getId());
        return taskDto;
    }

    @Override
    public List<TaskDto> getAllTasks(Optional<Long> userId) {

        List<Task> tasks;
        List<TaskDto>  taskDtos = new ArrayList<>();

        if (userId.isPresent()) {
            tasks = taskRepository.findByUserId(userId.get());
        }else{
            tasks = taskRepository.findAll();
        }
        for (Task task : tasks) {
            taskDtos.add(converter(task));
        }
        return taskDtos;
    }

    @Override
    public TaskDto createNewTask(TaskDto newTaskDto) {

        Task task = new Task();
        task.setTitle(newTaskDto.getTitle());
        task.setDescription(newTaskDto.getDescription());
        task.setCompleted(newTaskDto.getCompleted());

        Optional<User> user = userService.getOneUserById(newTaskDto.getUserId());
        user.ifPresent(task::setUser);
        taskRepository.save(task);
        return converter(task);

    }

    @Override
    public List<TaskDto> getCompletedTasks(Optional<Long> userId) {
        List<Task> userALlTasks = null;
        List<Task> userCompletedTasks = new ArrayList<>();
        List<TaskDto>  taskDtos = new ArrayList<>();

        if (userId.isPresent()) {
            userALlTasks = taskRepository.findByUserId(userId.get());
            for (Task task : userALlTasks) {
                if (task.getCompleted()) {
                    userCompletedTasks.add(task);
                }
            }
        }
         if(!userCompletedTasks.isEmpty()){

             for (Task task : userALlTasks) {
                 if (task.getCompleted()) {
                     taskDtos.add(converter(task));
                 }
             }
         }
        return taskDtos;
    }

    @Override
    public TaskDto getOneTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        return task.map(this::converter).orElse(null);

    }

    @Override
    public TaskDto updateOneTask(Long taskId, TaskDto newTask) {
        Optional<Task> task = taskRepository.findById(taskId);

        if(task.isPresent()){
            task.get().setCompleted(newTask.getCompleted());
            task.get().setTitle(newTask.getTitle());
            task.get().setDescription(newTask.getDescription());
            task.get().setUpdatedAt(LocalDateTime.now());

            return converter(task.get());
        }
        return null;
    }

    @Override
    public TaskDto completeTask(Long taskId) {

        Optional<Task> task = taskRepository.findById(taskId);

        if (task.isPresent()){
            task.get().setCompleted(true);
            taskRepository.save(task.get());
            return converter(task.get());
        }
        return null;
    }

    @Override
    public Boolean deleteById(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()){
            taskRepository.deleteById(taskId);
            return true;
        }
        return false;
    }
}
