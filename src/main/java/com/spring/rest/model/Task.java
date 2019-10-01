package com.spring.rest.model;

 
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.Nullable;


 
public class Task  extends BaseResponse {
	
 


    public Task() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Task(com.spring.model.Task task/* ,Boolean setDependent*/) {
		super();
		this.task_ID = task.getTask_ID();	
		this.task_Name = task.getTask_Name();
		this.start_Date = task.getStart_Date();
		this.end_Date = task.getEnd_Date();
		this.priority = task.getPriority();
		this.status = task.getStatus();
 
		//if(setDependent==null || setDependent==true) {
			
			if (task.getParentTask() != null) {
				//this.parentTask = new ParentTask(task.getParentTask());
				this.parent_ID = task.getParentTask().getParent_ID();
				this.parent_TaskName=task.getParentTask().getParent_Task();
			} else {
				//this.parentTask = new ParentTask();
				//this.parentTask.setParent_Task("No Parent Assigned");
	 
			}
			
			
			if (task.getProject() != null) {
				//this.project = new Project(task.getProject());
				this.project_ID = task.getProject() .getProject_ID();
				this.project_Name = task.getProject().getProject_Name();
			//	this.project.setProject_Name("No Project Assigned");
			} else {
				//this.project = new Project();
			}
			
		//}
		
			/*if(task.getUser()!=null ) {			
			this.user= new User(task.getUser(), false) ;
		}
		*/
			
			if(task.getUser()!=null ) {			
				this.user_ID=task.getUser().getUser_ID();
				this.userName=task.getUser().getFirst_Name()+" "+task.getUser().getLast_Name();
			}	
 
	}


 

 
 



	@Override
	public String toString() {
		return "Task [task_ID=" + task_ID + ", parent_ID=" + parent_ID + ", project_ID=" + project_ID + ", user_ID="
				+ user_ID + ", userName=" + userName + ", task_Name=" + task_Name + ", project_Name=" + project_Name
				+ ", parent_TaskName=" + parent_TaskName + ", start_Date=" + start_Date + ", end_Date=" + end_Date
				+ ", priority=" + priority + ", status=" + status + "]";
	}









	private Long task_ID;
	
 
	private Long parent_ID=new Long(0);;
    
 
	private Long project_ID=new Long(0);;
	
	private Long user_ID=new Long(0);
	
	private String userName="No User Assigned";
    
 
	private String task_Name;
	
	private String project_Name="No Project Assigned";
	
	private String parent_TaskName="No Parent Assigned";
    
    
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date start_Date;
    
    
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date end_Date;
    
    
 
	private int priority;
    
 
	private String status;
    
 



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



	public Long getUser_ID() {
		return user_ID;
	}



	public void setUser_ID(Long user_ID) {
		this.user_ID = user_ID;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getProject_Name() {
		return project_Name;
	}



	public void setProject_Name(String project_Name) {
		this.project_Name = project_Name;
	}



	public String getParent_TaskName() {
		return parent_TaskName;
	}



	public void setParent_TaskName(String parent_TaskName) {
		this.parent_TaskName = parent_TaskName;
	}

 

    

}
