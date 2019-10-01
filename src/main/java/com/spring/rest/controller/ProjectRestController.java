package com.spring.rest.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.Project;
import com.spring.model.Task;
import com.spring.model.User;
import com.spring.rest.model.BaseResponse;
import com.spring.service.ProjectService;
import com.spring.service.UserService;

@RestController
@RequestMapping("/Project")
public class ProjectRestController {

	@Autowired
	private ProjectService projectService;
	

	@Autowired
	private UserService userService;

	private final String sharedKey = "SHARED_KEY";
	private static final String SUCCESS_STATUS = "success";
	private static final String PROJECT_ADDED_STATUS = "New Project Has Been Sucessfully Added";
	private static final String PROJECT_UPDATED_STATUS = "Project Has Been Sucessfully Updated";
	// private static final String WRONG_SUBJECT_STATUS = "Wrong subject provided";
	private static final String WRONG_PROJECT_STATUS = "Project ID Is Wrong";
	private static final String ERROR_STATUS = "error";
	private static final int CODE_SUCCESS = 100;
	private static final int AUTH_FAILURE = 102;
	// private static final int WRONG_SUBJECT = 103;
	private static final int WRONG_PROJECT = 104;
	private static final int ERROR = -100;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public BaseResponse addProject(@RequestParam(value = "key") String key,
			@RequestBody com.spring.rest.model.Project requentProject) {
		BaseResponse response = new BaseResponse();

		// If any error happen generic error will return
		response.setResponseStatus(ERROR_STATUS);
		response.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {
			
			
			if(requentProject.getUserManager_ID()==null) {
				return response;
			}
			
 
			
			User user=userService.findById(requentProject.getUserManager_ID());
			
			if(user==null) {
				return response;
			}
 
			

			Project project = new Project(requentProject.getProject_Name(), requentProject.getStart_Date(),
					requentProject.getEnd_Date(), requentProject.getPriority(), new HashSet<Task>(), new HashSet<User>());
			
			
			

			projectService.save(project);
			
			if(project.getProject_ID()!=null) {
				user.setProject(project);
				userService.update(user);
			}

			response.setResponseStatus(PROJECT_ADDED_STATUS);
			response.setResponseCode(CODE_SUCCESS);

		} else {
			response.setResponseStatus(ERROR_STATUS);
			response.setResponseCode(AUTH_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public BaseResponse updateProject(@RequestParam(value = "key") String key,
			@RequestBody com.spring.rest.model.Project requentProject) {
		BaseResponse response = new BaseResponse();

		// If any error happen generic error will return
		response.setResponseStatus(ERROR_STATUS);
		response.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			if (requentProject.getProject_ID() == null) {

				response.setResponseStatus(WRONG_PROJECT_STATUS);
				response.setResponseCode(WRONG_PROJECT);
				return response;

			}

			Project project = projectService.findById(requentProject.getProject_ID());

			if (project == null) {
				response.setResponseStatus(WRONG_PROJECT_STATUS);
				response.setResponseCode(WRONG_PROJECT);
				return response;
			}
			
 
			
			if(requentProject.getUserManager_ID()==null) {
				return response;
			}
			
			User user=userService.findById(requentProject.getUserManager_ID());
			
			if(user==null) {
				return response;
			}
			
			
			

			// If user wants to update project

			project.setProject_Name(requentProject.getProject_Name());
			project.setStart_Date(requentProject.getStart_Date());			
			project.setEnd_Date(requentProject.getEnd_Date());
			project.setPriority(requentProject.getPriority());

			projectService.update(project);
			
			
			
			user.setProject(project);
			userService.update(user);
			
			
			

			response.setResponseStatus(PROJECT_UPDATED_STATUS);
			response.setResponseCode(CODE_SUCCESS);

		} else {
			response.setResponseStatus(ERROR_STATUS);
			response.setResponseCode(AUTH_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public BaseResponse deleteProject(@RequestParam(value = "key") String key,
			@RequestParam(value = "project_ID") String project_ID) {
		BaseResponse response = new BaseResponse();

		// If any error happen generic error will return
		response.setResponseStatus(ERROR_STATUS);
		response.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			if (project_ID == null && project_ID != "") {

				response.setResponseStatus(WRONG_PROJECT_STATUS);
				response.setResponseCode(WRONG_PROJECT);
				return response;

			}

			if (!isNumeric(project_ID)) {
				response.setResponseStatus(WRONG_PROJECT_STATUS);
				response.setResponseCode(WRONG_PROJECT);
				return response;
			}

			Project project = projectService.findById(Long.parseLong(project_ID));

			if (project == null) {
				response.setResponseStatus(WRONG_PROJECT_STATUS);
				response.setResponseCode(WRONG_PROJECT);
				return response;
			}

			projectService.delete(project);

			response.setResponseStatus(SUCCESS_STATUS);
			response.setResponseCode(CODE_SUCCESS);

		} else {
			response.setResponseStatus(ERROR_STATUS);
			response.setResponseCode(AUTH_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/getProjectById", method = RequestMethod.GET)
	public com.spring.rest.model.Project getProjectById(@RequestParam(value = "key") String key,
			@RequestParam(value = "project_ID") String project_ID) {

		com.spring.rest.model.Project responseProject = new com.spring.rest.model.Project();

		// If any error happen generic error will return
		responseProject.setResponseStatus(ERROR_STATUS);
		responseProject.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			if (project_ID == null && project_ID != "") {

				responseProject.setResponseStatus(WRONG_PROJECT_STATUS);
				responseProject.setResponseCode(WRONG_PROJECT);
				return responseProject;

			}

			if (!isNumeric(project_ID)) {
				responseProject.setResponseStatus(WRONG_PROJECT_STATUS);
				responseProject.setResponseCode(WRONG_PROJECT);
				return responseProject;
			}

			Project project = projectService.findById(Long.parseLong(project_ID));

			if (project == null) {
				responseProject.setResponseStatus(WRONG_PROJECT_STATUS);
				responseProject.setResponseCode(WRONG_PROJECT);
				return responseProject;
			}

			responseProject = new com.spring.rest.model.Project(project);

			responseProject.setResponseStatus(SUCCESS_STATUS);
			responseProject.setResponseCode(CODE_SUCCESS);

		} else {
			responseProject.setResponseStatus(ERROR_STATUS);
			responseProject.setResponseCode(AUTH_FAILURE);
		}

		return responseProject;
	}

	@RequestMapping(value = "/getAllProject", method = RequestMethod.GET)
	public List<com.spring.rest.model.Project> getAllProject(@RequestParam(value = "key") String key) {

		com.spring.rest.model.Project responseProject = new com.spring.rest.model.Project();
		List<com.spring.rest.model.Project> responseProjectList = null; /*
																		 * =new ArrayList<com.spring.rest.model.
																		 * Project>();
																		 */

		// If any error happen generic error will return
		responseProject.setResponseStatus(ERROR_STATUS);
		responseProject.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			List<Project> projectList = projectService.list();
			responseProjectList = new ArrayList<com.spring.rest.model.Project>();

			for (Project project : projectList) {
				responseProject = new com.spring.rest.model.Project(project);

				responseProject.setResponseStatus(SUCCESS_STATUS);
				responseProject.setResponseCode(CODE_SUCCESS);
				responseProjectList.add(responseProject);
			}

		} else {
			responseProject.setResponseStatus(ERROR_STATUS);
			responseProject.setResponseCode(AUTH_FAILURE);
		}

		if (responseProjectList == null) {
			responseProjectList = new ArrayList<com.spring.rest.model.Project>();
			responseProjectList.add(responseProject);
		}

		return responseProjectList;
	}
	
	
	@RequestMapping(value = "/getAllProjectByName", method = RequestMethod.GET)
	public List<com.spring.rest.model.Project> getAllProjectByName(@RequestParam(value = "key") String key,
												@RequestParam(value = "project_Name") String project_Name) {

		com.spring.rest.model.Project responseProject = new com.spring.rest.model.Project();
		List<com.spring.rest.model.Project> responseProjectList = null; /*
																		 * =new ArrayList<com.spring.rest.model.
																		 * Project>();
																		 */

		// If any error happen generic error will return
		responseProject.setResponseStatus(ERROR_STATUS);
		responseProject.setResponseCode(ERROR);
		
		List<Project> projectList = new ArrayList<Project>();
		

		if (sharedKey.equalsIgnoreCase(key)) {
			
			if (project_Name != null && project_Name.trim().length() > 0) {
				projectList = projectService.listByProject(project_Name);
			} else {

				projectList = projectService.list();

			}
			
			
			responseProjectList = new ArrayList<com.spring.rest.model.Project>();

			for (Project project : projectList) {
				responseProject = new com.spring.rest.model.Project(project);

				responseProject.setResponseStatus(SUCCESS_STATUS);
				responseProject.setResponseCode(CODE_SUCCESS);
				responseProjectList.add(responseProject);
			}

		} else {
			responseProject.setResponseStatus(ERROR_STATUS);
			responseProject.setResponseCode(AUTH_FAILURE);
		}

		if (responseProjectList == null) {
			responseProjectList = new ArrayList<com.spring.rest.model.Project>();
			responseProjectList.add(responseProject);
		}

		return responseProjectList;
	}

	public static boolean isNumeric(final String str) {

		// null or empty
		if (str == null || str.length() == 0) {
			return false;
		}

		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}

		return true;

	}

}
