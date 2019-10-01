package com.spring.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.ParentTask;
import com.spring.service.ParentTaskService;

@Controller
public class ParentTaskController {
	
	 @Autowired
	 private ParentTaskService parentTaskService;
	 
	 
     
    @ModelAttribute("parentTask")
    public ParentTask formBackingObject() {
        return new ParentTask();
    }
	
	 
	    @GetMapping("/GetParentTask")
	    public String userForm(Locale locale, Model model) {
 
	    	model.addAttribute("parentTaskList", parentTaskService.list());	    	
	    	
	    	 model.addAttribute("Header", "Parent Task");
	    	 model.addAttribute("message", "Parent Task List");
	        return "ParentTask";
	    }   
	    
	    
		@RequestMapping(value="/addParentTaskPage", method = RequestMethod.GET)
		public String  addParentTaskPage(ModelMap model) {
			
			model.addAttribute("Title", "Add ParentTask");	
			model.addAttribute("Header", "Add ParentTask");

			

			return "AddUpdateParentTask"; 
		}
		
		
	    
	    
	    @PostMapping("/saveParentTask")
	    public String saveParentTask(@ModelAttribute("parentTask") @Valid ParentTask parentTask,
	                            BindingResult result, Model model) { 
	    
	    	if(parentTask.getParent_ID()!=null) {
	    		parentTaskService.update(parentTask);
	    	}else {
	    		parentTaskService.save(parentTask);
	    	}
 	    	
	    	 	              
	    	model.addAttribute("Title", "Add Parent Task");	
			model.addAttribute("Header", "Add Parent Task");
			model.addAttribute("message", "Parent task has been successfully added");
	    	

	        return "redirect:/GetParentTask";
	    }
	    
	    
		@RequestMapping(value="/updateParentTask/{parent_ID}", method = RequestMethod.GET)
		public ModelAndView updateParentTask(@PathVariable("parent_ID")Long parent_ID,ModelMap model) {
			 
			ParentTask parentTask=parentTaskService.findById(parent_ID);
			model.addAttribute("parentTask",parentTask);	
			model.addAttribute("Title", "Update Parent Task");	
			model.addAttribute("Header", "Update Parent Task");
		
			return   new ModelAndView("AddUpdateParentTask", "parentTask", parentTask); 
		}
		
		
		@RequestMapping(value="/deleteParentTask/{parent_ID}", method = RequestMethod.GET)
		public String searchSubject(@PathVariable("parent_ID")Long parent_ID,ModelMap model) {
			 
			ParentTask parentTask=parentTaskService.findById(parent_ID);
			parentTaskService.delete(parentTask);

 		 
	    	model.addAttribute("Title", "Parent Task");	
			model.addAttribute("Header", "Parent Task List");
			
			model.addAttribute("message", "Parent Task  has been deleted successfully");
			
			
		
			return "redirect:/GetParentTask";
		}
	     
	   
	    
		

}
