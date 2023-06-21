package com.project.todoapp.services.impl;

import com.project.todoapp.repositories.UserRepository;

public class UserServiceImpl {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
