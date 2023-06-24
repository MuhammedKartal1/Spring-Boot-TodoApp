package com.project.todoapp.services.impl;

import com.project.todoapp.dto.TaskDto;
import com.project.todoapp.entities.Task;
import com.project.todoapp.entities.User;
import com.project.todoapp.repositories.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class TaskServiceImplTest {

    private TaskServiceImpl taskService;
    private TaskRepository taskRepository;
    private UserServiceImpl userService;

    private Task task1;
    private List<Task> taskList;

    private User user1 ;

    @Before
    public void setUp() throws Exception {

            taskRepository = Mockito.mock(TaskRepository.class);
            userService = Mockito.mock(UserServiceImpl.class);

            taskService = new TaskServiceImpl(taskRepository,userService);


            user1 = new User();
            user1.setUsername("user1");
            user1.setPassword("password1");

            User user2 = new User();
            user2.setUsername("user2");
            user2.setPassword("password2");


            task1 = new Task();
            task1.setDescription("TestDescription1");
            task1.setTitle("TestTitle1");
            task1.setCompleted(false);
            task1.setUser(user1);

            Task task2 = new Task();
            task2.setDescription("TestDescription2");
            task2.setTitle("TestTitle2");
            task2.setCompleted(false);
            task2.setUser(user1);

            taskList = new ArrayList<>();
            taskList.add(task1);
            taskList.add(task2);
    }

    @Test
    public void whenGetAllTasksCalledWithValidRequest_itShoulReturnTaskDtoList(){

        List<TaskDto> taskDtoList;
        Mockito.when(taskRepository.findAll()).thenReturn(taskList);
        Optional<Long> optional = Optional.empty();


        taskDtoList = taskService.getAllTasks(optional);

        List<TaskDto> taskDtoListExpected = new ArrayList<>();
        TaskDto taskDto1 = new TaskDto();
        taskDto1.setDescription("TestDescription1");
        taskDto1.setTitle("TestTitle1");
        taskDto1.setCompleted(false);
        taskDto1.setUserId(user1.getId());
        taskDtoListExpected.add(taskDto1);

        TaskDto taskDto2 = new TaskDto();
        taskDto2.setDescription("TestDescription2");
        taskDto2.setTitle("TestTitle2");
        taskDto2.setCompleted(false);
        taskDto2.setUserId(user1.getId());
        taskDtoListExpected.add(taskDto2);

        assertEquals(taskDtoList.size(), taskDtoListExpected.size());

        for (int i = 0; i < taskDtoListExpected.size(); i++) {
            TaskDto expectedTaskDto = taskDtoListExpected.get(i);
            TaskDto actualTaskDto = taskDtoList.get(i);
            assertEquals(expectedTaskDto.getTitle(), actualTaskDto.getTitle());
            assertEquals(expectedTaskDto.getDescription(), actualTaskDto.getDescription());
        }

        verify(taskRepository).findAll();


    }
    @Test
    public void whenGetCreateNewTaskCalledWithValidRequest_itShouldReturnTaskDto(){

        TaskDto actualTaskDto = new TaskDto();
        actualTaskDto.setTitle(task1.getTitle());
        actualTaskDto.setDescription(task1.getDescription());
        actualTaskDto.setUserId(task1.getUser().getId());

        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task1);
        Mockito.when(userService.getOneUserById(user1.getId())).thenReturn(Optional.of(user1));


        TaskDto expectedTaskDto = taskService.createNewTask(actualTaskDto);

        assertEquals(actualTaskDto.getUserId(),expectedTaskDto.getUserId());
        assertEquals(actualTaskDto.getTitle(), expectedTaskDto.getTitle());
        assertEquals(actualTaskDto.getDescription(),expectedTaskDto.getDescription());

       Mockito.verify(taskRepository).save(Mockito.any(Task.class));
       Mockito.verify(userService).getOneUserById(task1.getId());

    }







}