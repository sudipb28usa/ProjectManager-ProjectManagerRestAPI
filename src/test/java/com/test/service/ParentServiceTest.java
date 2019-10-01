package com.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.spring.dao.ParentTaskDao;
import com.spring.model.ParentTask;
import com.spring.model.Task;
import com.spring.service.ParentTaskServiceImpl;

 
 
 
public class ParentServiceTest extends AbstractServiceTest {
	

	 
		@InjectMocks
		ParentTaskServiceImpl service;

		@Mock
		ParentTaskDao dao;

	 

		@Before
		public void init() {
			MockitoAnnotations.initMocks(this);

		}

		@Test
		public void saveTest() {
	 
			ParentTask parentTask=new ParentTask("Parent1_test", new HashSet<Task>());		
	 
			service.save(parentTask);

			verify(dao, times(1)).save(parentTask);
		}
		
		@Test
		public void updateTest() {
	 
			ParentTask parentTask=new ParentTask("Parent1_test", new HashSet<Task>());		
			 
			service.save(parentTask);	
			
			when(dao.findById(parentTask.getParent_ID())).thenReturn(parentTask);
			
			parentTask=service.findById(parentTask.getParent_ID());
			
			parentTask.setParent_Task("Parent1_test_UPD");
	 
			service.update(parentTask); 

			verify(dao, times(1)).update(parentTask);
		}
		
		
		@Test
		public void deleteTest() {
	 
			ParentTask parentTask=new ParentTask("Parent1_test", new HashSet<Task>());		
			 
			service.save(parentTask);	
			
			when(dao.findById(parentTask.getParent_ID())).thenReturn(parentTask);
			
			parentTask=service.findById(parentTask.getParent_ID());
	 
			service.delete(parentTask); 

			verify(dao, times(1)).delete(parentTask);
		}
		
		
		
		@Test
		public void findByIdTest() {
			
			ParentTask parentTask=new ParentTask("Parent1_test", new HashSet<Task>());		
			 
			service.save(parentTask);	
			
			when(dao.findById(parentTask.getParent_ID())).thenReturn(parentTask);	
							 		 
			ParentTask parentTaskByID = service.findById(parentTask.getParent_ID()); 
			
			assertEquals("Parent1_test", parentTaskByID.getParent_Task());
			
			verify(dao, times(1)).findById(parentTask.getParent_ID());

		}
		
		
		@Test
		public void listAllTest() {
			
			List<ParentTask> listP = new ArrayList<ParentTask>();
			
			ParentTask parentTask=new ParentTask("Parent1_test", new HashSet<Task>());
			listP.add(parentTask);
			
			parentTask=new ParentTask("Parent2_test", new HashSet<Task>());
			listP.add(parentTask);
			
			parentTask=new ParentTask("Parent3_test", new HashSet<Task>());
			listP.add(parentTask);			
							 
			when(dao.list()).thenReturn(listP);

			List<ParentTask> parentTaskList = service.list();

			assertEquals(3, parentTaskList.size());
			
			verify(dao, times(1)).list();

		}
		
		
		@Test
		public void listByParentTaskTest() {
			
			String parentTaskName = "Parent1_test";
			
			List<ParentTask> listP = new ArrayList<ParentTask>();
			
			ParentTask parentTask=new ParentTask("Parent1_test", new HashSet<Task>());
			listP.add(parentTask);
			
			parentTask=new ParentTask("Parent2_test", new HashSet<Task>());
			listP.add(parentTask);
			
			parentTask=new ParentTask("Parent3_test", new HashSet<Task>());
			listP.add(parentTask);							
			 
			when(dao.listByParentTask(parentTaskName)).thenReturn(listP);

			List<ParentTask> parentTaskList = service.listByParentTask(parentTaskName);
			
			ParentTask parentTaskSearch=parentTaskList.get(0);

			assertEquals("Parent1_test", parentTaskSearch.getParent_Task());
			 
		}
		
		
		
	 
	 
 
 
	

}
