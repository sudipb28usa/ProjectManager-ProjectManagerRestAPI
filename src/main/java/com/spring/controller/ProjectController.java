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

import com.spring.model.Project;
import com.spring.service.ProjectService;

@Controller
public class ProjectController {
	
	 @Autowired
	 private ProjectService projectService;
	 
	 
     
    @ModelAttribute("project")
    public Project formBackingObject() {
        return new Project();
    }
	
	 
	    @GetMapping("/GetProject")
	    public String getProject(Locale locale, Model model) {
 
	    	model.addAttribute("projectList", projectService.list());	    	
	    	
	    	 model.addAttribute("Header", "Project");
	    	 model.addAttribute("message", "Project List");
	        return "Project";
	    }   
	    
	    
		@RequestMapping(value="/addProjectPage", method = RequestMethod.GET)
		public String  addProjectPage(ModelMap model) {
			
			model.addAttribute("Title", "Add Project");	
			model.addAttribute("Header", "Add Project");

			

			return "AddUpdateProject"; 
		}
		
		
	    
	    
	    @PostMapping("/saveProject")
	    public String saveProject(@ModelAttribute("project") @Valid Project project,
	                            BindingResult result, Model model) { 
	    
	    	if(project.getProject_ID()!=null) {
	    		projectService.update(project);
	    	}else {
	    		projectService.save(project);
	    	}
 	    	
	    	 	              
	    	model.addAttribute("Title", "Add Project");	
			model.addAttribute("Header", "Add Project");
			model.addAttribute("message", "Project has been successfully added");
	    	

	        return "redirect:/GetProject";
	    }
	    
	    
		@RequestMapping(value="/updateProject/{project_ID}", method = RequestMethod.GET)
		public ModelAndView updateProject(@PathVariable("project_ID")Long project_ID,ModelMap model) {
			 
			Project project=projectService.findById(project_ID);
			model.addAttribute("project",project);	
			model.addAttribute("Title", "Update Project");	
			model.addAttribute("Header", "Update Project");
		
			return   new ModelAndView("AddUpdateProject", "project", project); 
		}
		
		
		@RequestMapping(value="/deleteProject/{project_ID}", method = RequestMethod.GET)
		public String searchSubject(@PathVariable("project_ID")Long project_ID,ModelMap model) {
			 
			Project project=projectService.findById(project_ID);
			projectService.delete(project);

 		 
	    	model.addAttribute("Title", "Project");	
			model.addAttribute("Header", "Project List");
			
			model.addAttribute("message", "Project  has been deleted successfully");
			
			
		
			return "redirect:/GetProject";
		}
	     
	   
	    
		

}
