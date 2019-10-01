package com.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.spring.dao.ParentTaskDao;
import com.spring.dao.ProjectDao;
import com.spring.dao.TaskDao;
import com.spring.dao.UserDao;
import com.spring.model.ParentTask;
import com.spring.model.Project;
import com.spring.model.Task;
import com.spring.model.User;
import com.spring.service.ParentTaskServiceImpl;
import com.spring.service.ProjectServiceImpl;
import com.spring.service.TaskServiceImpl;
import com.spring.service.UserServiceImpl;

 
 
 
public class UserServiceTest extends AbstractServiceTest {
	

	 
		@InjectMocks
		UserServiceImpl service;
		
		@InjectMocks
		ProjectServiceImpl serviceProject;
		
		@InjectMocks
		ParentTaskServiceImpl serviceParent;
		
		@InjectMocks
		TaskServiceImpl serviceTask;

		@Mock
		UserDao dao;
		
		@Mock
		ProjectDao daoProject;
		
		@Mock
		ParentTaskDao daoParent;
		
		@Mock
		TaskDao daoPTask;
		
		Task task;
		
		Project project;
		
		ParentTask parentTask;
		
		

	 

		@Before
		public void init() {
			MockitoAnnotations.initMocks(this);
			
			this.parentTask=new ParentTask("Parent1_test", new HashSet<Task>());		
			 
			serviceParent.save(this.parentTask);
			
			
			this.project=new Project("Project1_test",new Date(1),new Date(2),2,new HashSet<Task>(),new HashSet<User>());		
			 
			serviceProject.save(this.project);
			
			task=new Task("Task1_test",new Date(1),new Date(2),2, "New" ,this.parentTask,this. project);		
			 
			serviceTask.save(task);	
			

		}
		
		

		@Test
		public void saveTest() {
 		 					
			User user=new User("FirstName" , "LastName", "259874" ,this.project , null);		
	 
			service.save(user);

			verify(dao, times(1)).save(user); 
		}
		
		
 		
		@Test
		public void updateTest() { 
				 
			User user=new User("FirstName" , "LastName", "259874" ,this.project , null);		
	 
			service.save(user);
			
			
			when(dao.findById(user.getUser_ID())).thenReturn(user);
			
			user=service.findById(user.getUser_ID());
			
			user.setFirst_Name("User1_test_UPD");
	 
			service.update(user); 

			verify(dao, times(1)).update(user);
		}
	
 		
		@Test
		public void deleteTest() {
	 
			User user=new User("FirstName" , "LastName", "259874" ,this.project , null);		
			 
			service.save(user);	
			
			when(dao.findById(user.getUser_ID())).thenReturn(user);
			
			user=service.findById(user.getUser_ID());
	 
			service.delete(user); 

			verify(dao, times(1)).delete(user);
		}
		
		
		
		
		@Test
		public void findByIdTest() {
			
			User user=new User("FirstName" , "LastName", "259874" ,this.project , null);		
			 
			service.save(user);	
			
			when(dao.findById(user.getUser_ID())).thenReturn(user);	
							 		 
			User userByID = service.findById(user.getUser_ID()); 
			
			assertEquals("FirstName", userByID.getFirst_Name());
			
			verify(dao, times(1)).findById(user.getUser_ID());

		}
			
	 	 	
		@Test
		public void listAllTest() {
			
			List<User> listUser = new ArrayList<User>();
			
			User user=new User("FirstName" , "LastName", "259874" ,this.project , null);
			listUser.add(user);
			
			
			user=new User("FirstName2" , "LastName2", "259874" ,null , this.task);
			listUser.add(user);			
							 
			when(dao.list()).thenReturn(listUser);

			List<User> userList = service.list();

			assertEquals(2, userList.size());
			
			verify(dao, times(1)).list();

		}
		 	
		
		@Test
		public void listByUserTest() {
			
			String firstName = "FirstName";
			
			List<User> listUser = new ArrayList<User>();
			
			User user=new User("FirstName" , "LastName", "259874" ,this.project , null);
			listUser.add(user);
			
			
			user=new User("FirstName2" , "LastName2", "259874" ,null , this.task);
			listUser.add(user);								
			 
			when(dao.listByFirst_Name(firstName)).thenReturn(listUser);

			List<User> userList = service.listByFirst_Name(firstName);
			
			User userSearch=userList.get(0);

			assertEquals("FirstName", userSearch.getFirst_Name());
			 
		}
		
		
		@Test
		public void findByTaskIdTest() {
			String firstName = "FirstName";
			
			User user=new User(firstName , "LastName2", "259874" ,null , this.task);
			 									 
			when(dao.findByTaskId(this.task.getTask_ID())).thenReturn(user);

			User userTask = service.findByTaskId(this.task.getTask_ID());
			 
			assertEquals(firstName, userTask.getFirst_Name());
			 
		}
		
		 
		
	 
	 
 
 
	

}
