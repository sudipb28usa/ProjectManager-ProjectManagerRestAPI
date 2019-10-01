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

import com.spring.dao.ProjectDao;
import com.spring.model.Project;
import com.spring.model.Task;
import com.spring.model.User;
import com.spring.service.ProjectServiceImpl;

 
 
 
public class ProjectServiceTest extends AbstractServiceTest {
	

	 
		@InjectMocks
		ProjectServiceImpl service;

		@Mock
		ProjectDao dao;

	 

		@Before
		public void init() {
			MockitoAnnotations.initMocks(this);

		}

		@Test
		public void saveTest() {
	 //String project_Name, Date start_Date, Date end_Date, int priority, Set<Task> taskSet, Set<User> userSet
			
			
		 
			Project project=new Project("Project1_test",new Date(1),new Date(2),2,new HashSet<Task>(),new HashSet<User>());		
	 
			service.save(project);

			verify(dao, times(1)).save(project);
		}
		
		@Test
		public void updateTest() {
	 
			Project project=new Project("Project1_test",new Date(1),new Date(2),2,new HashSet<Task>(),new HashSet<User>());		
			 
			service.save(project);	
			
			when(dao.findById(project.getProject_ID())).thenReturn(project);
			
			project=service.findById(project.getProject_ID());
			
			project.setProject_Name("Project1_test_UPD");
	 
			service.update(project); 

			verify(dao, times(1)).update(project);
		}
	
		@Test
		public void deleteTest() {
	 
			Project project=new Project("Project1_test",new Date(1),new Date(2),2,new HashSet<Task>(),new HashSet<User>());		
			 
			service.save(project);	
			
			when(dao.findById(project.getProject_ID())).thenReturn(project);
			
			project=service.findById(project.getProject_ID());
	 
			service.delete(project); 

			verify(dao, times(1)).delete(project);
		}
		
		
		
	
		@Test
		public void findByIdTest() {
			
			Project project=new Project("Project1_test",new Date(1),new Date(2),2,new HashSet<Task>(),new HashSet<User>());		
			 
			service.save(project);	
			
			when(dao.findById(project.getProject_ID())).thenReturn(project);	
							 		 
			Project projectByID = service.findById(project.getProject_ID()); 
			
			assertEquals("Project1_test", projectByID.getProject_Name());
			
			verify(dao, times(1)).findById(project.getProject_ID());

		}
			
		
		@Test
		public void listAllTest() {
			
			List<Project> listProject = new ArrayList<Project>();
			
			Project project=new Project("Project1_test",new Date(1),new Date(2),2,new HashSet<Task>(),new HashSet<User>());
			listProject.add(project);
			
			project=new Project("Project2_test",new Date(1),new Date(2),2,new HashSet<Task>(),new HashSet<User>());
			listProject.add(project);
			
			project=new Project("Project3_test",new Date(1),new Date(2),2,new HashSet<Task>(),new HashSet<User>());
			listProject.add(project);			
							 
			when(dao.list()).thenReturn(listProject);

			List<Project> projectList = service.list();

			assertEquals(3, projectList.size());
			
			verify(dao, times(1)).list();

		}
		 	
		
		@Test
		public void listByProjectTest() {
			
			String projectName = "Project1_test";
			
			List<Project> listProject = new ArrayList<Project>();
			
			Project project=new Project("Project1_test",new Date(1),new Date(2),2,new HashSet<Task>(),new HashSet<User>());
			listProject.add(project);
			
			project=new Project("Project2_test",new Date(1),new Date(2),2,new HashSet<Task>(),new HashSet<User>());
			listProject.add(project);
			
			project=new Project("Project3_test",new Date(1),new Date(2),2,new HashSet<Task>(),new HashSet<User>());
			listProject.add(project);								
			 
			when(dao.listByProject(projectName)).thenReturn(listProject);

			List<Project> projectList = service.listByProject(projectName);
			
			Project projectSearch=projectList.get(0);

			assertEquals("Project1_test", projectSearch.getProject_Name());
			 
		}
		
		
		
	 
	 
 
 
	

}
