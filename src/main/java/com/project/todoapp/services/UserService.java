package com.project.todoapp.services;

import com.project.todoapp.dto.UserDto;
import com.project.todoapp.entities.User;

import java.util.List;


public interface UserService {

    List<UserDto> getAllUsers();

    UserDto createNewUser(UserDto newUser);

    UserDto getOneUser(Long userId);

    UserDto updateOneUser(Long userId, UserDto newUser);

    Boolean deleteById(Long userId);


    User getOneUserByName(String username);
}
