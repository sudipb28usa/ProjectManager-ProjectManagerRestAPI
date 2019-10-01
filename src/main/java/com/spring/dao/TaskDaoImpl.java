package com.spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.Task;

@Repository
public class TaskDaoImpl implements TaskDao {

		@Autowired
	   private SessionFactory sessionFactory;
	 
	   @Override
	   public void save(Task task) {
	      sessionFactory.getCurrentSession().save(task);
	   }
	   
	   @Override
	   public void update(Task task) {
	      sessionFactory.getCurrentSession().update(task);
	   }
	 
	   @Override
	   public List<Task> list() {
	      @SuppressWarnings("unchecked")
	      TypedQuery<Task> query = sessionFactory.getCurrentSession().createQuery("from Task");
	      return query.getResultList();
	   }

	@Override
	public List<Task> listByTask(String task) {
		 @SuppressWarnings("unchecked")
		  TypedQuery<Task> query = sessionFactory.getCurrentSession().createQuery("from Task t fetch all properties where t.task_Name like '%"+task+"%'");
		return query.getResultList();
	}

	@Override
	public void delete(Task task) {
		sessionFactory.getCurrentSession().delete(task);
	}
	
	@Override
	public Task findById(Long task_ID) {
		Task task = (Task) sessionFactory.getCurrentSession().get(Task.class, task_ID);
		return task; 
	}
	
	@Override
	public List<Task> listByProject(String project_Name) {
		 @SuppressWarnings("unchecked")
		  TypedQuery<Task> query = sessionFactory.getCurrentSession().createQuery("Select t from Task t join t.project p  where p.project_Name like '%"+project_Name+"%'");
		return query.getResultList();
	}

}
