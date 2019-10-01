package com.spring.service;

import java.util.List;

import com.spring.model.User;

public interface UserService  {
	   void save(User user);
	   
	   void update(User user);
	   
	   List<User> list();
	   
		List<User> listByFirst_Name(String first_Name);
		
		void delete(User user);

		User findById(Long user_ID);
		
		User findByTaskId(Long task_ID);

 

}
