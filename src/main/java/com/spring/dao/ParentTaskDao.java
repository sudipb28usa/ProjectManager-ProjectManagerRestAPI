package com.spring.dao;

import java.util.List;

import com.spring.model.ParentTask;

public interface ParentTaskDao {

	void save(ParentTask parentTask);
	
	void update(ParentTask parentTask);

	List<ParentTask> list();
	
	List<ParentTask> listByParentTask(String parentTask);
	
	void delete(ParentTask parentTask);

	ParentTask findById(Long parent_ID);

}
