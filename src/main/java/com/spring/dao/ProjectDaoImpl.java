package com.spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.Project;

@Repository
public class ProjectDaoImpl implements ProjectDao {

		@Autowired
	   private SessionFactory sessionFactory;
	 
	   @Override
	   public void save(Project project) {
	      sessionFactory.getCurrentSession().save(project);
	   }
	   
	   @Override
	   public void update(Project project) {
	      sessionFactory.getCurrentSession().update(project);
	   }
	 
	   @Override
	   public List<Project> list() {
	      @SuppressWarnings("unchecked")
	      TypedQuery<Project> query = sessionFactory.getCurrentSession().createQuery("from Project");
	      return query.getResultList();
	   }

	@Override
	public List<Project> listByProject(String project) {
		 @SuppressWarnings("unchecked")
		  TypedQuery<Project> query = sessionFactory.getCurrentSession().createQuery("from Project p fetch all properties where p.projectName like '%"+project+"%'");
		return query.getResultList();
	}

	@Override
	public void delete(Project project) {
		sessionFactory.getCurrentSession().delete(project);
	}
	
	@Override
	public Project findById(Long project_ID) {
		Project project = (Project) sessionFactory.getCurrentSession().get(Project.class, project_ID);
		return project; 
	}

}
