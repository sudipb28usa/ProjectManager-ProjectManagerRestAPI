package com.spring.model;

 
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
@Table(name = "FSE_Parent_Task")
public class ParentTask   {
	

	
	
    public ParentTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParentTask(String parent_Task, Set<Task> taskSet) {
		super();
		this.parent_Task = parent_Task;
		this.taskSet = taskSet;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public String toString() {
		return "ParentTask [parent_ID=" + parent_ID + ", parent_Task=" + parent_Task + ", taskSet=" + taskSet + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Parent_ID")
	private Long parent_ID;
	
    @Column(name = "Parent_Task") 
	private String parent_Task;
    
    @OneToMany(mappedBy="parentTask" ,  fetch = FetchType.EAGER , cascade = CascadeType.REMOVE) /*mappedBy="parentTask" ,  fetch = FetchType.EAGER*/
	private Set<Task> taskSet;
//, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true
	public Long getParent_ID() {
		return parent_ID;
	}

	public void setParent_ID(Long parent_ID) {
		this.parent_ID = parent_ID;
	}

	public String getParent_Task() {
		return parent_Task;
	}

	public void setParent_Task(String parent_Task) {
		this.parent_Task = parent_Task;
	}

	public Set<Task> getTaskSet() {
		return taskSet;
	}

	public void setTaskSet(Set<Task> taskSet) {
		this.taskSet = taskSet;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    

}
