package com.spring.rest.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Project extends BaseResponse {

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(com.spring.model.Project project) {
		super();
		this.project_ID = project.getProject_ID();
		this.project_Name = project.getProject_Name();
		this.start_Date = project.getStart_Date();
		this.end_Date = project.getEnd_Date();
		this.priority = project.getPriority();
		
		if(project.getUserSet()!=null && project.getUserSet().size() >  0) {			
			this.userManager_ID=project.getUserSet().stream().findFirst().get().getUser_ID();
			this.userManager_Name=project.getUserSet().stream().findFirst().get().getFirst_Name()+" "+project.getUserSet().stream().findFirst().get().getLast_Name();
		} 
		
		
		 if(project.getTaskSet()!=null ) {
			 
			 this.noOfTask=(long) project.getTaskSet().size() ;
			 
			com.spring.model.Task task= project.getTaskSet().stream().filter(x -> !"Completed".equalsIgnoreCase(x.getStatus())).findFirst().orElse(null) ;
			if(task==null && noOfTask>0) {
				this.status="Completed";
			}
			 
		 }
		 
		 

	}

 


	@Override
	public String toString() {
		return "Project [project_ID=" + project_ID + ", project_Name=" + project_Name + ", start_Date=" + start_Date
				+ ", end_Date=" + end_Date + ", priority=" + priority + ", userManager_ID=" + userManager_ID
				+ ", userManager_Name=" + userManager_Name + "]";
	}




	private Long project_ID;

	private String project_Name;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date start_Date;

 
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date end_Date;

	private int priority;
	private String status="Work in progress";

	
	private Long userManager_ID;
	private String userManager_Name="No Manager Assigned";
	
	
	private Long noOfTask=new Long(0);


 
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

	public Long getUserManager_ID() {
		return userManager_ID;
	}

	public void setUserManager_ID(Long userManager_ID) {
		this.userManager_ID = userManager_ID;
	}

	public String getUserManager_Name() {
		return userManager_Name;
	}

	public void setUserManager_Name(String userManager_Name) {
		this.userManager_Name = userManager_Name;
	}

	public Long getNoOfTask() {
		return noOfTask;
	}

	public void setNoOfTask(Long noOfTask) {
		this.noOfTask = noOfTask;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

 

 

 

}
