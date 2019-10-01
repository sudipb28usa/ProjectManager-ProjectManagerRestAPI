package com.spring.rest.model;

public class ParentTask  extends BaseResponse {
 
	
	

    public ParentTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParentTask(com.spring.model.ParentTask parentTask) {
		this.parent_ID = parentTask.getParent_ID();
		this.parent_Task =  parentTask.getParent_Task();
 

	}

 


	@Override
	public String toString() {
		return "ParentTask [parent_ID=" + parent_ID + ", parent_Task=" + parent_Task + "]";
	}




	private Long parent_ID;
	

	private String parent_Task;
 
	
 
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

 
 
    
    

}
