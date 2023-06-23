package com.project.todoapp.services.impl;


import com.project.todoapp.dto.UserDto;

import com.project.todoapp.entities.User;
import com.project.todoapp.repositories.UserRepository;
import com.project.todoapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    private UserDto converter(User user){
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(converter(user));
        }
        return userDtos;
    }

    @Override
    public UserDto createNewUser(UserDto newUserDto) {

        User user = new User();
        user.setUsername(newUserDto.getUsername());
        user.setPassword(newUserDto.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);

        return converter(user);
    }

    @Override
    public UserDto getOneUser(Long userId) {

        Optional<User> user = userRepository.findById(userId);

        return user.map(this::converter).orElse(null);

    }

    @Override
    public UserDto updateOneUser(Long userId, UserDto newUser) {

        Optional<User> user = userRepository.findById(userId);


        if(user.isPresent()) {
            user.get().setUsername(newUser.getUsername());
            user.get().setPassword(newUser.getPassword());
            user.get().setUpdatedAt(LocalDateTime.now());
            user.get().setUsername(newUser.getUsername());
            user.get().setPassword(newUser.getPassword());
            userRepository.save(user.get());
            return newUser;
        }

        return null;
    }

    @Override
    public Boolean deleteById(Long userId) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }


    @Override
    public User getOneUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getOneUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
