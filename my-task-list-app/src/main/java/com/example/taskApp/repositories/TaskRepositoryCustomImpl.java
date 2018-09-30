package com.example.taskApp.repositories;

import com.example.taskApp.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;

public class TaskRepositoryCustomImpl implements TaskRepositoryCustom
{
		
	private final MongoOperations operations;
	
	@Autowired
	public TaskRepositoryCustomImpl(MongoOperations operations) {
		this.operations = operations;
	}

	@Override
    public List<Task> findByStatus(String status) {
		Query searchQuery = new Query();
		if(!status.equals("")){
			if("pending".equals(status)){
				searchQuery.addCriteria(Criteria.where("completed").is(Boolean.FALSE));
			}else if("completed".equals(status)){
				searchQuery.addCriteria(Criteria.where("completed").is(Boolean.TRUE));
			}
		}
		searchQuery.with(new Sort(Sort.Direction.DESC, "createdOn"));
		List<Task> tasks = operations.find(searchQuery, Task.class);
		return tasks;
	}
}