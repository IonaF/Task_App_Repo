package com.example.taskApp.repositories;

import com.example.taskApp.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, String>, TaskRepositoryCustom {

}