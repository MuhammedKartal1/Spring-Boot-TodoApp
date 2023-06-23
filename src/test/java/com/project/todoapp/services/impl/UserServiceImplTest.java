package com.project.todoapp.services.impl;

import com.project.todoapp.dto.UserDto;
import com.project.todoapp.entities.User;
import com.project.todoapp.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class UserServiceImplTest {

    private UserRepository userRepository;
    private UserServiceImpl userService;

    private User user1 ;

    private List<User> users;

    @Before
    public void setUp() throws Exception {

        userRepository =  Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);

        user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("password1");

        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("password2");

        users= new ArrayList<>();
        users.add(user1);
        users.add(user2);

    }

    @Test
    public void whenGetAllUsersCalledWithValidRequest_itShouldReturnUserDtoList(){

        List<UserDto> userDtoList ;
        Mockito.when(userRepository.findAll()).thenReturn(users);
        userDtoList = userService.getAllUsers();

        List<UserDto> expectedUserDtoList = new ArrayList<>();
        UserDto userDto1 = new UserDto();
        userDto1.setUsername("user1");
        userDto1.setPassword("password1");
        expectedUserDtoList.add(userDto1);

        UserDto userDto2 = new UserDto();
        userDto2.setUsername("user2");
        userDto2.setPassword("password2");
        expectedUserDtoList.add(userDto2);

        assertEquals(expectedUserDtoList.size(), userDtoList.size());

        for (int i = 0; i < expectedUserDtoList.size(); i++) {
            UserDto expectedUserDto = expectedUserDtoList.get(i);
            UserDto actualUserDto = userDtoList.get(i);
            assertEquals(expectedUserDto.getUsername(), actualUserDto.getUsername());
            assertEquals(expectedUserDto.getPassword(), actualUserDto.getPassword());
        }

         verify(userRepository).findAll();

    }


    @Test
    public void whenCreateNewUserCalledWithValidRequest_itShouldReturnUserDto(){

        UserDto userDto = new UserDto();
        userDto.setUsername(user1.getUsername());
        userDto.setPassword(user1.getPassword());

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user1);

        UserDto userDtoExpected =  userService.createNewUser(userDto);

        assertEquals(userDto.getUsername(), userDtoExpected.getUsername());
        assertEquals(userDto.getPassword(), userDtoExpected.getPassword());

        verify(userRepository).save(Mockito.any(User.class));

    }


}