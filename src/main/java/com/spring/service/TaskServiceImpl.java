package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.TaskDao;
import com.spring.model.Task;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;

	@Transactional
	public void save(Task task) {
		taskDao.save(task);
	}
	
	@Transactional
	public void update(Task task) {
		taskDao.update(task);
	}

	@Transactional(readOnly = true)
	public List<Task> list() {
		return taskDao.list();
	}

	@Transactional(readOnly = true)
	public List<Task> listByTask(String task) {
		return taskDao.listByTask(task);
	}

	@Transactional
	public void delete(Task task) {
		taskDao.delete(task);

	}

	@Transactional(readOnly = true)
	public Task findById(Long task_ID) {
		return taskDao.findById(task_ID);
	}
	
	@Transactional(readOnly = true)
	public List<Task> listByProject(String project_Name) {
		return taskDao.listByProject(project_Name);
	}

}
