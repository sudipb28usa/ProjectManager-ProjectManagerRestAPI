package com.spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.User;

@Repository
public class UserDaoImpl implements UserDao {

		@Autowired
	   private SessionFactory sessionFactory;
	 
	   @Override
	   public void save(User user) {
	      sessionFactory.getCurrentSession().save(user);
	   }
	   
	   @Override
	   public void update(User user) {
	      sessionFactory.getCurrentSession().update(user);
	   }
	 
	   @Override
	   public List<User> list() {
	      @SuppressWarnings("unchecked")
	      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
	      return query.getResultList();
	   }

	@Override
	public List<User> listByFirst_Name(String first_Name) {
		 @SuppressWarnings("unchecked")
		  TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User u fetch all properties where u.first_Name like '%"+first_Name+"%'");
		return query.getResultList();
	}

	@Override
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}
	
	@Override
	public User findById(Long user_ID) {
		User user = (User) sessionFactory.getCurrentSession().get(User.class, user_ID);
		return user; 
	}
	
	
	@Override
	public User findByTaskId(Long task_ID) {
		 User user =null;
		@SuppressWarnings("unchecked")
		 TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("Select  u from User u  join u.task t where  t.task_ID = "+task_ID+"");
		 if(query.getResultList().size()>0) {
			 user = (User) query.getResultList().get(0);
		 }
		 
		return user; 
	}

}
