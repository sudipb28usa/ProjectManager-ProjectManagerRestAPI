package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.UserDao;
import com.spring.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional
	public void save(User user) {
		userDao.save(user);
	}
	
	@Transactional
	public void update(User user) {
		userDao.update(user);
	}

	@Transactional(readOnly = true)
	public List<User> list() {
		return userDao.list();
	}

	@Transactional(readOnly = true)
	public List<User> listByFirst_Name(String first_Name) {
		return userDao.listByFirst_Name(first_Name);
	}

	@Transactional
	public void delete(User user) {
		userDao.delete(user);

	}

	@Transactional(readOnly = true)
	public User findById(Long user_ID) {
		return userDao.findById(user_ID);
	}

	@Transactional(readOnly = true)
	public User findByTaskId(Long task_ID) {
		// TODO Auto-generated method stub
		return userDao.findByTaskId(task_ID);
	}

}
