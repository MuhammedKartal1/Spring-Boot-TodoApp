package com.project.todoapp.graphql.data_fetcher;

import com.project.todoapp.dto.UserDto;
import com.project.todoapp.services.UserService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraphQLDataFetchers {

    @Autowired
    private UserService userService;

    public DataFetcher getAllUsersDataFetcher() {
        return dataFetchingEnvironment -> userService.getAllUsers();
    }

    public DataFetcher getOneUserDataFetcher() {
        return dataFetchingEnvironment -> {
            Long userId = Long.parseLong(dataFetchingEnvironment.getArgument("userId"));
            return userService.getOneUser(userId);
        };
    }

    public DataFetcher createUserDataFetcher() {
        return dataFetchingEnvironment -> {
            UserDto userDto = new UserDto();
            userDto.setUsername(dataFetchingEnvironment.getArgument("username"));
            userDto.setPassword(dataFetchingEnvironment.getArgument("password"));
            return userService.createNewUser(userDto);
        };
    }

    public DataFetcher updateOneUserDataFetcher() {
        return dataFetchingEnvironment -> {
            Long userId = Long.parseLong(dataFetchingEnvironment.getArgument("userId"));
            UserDto userDto = new UserDto();
            userDto.setUsername(dataFetchingEnvironment.getArgument("username"));
            userDto.setPassword(dataFetchingEnvironment.getArgument("password"));
            return userService.updateOneUser(userId, userDto);
        };
    }

    public DataFetcher deleteOneUserDataFetcher() {
        return dataFetchingEnvironment -> {
            Long userId = Long.parseLong(dataFetchingEnvironment.getArgument("userId"));
            return userService.deleteById(userId);
        };
    }
}
