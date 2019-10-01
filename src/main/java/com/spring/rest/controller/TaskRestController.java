package com.spring.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.ParentTask;
import com.spring.model.Project;
import com.spring.model.Task;
import com.spring.model.User;
import com.spring.rest.model.BaseResponse;
import com.spring.service.ParentTaskService;
import com.spring.service.ProjectService;
import com.spring.service.TaskService;
import com.spring.service.UserService;

@RestController
@RequestMapping("/Task")
public class TaskRestController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private ParentTaskService parentTaskService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProjectService projectService;

	private final String sharedKey = "SHARED_KEY";
	private static final String SUCCESS_STATUS = "success";
	private static final String TASK_ADDED_STATUS = "New Task Has Been Sucessfully Added";
	private static final String TASK_UPDATED_STATUS = "Task Has Been Sucessfully Updated";
	// private static final String WRONG_SUBJECT_STATUS = "Wrong subject provided";
	private static final String WRONG_TASK_STATUS = "Task ID Is Wrong";
	private static final String ERROR_STATUS = "error";
	private static final int CODE_SUCCESS = 100;
	private static final int AUTH_FAILURE = 102;
	// private static final int WRONG_SUBJECT = 103;
	private static final int WRONG_TASK = 104;
	private static final int ERROR = -100;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public BaseResponse addTask(@RequestParam(value = "key") String key,
			@RequestBody com.spring.rest.model.Task requentTask) {
		BaseResponse response = new BaseResponse();

		// If any error happen generic error will return
		response.setResponseStatus(ERROR_STATUS);
		response.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			if (requentTask.getUser_ID() == null) {
				return response;
			}

			User user = userService.findById(requentTask.getUser_ID());

			if (user == null) {
				return response;
			}

			if (requentTask.getProject_ID() == null) {
				return response;
			}

			Project project = projectService.findById(requentTask.getProject_ID());

			if (project == null) {
				return response;
			}

			// ---

			if (requentTask.getParent_ID() == null) {
				return response;
			}

			ParentTask parentTask = parentTaskService.findById(requentTask.getParent_ID());

			if (parentTask == null) {
				return response;
			}

			Task task = new Task(requentTask.getTask_Name(), requentTask.getStart_Date(), requentTask.getEnd_Date(),
					requentTask.getPriority(), requentTask.getStatus(), parentTask, project);

			// taskService.save(task);

			user.setTask(task);

			userService.update(user);

			response.setResponseStatus(TASK_ADDED_STATUS);
			response.setResponseCode(CODE_SUCCESS);

		} else {
			response.setResponseStatus(ERROR_STATUS);
			response.setResponseCode(AUTH_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public BaseResponse updateTask(@RequestParam(value = "key") String key,
			@RequestBody com.spring.rest.model.Task requentTask) {
		BaseResponse response = new BaseResponse();

		// If any error happen generic error will return
		response.setResponseStatus(ERROR_STATUS);
		response.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			if (requentTask.getTask_ID() == null) {

				response.setResponseStatus(WRONG_TASK_STATUS);
				response.setResponseCode(WRONG_TASK);
				return response;

			}

			User user = userService.findByTaskId(requentTask.getTask_ID());

			if (user != null) {
				user.setTask(null);

				userService.update(user);

				user = null;

			}

			if (requentTask.getUser_ID() == null) {
				return response;
			}

			user = userService.findById(requentTask.getUser_ID());

			if (user == null) {
				return response;
			}

			if (requentTask.getProject_ID() == null) {
				return response;
			}

			Project project = projectService.findById(requentTask.getProject_ID());

			if (project == null) {
				return response;
			}

			// ---

			if (requentTask.getParent_ID() == null) {
				return response;
			}

			ParentTask parentTask = parentTaskService.findById(requentTask.getParent_ID());

			if (parentTask == null) {
				return response;
			}

			Task task = taskService.findById(requentTask.getTask_ID());

			if (task == null) {
				response.setResponseStatus(WRONG_TASK_STATUS);
				response.setResponseCode(WRONG_TASK);
				return response;
			}

			// If user wants to update task

			task.setTask_Name(requentTask.getTask_Name());
			task.setStart_Date(requentTask.getStart_Date());
			task.setEnd_Date(requentTask.getEnd_Date());
			task.setPriority(requentTask.getPriority());
			task.setStatus(requentTask.getStatus());
			task.setParentTask(parentTask);
			task.setProject(project);

			// taskService.update(task);

			user.setTask(task);
			// user.setProject(project);

			userService.update(user);

			response.setResponseStatus(TASK_UPDATED_STATUS);
			response.setResponseCode(CODE_SUCCESS);

		} else {
			response.setResponseStatus(ERROR_STATUS);
			response.setResponseCode(AUTH_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public BaseResponse deleteTask(@RequestParam(value = "key") String key,
			@RequestParam(value = "task_ID") String task_ID) {
		BaseResponse response = new BaseResponse();

		// If any error happen generic error will return
		response.setResponseStatus(ERROR_STATUS);
		response.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			if (task_ID == null && task_ID != "") {

				response.setResponseStatus(WRONG_TASK_STATUS);
				response.setResponseCode(WRONG_TASK);
				return response;

			}

			if (!isNumeric(task_ID)) {
				response.setResponseStatus(WRONG_TASK_STATUS);
				response.setResponseCode(WRONG_TASK);
				return response;
			}

			Task task = taskService.findById(Long.parseLong(task_ID));

			if (task == null) {
				response.setResponseStatus(WRONG_TASK_STATUS);
				response.setResponseCode(WRONG_TASK);
				return response;
			}

			taskService.delete(task);

			response.setResponseStatus(SUCCESS_STATUS);
			response.setResponseCode(CODE_SUCCESS);

		} else {
			response.setResponseStatus(ERROR_STATUS);
			response.setResponseCode(AUTH_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/getTaskById", method = RequestMethod.GET)
	public com.spring.rest.model.Task getTaskById(@RequestParam(value = "key") String key,
			@RequestParam(value = "task_ID") String task_ID) {

		com.spring.rest.model.Task responseTask = new com.spring.rest.model.Task();

		// If any error happen generic error will return
		responseTask.setResponseStatus(ERROR_STATUS);
		responseTask.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			if (task_ID == null && task_ID != "") {

				responseTask.setResponseStatus(WRONG_TASK_STATUS);
				responseTask.setResponseCode(WRONG_TASK);
				return responseTask;

			}

			if (!isNumeric(task_ID)) {
				responseTask.setResponseStatus(WRONG_TASK_STATUS);
				responseTask.setResponseCode(WRONG_TASK);
				return responseTask;
			}

			Task task = taskService.findById(Long.parseLong(task_ID));

			if (task == null) {
				responseTask.setResponseStatus(WRONG_TASK_STATUS);
				responseTask.setResponseCode(WRONG_TASK);
				return responseTask;
			}

			responseTask = new com.spring.rest.model.Task(task);

			responseTask.setResponseStatus(SUCCESS_STATUS);
			responseTask.setResponseCode(CODE_SUCCESS);

		} else {
			responseTask.setResponseStatus(ERROR_STATUS);
			responseTask.setResponseCode(AUTH_FAILURE);
		}

		return responseTask;
	}

	@RequestMapping(value = "/getAllTask", method = RequestMethod.GET)
	public List<com.spring.rest.model.Task> getAllTask(@RequestParam(value = "key") String key) {

		com.spring.rest.model.Task responseTask = new com.spring.rest.model.Task();
		List<com.spring.rest.model.Task> responseTaskList = null; /*
																	 * =new ArrayList<com.spring.rest.model. Task>();
																	 */

		// If any error happen generic error will return
		responseTask.setResponseStatus(ERROR_STATUS);
		responseTask.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			List<Task> taskList = taskService.list();
			responseTaskList = new ArrayList<com.spring.rest.model.Task>();

			for (Task task : taskList) {
				responseTask = new com.spring.rest.model.Task(task);

				responseTask.setResponseStatus(SUCCESS_STATUS);
				responseTask.setResponseCode(CODE_SUCCESS);
				responseTaskList.add(responseTask);
			}

		} else {
			responseTask.setResponseStatus(ERROR_STATUS);
			responseTask.setResponseCode(AUTH_FAILURE);
		}

		if (responseTaskList == null) {
			responseTaskList = new ArrayList<com.spring.rest.model.Task>();
			responseTaskList.add(responseTask);
		}

		return responseTaskList;
	}

	@RequestMapping(value = "/getAllTaskByProject", method = RequestMethod.GET)
	public List<com.spring.rest.model.Task> getAllTaskByProject(@RequestParam(value = "key") String key,
			@RequestParam(value = "project_Name") String project_Name) {

		com.spring.rest.model.Task responseTask = new com.spring.rest.model.Task();
		List<com.spring.rest.model.Task> responseTaskList = null; /*
																	 * =new ArrayList<com.spring.rest.model. Task>();
																	 */

		// If any error happen generic error will return
		responseTask.setResponseStatus(ERROR_STATUS);
		responseTask.setResponseCode(ERROR);

		List<Task> taskList = new ArrayList<Task>();

		if (sharedKey.equalsIgnoreCase(key)) {
			
			
			
			if (project_Name != null && project_Name.trim().length() > 0) {
				taskList = taskService.listByProject(project_Name);
			} else {

				taskList = taskService.list();

			}

			responseTaskList = new ArrayList<com.spring.rest.model.Task>();

			for (Task task : taskList) {
				responseTask = new com.spring.rest.model.Task(task);

				responseTask.setResponseStatus(SUCCESS_STATUS);
				responseTask.setResponseCode(CODE_SUCCESS);
				responseTaskList.add(responseTask);
			}

		} else {
			responseTask.setResponseStatus(ERROR_STATUS);
			responseTask.setResponseCode(AUTH_FAILURE);
		}

		if (responseTaskList == null) {
			responseTaskList = new ArrayList<com.spring.rest.model.Task>();
			responseTaskList.add(responseTask);
		}

		return responseTaskList;
	}
	
	@RequestMapping(value = "/markComplete", method = RequestMethod.PUT)
	public BaseResponse markComplete(@RequestParam(value = "key") String key,
			@RequestParam(value = "task_ID") String task_ID) {
		BaseResponse response = new BaseResponse();

		// If any error happen generic error will return
		response.setResponseStatus(ERROR_STATUS);
		response.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			if (task_ID == null) {

				response.setResponseStatus(WRONG_TASK_STATUS);
				response.setResponseCode(WRONG_TASK);
				return response;

			}
			
 
			if (!isNumeric(task_ID)) {
				response.setResponseStatus(WRONG_TASK_STATUS);
				response.setResponseCode(WRONG_TASK);
				return response;
			}

			Task task = taskService.findById(Long.parseLong(task_ID));

			if (task == null) {
				response.setResponseStatus(WRONG_TASK_STATUS);
				response.setResponseCode(WRONG_TASK);
				return response;
			}

			 
			task.setStatus("Completed");


			taskService.update(task);

		

			response.setResponseStatus(TASK_UPDATED_STATUS);
			response.setResponseCode(CODE_SUCCESS);

		} else {
			response.setResponseStatus(ERROR_STATUS);
			response.setResponseCode(AUTH_FAILURE);
		}

		return response;
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
