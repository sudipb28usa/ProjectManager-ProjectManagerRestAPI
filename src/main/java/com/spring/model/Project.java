package com.spring.model;

 
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@Entity
@Table(name = "FSE_Project")
public class Project   {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	


    public Project() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Project(String project_Name, Date start_Date, Date end_Date, int priority,
			Set<Task> taskSet, Set<User> userSet) {
		super();
		this.project_Name = project_Name;
		this.start_Date = start_Date;
		this.end_Date = end_Date;
		this.priority = priority;
		this.taskSet = taskSet;
		this.userSet = userSet;
	}


	@Override
	public String toString() {
		return "Project [project_ID=" + project_ID + ", projectName=" + project_Name + ", start_Date=" + start_Date
				+ ", end_Date=" + end_Date + ", priority=" + priority + ", taskSet=" + taskSet + ", userSet=" + userSet
				+ "]";
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Project_ID")
	private Long project_ID;
	
    @Column(name = "Project") 
	private String project_Name;
    
    
    @Column(name = "Start_Date") 
	private Date start_Date;
    
    
    @Column(name = "End_Date") 
	private Date end_Date;
    
    
    @Column(name = "Priority") 
	private int priority;
    
    

    

    @OneToMany(mappedBy="project"  ,  fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
	private Set<Task> taskSet;
    
    
    
   @OneToMany(mappedBy="project"  ,  fetch = FetchType.EAGER ,  cascade = CascadeType.REMOVE)
	private Set<User> userSet;


	public Long getProject_ID() {
		return project_ID;
	}


	public void setProject_ID(Long project_ID) {
		this.project_ID = project_ID;
	}


	public String getProject_Name() {
		return project_Name;
	}


	public void setProject_Name(String project_Name) {
		this.project_Name = project_Name;
	}


	public Date getStart_Date() {
		return start_Date;
	}


	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
	}


	public Date getEnd_Date() {
		return end_Date;
	}


	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	public Set<Task> getTaskSet() {
		return taskSet;
	}


	public void setTaskSet(Set<Task> taskSet) {
		this.taskSet = taskSet;
	}


	public Set<User> getUserSet() {
		return userSet;
	}


	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
    
    

}
