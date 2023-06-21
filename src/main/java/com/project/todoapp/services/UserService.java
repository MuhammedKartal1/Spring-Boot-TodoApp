package com.project.todoapp.services;

import com.project.todoapp.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User createNewUser(Long userId);

    User getOneUser(Long userId);

    User updateOneUser(Long userId, User newUser);

    Boolean deleteById(Long userId);


}
