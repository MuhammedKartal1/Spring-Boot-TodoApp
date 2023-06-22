package com.project.todoapp.services.impl;

import com.project.todoapp.dto.UserDto;
import com.project.todoapp.entities.User;
import com.project.todoapp.repositories.UserRepository;
import com.project.todoapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    final ModelMapper modelMapper;


    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());

    }

    @Override
    public UserDto createNewUser(UserDto newUserDto) {

        User user = modelMapper.map(newUserDto, User.class);
        user.setCreatedAt(LocalDateTime.now());
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto getOneUser(Long userId) {

        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent())
         return modelMapper.map(user, UserDto.class);

        return null;
    }

    @Override
    public UserDto updateOneUser(Long userId, UserDto newUser) {

        Optional<User> user = userRepository.findById(userId);


        if(user.isPresent()) {
            user.get().setUsername(newUser.getUsername());
            user.get().setPassword(newUser.getPassword());
            user.get().setUpdatedAt(LocalDateTime.now());
            return modelMapper.map(userRepository.save(user.get()), UserDto.class);
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
}
