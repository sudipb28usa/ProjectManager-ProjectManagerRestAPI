package com.spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.ParentTask;

@Repository
public class ParentTaskDaoImpl implements ParentTaskDao {

		@Autowired
	   private SessionFactory sessionFactory;
	 
	   @Override
	   public void save(ParentTask parentTask) {
	      sessionFactory.getCurrentSession().save(parentTask);
	   }
	   
	   @Override
	   public void update(ParentTask parentTask) {
	      sessionFactory.getCurrentSession().update(parentTask);
	   }
	 
	   @Override
	   public List<ParentTask> list() {
	      @SuppressWarnings("unchecked")
	      TypedQuery<ParentTask> query = sessionFactory.getCurrentSession().createQuery("from ParentTask");
	      return query.getResultList();
	   }

	@Override
	public List<ParentTask> listByParentTask(String parentTask) {
		 @SuppressWarnings("unchecked")
		  TypedQuery<ParentTask> query = sessionFactory.getCurrentSession().createQuery("from ParentTask p fetch all properties where p.parent_Task like '%"+parentTask+"%'");
		return query.getResultList();
	}

	@Override
	public void delete(ParentTask parentTask) {
		sessionFactory.getCurrentSession().delete(parentTask);
	}
	
	@Override
	public ParentTask findById(Long parent_ID) {
		ParentTask parentTask = (ParentTask) sessionFactory.getCurrentSession().get(ParentTask.class, parent_ID);
		return parentTask; 
	}

}
