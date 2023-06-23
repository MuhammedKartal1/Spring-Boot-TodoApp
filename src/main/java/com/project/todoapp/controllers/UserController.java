package com.project.todoapp.controllers;


import com.project.todoapp.dto.UserDto;
import com.project.todoapp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Tüm kullanıcıların bilgilerini getirmek için
     * @return response entity
     */
    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    /**
     * Id'si verilen kuıllanıcıların bilgilerini ketirmek için
     * @param userId id
     * @return response entity
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getOneUser(@PathVariable Long userId){

        UserDto userDto = userService.getOneUser(userId);

        return ResponseEntity.ok(userDto);
    }

    /**
     * Yeni kullanıcı eklemek için
     * @param newUser userDto
     * @return response entity
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto newUser){
        return ResponseEntity.ok(userService.createNewUser(newUser));
    }


    /**
     * Id'si verilen kullanıcıyı güncellemek için
     * @param userId id
     * @param newUser userDto
     * @return response entity
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateOneUser(@PathVariable Long userId, @RequestBody UserDto newUser){
        return ResponseEntity.ok(userService.updateOneUser(userId, newUser));
    }

    /**
     * Id'si verilen bir kullanıcıyı silmek için
     * @param userId id
     * @return response entity
     */
    @DeleteMapping("{userId}")
    public ResponseEntity<Boolean>deleteOneUser(@PathVariable Long userId){
        Boolean status = userService.deleteById(userId);
        return ResponseEntity.ok(status);
    }

}
