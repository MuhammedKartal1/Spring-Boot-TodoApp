package com.project.todoapp.services.impl;

import com.project.todoapp.entities.User;
import com.project.todoapp.repositories.UserRepository;
import com.project.todoapp.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user =  userRepository.findByUsername(username);
       return JwtUserDetails.create(user);

    }
    public UserDetails loadUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.map(JwtUserDetails::create).orElse(null);
    }
}
