package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.ProjectDao;
import com.spring.model.Project;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Transactional
	public void save(Project project) {
		projectDao.save(project);
	}
	
	@Transactional
	public void update(Project project) {
		projectDao.update(project);
	}

	@Transactional(readOnly = true)
	public List<Project> list() {
		return projectDao.list();
	}

	@Transactional(readOnly = true)
	public List<Project> listByProject(String project) {
		return projectDao.listByProject(project);
	}

	@Transactional
	public void delete(Project project) {
		projectDao.delete(project);

	}

	@Transactional(readOnly = true)
	public Project findById(Long project_ID) {
		return projectDao.findById(project_ID);
	}

}
