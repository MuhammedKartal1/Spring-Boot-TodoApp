package com.project.todoapp.services.impl;

import com.project.todoapp.dto.TaskDto;
import com.project.todoapp.entities.Task;
import com.project.todoapp.repositories.TaskRepository;
import com.project.todoapp.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {


    final TaskRepository taskRepository;

    final ModelMapper modelMapper;


    @Override
    public List<TaskDto> getAllTasks(Optional<Long> userId) {

        List<Task> tasks;
        List<TaskDto>  taskDtos;

        if (userId.isPresent()) {
            tasks = taskRepository.findByUserId(userId);
        }else{
            tasks = taskRepository.findAll();
        }
        taskDtos = tasks.stream().map(task -> modelMapper.map(task,TaskDto.class)).collect(Collectors.toList());
        return taskDtos;
    }

    @Override
    public TaskDto createNewTask(TaskDto newTaskDto) {

        Task task = modelMapper.map(newTaskDto, Task.class);
        return modelMapper.map(taskRepository.save(task), TaskDto.class);

    }

    @Override
    public List<TaskDto> getCompletedTasks(Optional<Long> userId) {
        List<Task> userALlTasks ;
        List<Task> userCompletedTasks = new ArrayList<>();
        List<TaskDto>  taskDtos = null;

        if (userId.isPresent()) {
            userALlTasks = taskRepository.findByUserId(userId);
            for (Task task : userALlTasks) {
                if (task.getCompleted()) {
                    userCompletedTasks.add(task);
                }
            }
        }
         if(!userCompletedTasks.isEmpty()){
             taskDtos = userCompletedTasks.stream().map(task -> modelMapper.map(task,TaskDto.class)).collect(Collectors.toList());
         }

        return taskDtos;
    }

    @Override
    public TaskDto getOneTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);

        if (task.isPresent())
            return modelMapper.map(task, TaskDto.class);

        return null;
    }

    @Override
    public TaskDto updateOneTask(Long taskId, TaskDto newTask) {
        Optional<Task> task = taskRepository.findById(taskId);

        if(task.isPresent()){
            task.get().setCompleted(newTask.getCompleted());
            task.get().setTitle(newTask.getTitle());
            task.get().setDescription(newTask.getDescription());
            task.get().setUpdatedAt(LocalDateTime.now());
            return modelMapper.map(taskRepository.save(task.get()), TaskDto.class);
        }

        return null;

    }

    @Override
    public TaskDto completeTask(Long taskId) {

        Optional<Task> task = taskRepository.findById(taskId);

        if (task.isPresent()){
            task.get().setCompleted(true);
            return modelMapper.map(taskRepository.save(task.get()), TaskDto.class);
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
