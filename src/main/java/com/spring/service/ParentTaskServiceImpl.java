package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.ParentTaskDao;
import com.spring.model.ParentTask;

@Service
public class ParentTaskServiceImpl implements ParentTaskService {

	@Autowired
	private ParentTaskDao parentTaskDao;

	@Transactional
	public void save(ParentTask parentTask) {
		parentTaskDao.save(parentTask);
	}
	
	@Transactional
	public void update(ParentTask parentTask) {
		parentTaskDao.update(parentTask);
	}

	@Transactional(readOnly = true)
	public List<ParentTask> list() {
		return parentTaskDao.list();
	}

	@Transactional(readOnly = true)
	public List<ParentTask> listByParentTask(String parentTask) {
		return parentTaskDao.listByParentTask(parentTask);
	}

	@Transactional
	public void delete(ParentTask parentTask) {
		parentTaskDao.delete(parentTask);

	}

	@Transactional(readOnly = true)
	public ParentTask findById(Long parent_ID) {
		return parentTaskDao.findById(parent_ID);
	}

}
