package com.project.todoapp.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
public class TaskDto {
    private String title;
    private String description;
    private Boolean completed;
    private Long userId;
}
