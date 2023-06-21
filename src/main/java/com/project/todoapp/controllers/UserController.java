package com.project.todoapp.controllers;


import com.project.todoapp.entities.User;
import com.project.todoapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody Long userId){
        return userService.createNewUser(userId);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        return userService.getOneUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser){
        return  userService.updateOneUser(userId, newUser);
    }

    @DeleteMapping("{userId}")
    public Boolean deleteOneUser(@PathVariable Long userId){
        return userService.deleteById(userId);
    }



}
