package com.spring.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	 
	    @GetMapping("/")
	    public String userForm(Locale locale, Model model) {
	      //  model.addAttribute("subjects", subjectService.list());
	    	
	    	 model.addAttribute("Header", "Please click on below menu");
	        return "Home";
	    }    	    
	     
	   
	    
		

}
