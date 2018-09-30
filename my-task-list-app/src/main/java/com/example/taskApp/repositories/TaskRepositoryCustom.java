package com.example.taskApp.repositories;

import com.example.taskApp.models.Task;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepositoryCustom {

    List <Task> findByStatus(String status);
}