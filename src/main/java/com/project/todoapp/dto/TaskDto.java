package com.project.todoapp.dto;


import lombok.Data;


@Data
public class TaskDto {
    private String title;
    private String description;
    private Boolean completed;
    private Long userId;
}
