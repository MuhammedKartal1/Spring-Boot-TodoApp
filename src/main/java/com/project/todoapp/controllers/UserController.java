package com.project.todoapp.controllers;


import com.project.todoapp.dto.UserDto;
import com.project.todoapp.entities.User;
import com.project.todoapp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Tüm kullanıcıların bilgilerini getirmek için
     * @return
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Id'si verilen kuıllanıcıların bilgilerini ketirmek için
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getOneUser(@PathVariable Long userId){

        UserDto userDto = userService.getOneUser(userId);

        return ResponseEntity.ok(userDto);
    }

    /**
     * Yeni kullanıcı eklemek için
     * @param newUser
     * @return
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto newUser){
        return ResponseEntity.ok(userService.createNewUser(newUser));
    }


    /**
     * Id'si verilen kullanıcıyı güncellemek için
     * @param userId
     * @param newUser
     * @return
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateOneUser(@PathVariable Long userId, @RequestBody UserDto newUser){
        return ResponseEntity.ok(userService.updateOneUser(userId, newUser));
    }

    /**
     * Id'si verilen bir kullanıcıyı silmek için
     * @param userId
     * @return
     */
    @DeleteMapping("{userId}")
    public ResponseEntity<Boolean>deleteOneUser(@PathVariable Long userId){
        Boolean status = userService.deleteById(userId);
        return ResponseEntity.ok(userService.deleteById(userId));
    }



}
