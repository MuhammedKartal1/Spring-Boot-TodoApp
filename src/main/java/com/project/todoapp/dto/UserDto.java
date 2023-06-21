package com.project.todoapp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserDto {
    private String username;
    private String password;
}
