package com.demonstration.web;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demonstration.dao.TaskRepository;
import com.demonstration.entities.Task;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")

public class TaskRestController {
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/tasks")
	@CrossOrigin(origins = "http://localhost:4200")

	public List<Task> getListTasks(){
		return taskRepository.findAll();
	}
	
		
	@PostMapping("/tasks")
	@CrossOrigin(origins = "http://localhost:4200")
	public Task save(@RequestBody Task t) {
		return taskRepository.save(t);
	}
	

}