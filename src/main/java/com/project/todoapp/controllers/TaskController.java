package com.project.todoapp.controllers;

import com.project.todoapp.dto.TaskDto;
import com.project.todoapp.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Bir kullanıcıya ait tüm görevleri listelemek için
     * @param userId id
     * @return response entity
     */
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(@RequestParam(value = "userId", required = false) Long userId){
        return ResponseEntity.ok(taskService.getAllTasks(Optional.ofNullable(userId)));
    }

    /**
     * Tamamlanan görevleri listelemek için
     * @param userId id
     * @return response entity
     */
    @GetMapping("/completed")
    public ResponseEntity<List<TaskDto>> getCompletedTasks(@RequestParam(value = "userId", required = false) Long userId){
        return ResponseEntity.ok(taskService.getCompletedTasks(Optional.ofNullable(userId)));
    }

    /**
     * Belirli bir görevin detaylarını getirmek için
     * @param taskId id
     * @return response entity
     */
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getOneTaskById(@PathVariable Long taskId){
        return ResponseEntity.ok(taskService.getOneTask(taskId));
    }

    /**
     * Yeni görev oluşturmak için
     * @param newTaskDto TaskDto
     * @return response entity
     */
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto newTaskDto){
        return ResponseEntity.ok(taskService.createNewTask(newTaskDto));
    }

    /**
     *Belirli bir görevi güncellemek için
     * @param taskId id
     * @param newTaskDto TaskDto
     * @return response entity
     */
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateOneTask(@RequestParam Long taskId, @RequestBody TaskDto newTaskDto){
        return ResponseEntity.ok(taskService.updateOneTask(taskId,newTaskDto));
    }

    /**
     * Belirli bir görevi tamamladığını işaretlemek için
     * @param taskId id
     * @return response entity
     */
    @PutMapping("/{taskId}/complete")
    public ResponseEntity<TaskDto> completeTask(@PathVariable Long taskId){
        return ResponseEntity.ok(taskService.completeTask(taskId));
    }

    /**
     * Belirli bir görevi silmek için
     * @param taskId id
     * @return response entity
     */
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Boolean> deleteOneTask(@PathVariable Long taskId){
        Boolean status = taskService.deleteById(taskId);
        return ResponseEntity.ok(status);
    }


}
