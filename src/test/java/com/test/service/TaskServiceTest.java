package com.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.spring.dao.ParentTaskDao;
import com.spring.dao.ProjectDao;
import com.spring.dao.TaskDao;
import com.spring.model.ParentTask;
import com.spring.model.Project;
import com.spring.model.Task;
import com.spring.model.Task;
import com.spring.model.User;
import com.spring.service.ParentTaskServiceImpl;
import com.spring.service.ProjectServiceImpl;
import com.spring.service.TaskServiceImpl;

 
 
 
public class TaskServiceTest extends AbstractServiceTest {
	

	 
		@InjectMocks
		TaskServiceImpl service;
		
		@InjectMocks
		ProjectServiceImpl serviceProject;
		
		@InjectMocks
		ParentTaskServiceImpl serviceParent;

		@Mock
		TaskDao dao;
		
		@Mock
		ProjectDao daoProject;
		
		@Mock
		ParentTaskDao daoParent;
		
		ParentTask parentTask;
		
		Project project;
		
		

	 

		@Before
		public void init() {
			MockitoAnnotations.initMocks(this);
			
			this.parentTask=new ParentTask("Parent1_test", new HashSet<Task>());		
			 
			serviceParent.save(this.parentTask);
			 
			
			this.project=new Project("Project1_test",new Date(1),new Date(2),2,new HashSet<Task>(),new HashSet<User>());		
			 
			serviceProject.save(this.project);

		}
		
		

		@Test
		public void saveTest() {
	 	 					 					
			Task task=new Task("Task1_test",new Date(1),new Date(2),2, "New" ,this.parentTask, this.project);		
	 
			service.save(task);

			verify(dao, times(1)).save(task); 
		}
		
		
		
		@Test
		public void updateTest() { 
				 
			Task task=new Task("Task1_test",new Date(1),new Date(2),2, "New" ,this.parentTask,this. project);		
	 
			service.save(task);
			
			
			when(dao.findById(task.getTask_ID())).thenReturn(task);
			
			task=service.findById(task.getTask_ID());
			
			task.setTask_Name("Task1_test_UPD");
	 
			service.update(task); 

			verify(dao, times(1)).update(task);
		}
	
		
		@Test
		public void deleteTest() {
	 
			Task task=new Task("Task1_test",new Date(1),new Date(2),2, "New" ,this.parentTask,this. project);		
			 
			service.save(task);	
			
			when(dao.findById(task.getTask_ID())).thenReturn(task);
			
			task=service.findById(task.getTask_ID());
	 
			service.delete(task); 

			verify(dao, times(1)).delete(task);
		}
		
		
		
		
		@Test
		public void findByIdTest() {
			
			Task task=new Task("Task1_test",new Date(1),new Date(2),2, "New" ,this.parentTask,this. project);		
			 
			service.save(task);	
			
			when(dao.findById(task.getTask_ID())).thenReturn(task);	
							 		 
			Task taskByID = service.findById(task.getTask_ID()); 
			
			assertEquals("Task1_test", taskByID.getTask_Name());
			
			verify(dao, times(1)).findById(task.getTask_ID());

		}
			
		 	
		@Test
		public void listAllTest() {
			
			List<Task> listTask = new ArrayList<Task>();
			
			Task task=new Task("Task1_test",new Date(1),new Date(2),2, "New" ,this.parentTask,this. project);
			listTask.add(task);
			
			task=new Task("Task2_test",new Date(1),new Date(2),2, "New" ,this.parentTask,this. project);
			listTask.add(task);
			
			task=new Task("Task3_test",new Date(1),new Date(2),2, "New" ,this.parentTask,this. project);
			listTask.add(task);			
							 
			when(dao.list()).thenReturn(listTask);

			List<Task> taskList = service.list();

			assertEquals(3, taskList.size());
			
			verify(dao, times(1)).list();

		}
		 	
		
		@Test
		public void listByTaskTest() {
			
			String taskName = "Task1_test";
			
			List<Task> listTask = new ArrayList<Task>();
			
			Task task=new Task("Task1_test",new Date(1),new Date(2),2, "New" ,this.parentTask,this. project);
			listTask.add(task);
			
			task=new Task("Task2_test",new Date(1),new Date(2),2, "New" ,this.parentTask,this. project);
			listTask.add(task);
			
			task=new Task("Task3_test",new Date(1),new Date(2),2, "New" ,this.parentTask,this. project);
			listTask.add(task);								
			 
			when(dao.listByTask(taskName)).thenReturn(listTask);

			List<Task> taskList = service.listByTask(taskName);
			
			Task taskSearch=taskList.get(0);

			assertEquals("Task1_test", taskSearch.getTask_Name());
			 
		}
		
		
		@Test
		public void listByProjectTest() {
			
			String projectName = "Project1_test";
			
			List<Task> listTask = new ArrayList<Task>();
			
			Task task=new Task("Task1_test",new Date(1),new Date(2),2, "New" ,this.parentTask,this. project);
			listTask.add(task);
			
			task=new Task("Task2_test",new Date(1),new Date(2),2, "New" ,this.parentTask,this. project);
			listTask.add(task);
			
			task=new Task("Task3_test",new Date(1),new Date(2),2, "New" ,this.parentTask,this. project);
			listTask.add(task);								
			 
			when(dao.listByProject(projectName)).thenReturn(listTask);

			List<Task> taskList = service.listByProject(projectName);
			

			assertEquals(3, taskList.size());
			verify(dao, times(1)).listByProject(projectName); 
			 
		}
		
		
		
	 
	 
 
 
	

}
