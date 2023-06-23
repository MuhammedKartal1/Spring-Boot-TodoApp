package com.project.todoapp.graphql.resollver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.project.todoapp.dto.UserDto;
import com.project.todoapp.services.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final UserService userService;

    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    public UserDto getOneUser(Long userId) {
        return userService.getOneUser(userId);
    }

    public UserDto createUser(String username, String password) {
        UserDto newUser = new UserDto();
        newUser.setUsername(username);
        newUser.setPassword(password);
        return userService.createNewUser(newUser);
    }

    public UserDto updateOneUser(Long userId, String username, String password) {
        UserDto updatedUser = new UserDto();
        updatedUser.setUsername(username);
        updatedUser.setPassword(password);
        return userService.updateOneUser(userId, updatedUser);
    }

    public Boolean deleteOneUser(Long userId) {
        return userService.deleteById(userId);
    }
}

