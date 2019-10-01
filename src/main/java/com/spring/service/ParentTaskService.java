package com.spring.service;

import java.util.List;

import com.spring.model.ParentTask;

public interface ParentTaskService  {
	   void save(ParentTask parentTask);
	   
	   void update(ParentTask parentTask);
	   
	   List<ParentTask> list();
	   
		List<ParentTask> listByParentTask(String parentTask);
		
		void delete(ParentTask parentTask);

		ParentTask findById(Long parentTaskId);

 

}
