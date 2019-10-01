package com.spring.dao;

import java.util.List;

import com.spring.model.Task;

public interface TaskDao {

	void save(Task task);
	
	void update(Task task);

	List<Task> list();
	
	List<Task> listByTask(String task);
	
	void delete(Task task);

	Task findById(Long task_ID);

	List<Task> listByProject(String project_Name);

}
