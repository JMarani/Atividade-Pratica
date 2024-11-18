package com.kanban.service;

import com.kanban.model.Task;
import com.kanban.model.TaskStatus;
import com.kanban.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    
    public Task createTask(Task task) {
        task.setStatus(TaskStatus.TODO);
        return taskRepository.save(task);
    }
    
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatusOrderByPriorityDesc(status);
    }
    
    public Task moveTask(Long id) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));
            
        switch (task.getStatus()) {
            case TODO:
                task.setStatus(TaskStatus.IN_PROGRESS);
                break;
            case IN_PROGRESS:
                task.setStatus(TaskStatus.DONE);
                break;
            default:
                throw new RuntimeException("Task already completed");
        }
        
        return taskRepository.save(task);
    }
    
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));
            
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setPriority(taskDetails.getPriority());
        task.setDueDate(taskDetails.getDueDate());
        
        return taskRepository.save(task);
    }
    
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
