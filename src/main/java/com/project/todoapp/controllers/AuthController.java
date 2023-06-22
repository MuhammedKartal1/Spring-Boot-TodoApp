package com.project.todoapp.controllers;

import com.project.todoapp.dto.UserDto;
import com.project.todoapp.security.JwtTokenProvider;
import com.project.todoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private AuthenticationManager authenticationManager;


    private JwtTokenProvider jwtTokenProvider;


    private UserService userService;


    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, UserService userService,
                          PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;

    }

    //private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken( userDto.getUsername(),  passwordEncoder.encode(userDto.getPassword()));
        System.out.println(passwordEncoder.encode(userDto.getPassword().toString()));
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return "Bearer " + jwtToken;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
        if(userService.getOneUserByName(userDto.getUsername()) != null )
            return new ResponseEntity<>("Username is already in use", HttpStatus.BAD_REQUEST);

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userService.createNewUser(userDto);
        return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);

    }




}
