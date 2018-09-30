package com.example.taskApp.controllers;

import javax.validation.Valid;
import com.example.taskApp.models.Task;
import com.example.taskApp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Calendar;

@RestController
@RequestMapping("/api")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:8000")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        Sort sortByCreatedOnDesc = new Sort(Sort.Direction.DESC, "createdOn");
        return taskRepository.findAll(sortByCreatedOnDesc);
    }

    @PostMapping(value="/tasks")
    public Task createTask(@RequestBody Task task) {
        task.setCompleted(false);
        return taskRepository.save(task);
    }

    @GetMapping(value="/tasks/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable("taskId") String taskId) {
        return taskRepository.findById(taskId)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }
	
	@GetMapping(value="/tasks/status/{status}")
    public List<Task> getTaskByStatus(@PathVariable("status") String status) {
        return taskRepository.findByStatus(status);
    }

    @PutMapping(value="/tasks/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable("taskId") String taskId, @Valid @RequestBody Task task) {
        return taskRepository.findById(taskId)
                .map(taskData -> {
                    taskData.setCompleted(true);
                    Task updatedTask = taskRepository.save(taskData);
                    return ResponseEntity.ok().body(updatedTask);
                }).orElse(ResponseEntity.notFound().build());
    }
	
    @DeleteMapping(value="/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable("taskId") String taskId) {
        return taskRepository.findById(taskId)
                .map(task -> {
                    taskRepository.deleteById(taskId);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}