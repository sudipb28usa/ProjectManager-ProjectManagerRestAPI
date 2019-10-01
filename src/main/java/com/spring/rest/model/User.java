package com.spring.rest.model;

public class User extends BaseResponse {

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(com.spring.model.User user /*,Boolean setDependent*/) {
		super();
		this.user_ID = user.getUser_ID();
		this.first_Name = user.getFirst_Name();
		this.last_Name = user.getLast_Name();
		this.employee_ID = user.getEmployee_ID();
		
		//if(setDependent==null || setDependent==true) {

		if (user.getProject() != null) {
			//this.project = new Project(user.getProject());
			this.project_ID = user.getProject().getProject_ID();
			this.project_Name = user.getProject().getProject_Name();
		} /*else {
			this.project = new Project();
			this.project.setProject_Name("No Project Assigned");
		}*/

		if (user.getTask() != null) {
			//this.task = new Task(user.getTask(), true);
			this.task_ID = user.getTask().getTask_ID();
			this.task_Name = user.getTask().getTask_Name();
			this.project_ID=user.getTask().getProject().getProject_ID();
			this.project_Name=user.getTask().getProject().getProject_Name();
		} /*else {
			this.task = new Task();
			this.task .setTask_Name("No Task Assigned");
		}*/
		
	//	}

	}

 

	private Long user_ID;

	private String first_Name;

	private String last_Name;

	private String employee_ID;

	private Long project_ID;

	private Long task_ID;
	
	
	private String project_Name="No Project Assigned";

	private String task_Name="No Task Assigned";

 

	 

	public Long getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(Long user_ID) {
		this.user_ID = user_ID;
	}

	public String getFirst_Name() {
		return first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getLast_Name() {
		return last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getEmployee_ID() {
		return employee_ID;
	}

	public void setEmployee_ID(String employee_ID) {
		this.employee_ID = employee_ID;
	}

	public Long getProject_ID() {
		return project_ID;
	}

	public void setProject_ID(Long project_ID) {
		this.project_ID = project_ID;
	}

	public Long getTask_ID() {
		return task_ID;
	}

	public void setTask_ID(Long task_ID) {
		this.task_ID = task_ID;
	}

	public String getProject_Name() {
		return project_Name;
	}

	public void setProject_Name(String project_Name) {
		this.project_Name = project_Name;
	}

	public String getTask_Name() {
		return task_Name;
	}

	public void setTask_Name(String task_Name) {
		this.task_Name = task_Name;
	}

 

}
