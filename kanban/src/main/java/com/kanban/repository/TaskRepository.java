package com.kanban.repository;

import com.kanban.model.Task;
import com.kanban.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByStatusOrderByPriorityDesc(TaskStatus status);
}
