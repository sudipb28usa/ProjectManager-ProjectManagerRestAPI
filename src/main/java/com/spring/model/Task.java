package com.spring.model;

 
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

 
 



@Entity
@Table(name = "FSE_Task")
public class Task   {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    public Task() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Task( String task_Name, Date start_Date, Date end_Date,
			int priority, String status, ParentTask parentTask, Project project) {
		super();

 
		this.task_Name = task_Name;
		this.start_Date = start_Date;
		this.end_Date = end_Date;
		this.priority = priority;
		this.status = status;
		this.parentTask = parentTask;
		this.project = project;
		
		if(project!=null) {
			this.project_ID =project.getProject_ID();
		}
		
		
		if(parentTask!=null) {
			this.parent_ID =parentTask.getParent_ID();
		}
	}



	@Override
	public String toString() {
		return "Task [task_ID=" + task_ID + ", parent_ID=" + parent_ID + ", project_ID=" + project_ID + ", taskName="
				+ task_Name + ", start_Date=" + start_Date + ", end_Date=" + end_Date + ", priority=" + priority
				+ ", status=" + status + ", parentTask=" + parentTask + ", project=" + project + "]";
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Task_ID")
	private Long task_ID;
	

	transient private Long parent_ID;
    
    transient private Long project_ID;
    
    @Column(name = "Task") 
	private String task_Name;
    
    
    @Column(name = "Start_Date") 
	private Date start_Date;
    
    
    @Column(name = "End_Date") 
	private Date end_Date;
    
    
    @Column(name = "Priority") 
	private int priority;
    
    @Column(name = "Status") 
	private String status;
    
    
    @ManyToOne
    @JoinColumn(name = "Parent_ID")
    private ParentTask parentTask;
    
    
 
    @ManyToOne
    @JoinColumn(name = "Project_ID") 
    private Project project;
    
    @OneToOne(mappedBy = "task", fetch=FetchType.EAGER )
    private User user;
 


	public Long getTask_ID() {
		return task_ID;
	}



	public void setTask_ID(Long task_ID) {
		this.task_ID = task_ID;
	}



	public Long getParent_ID() {
		return parent_ID;
	}



	public void setParent_ID(Long parent_ID) {
		this.parent_ID = parent_ID;
	}



	public Long getProject_ID() {
		return project_ID;
	}



	public void setProject_ID(Long project_ID) {
		this.project_ID = project_ID;
	}



	public String getTask_Name() {
		return task_Name;
	}



	public void setTask_Name(String task_Name) {
		this.task_Name = task_Name;
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



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public ParentTask getParentTask() {
		return parentTask;
	}



	public void setParentTask(ParentTask parentTask) {
		
		this.parent_ID = parentTask.getParent_ID();
 
		
		this.parentTask = parentTask;
	}



	public Project getProject() {
		return project;
	}



	public void setProject(Project project) {	
		this.project_ID = project.getProject_ID();
		this.project = project;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
    
    
    
    
    

}
