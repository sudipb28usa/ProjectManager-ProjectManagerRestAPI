package com.spring.dao;

import java.util.List;

import com.spring.model.Project;

public interface ProjectDao {

	void save(Project project);
	
	void update(Project project);

	List<Project> list();
	
	List<Project> listByProject(String project);
	
	void delete(Project project);

	Project findById(Long project_ID);

}
