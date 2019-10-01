package com.spring.model;

 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "FSE_User")
public class User   {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


 
    
    


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public User(String first_Name, String last_Name, String employee_ID,   Project project, Task task) {
		super();
 
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.employee_ID = employee_ID;
 
		
		this.project = project;
		this.task = task;
		
		if(task!=null) {
			this.task_ID =task.getTask_ID();
		}
		
		if(project!=null) {
			this.project_ID =project.getProject_ID();
		}
	}



	@Override
	public String toString() {
		return "User [user_ID=" + user_ID + ", first_Name=" + first_Name + ", last_Name=" + last_Name + ", employee_ID="
				+ employee_ID + ", project_ID=" + project_ID + ", task_ID=" + task_ID + ", project=" + project
				+ ", task=" + task + "]";
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")
	private Long user_ID;
	
    @Column(name = "First_Name") 
	private String first_Name;
    
    
    @Column(name = "Last_Name") 
	private String last_Name;
    
    
    @Column(name = "Employee_ID") 
	private String employee_ID;
    

    transient private Long project_ID;
    

	transient private Long task_ID;
    
    
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Project_ID")
    private Project project;
    
    
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Task_ID")
    private Task task;



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



	public Project getProject() {
		return project;
	}



	public void setProject(Project project) {
		this.project = project;
	}



	public Task getTask() {
		return task;
	}



	public void setTask(Task task) {
		this.task = task;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
    
    

}
