package com.spring.rest.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.ParentTask;
import com.spring.model.Task;
import com.spring.rest.model.BaseResponse;
import com.spring.service.ParentTaskService;

@RestController
@RequestMapping("/ParentTasks")
public class ParentTaskRestController {

	@Autowired
	private ParentTaskService parentTaskService;

	private final String sharedKey = "SHARED_KEY";
	private static final String SUCCESS_STATUS = "success";
	private static final String PARENT_TASK_ADDED_STATUS = "New ParentTask Has Been Sucessfully Added";
	private static final String PARENT_TASK_UPDATED_STATUS = "ParentTask Has Been Sucessfully Updated";
	//private static final String WRONG_SUBJECT_STATUS = "Wrong subject provided";
	private static final String WRONG_PARENT_TASK_STATUS = "ParentTask ID Is Wrong";
	private static final String ERROR_STATUS = "error";
	private static final int CODE_SUCCESS = 100;
	private static final int AUTH_FAILURE = 102;
	//private static final int WRONG_SUBJECT = 103;
	private static final int WRONG_PARENT_TASK = 104;
	private static final int ERROR = -100;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public BaseResponse addParentTask(@RequestParam(value = "key") String key,
			@RequestBody com.spring.rest.model.ParentTask requentParentTask) {
		BaseResponse response = new BaseResponse();

		// If any error happen generic error will return
		response.setResponseStatus(ERROR_STATUS);
		response.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

 

			ParentTask parentTask = new ParentTask(requentParentTask.getParent_Task(), new HashSet<Task>());
 
			parentTaskService.save(parentTask);

			response.setResponseStatus(PARENT_TASK_ADDED_STATUS);
			response.setResponseCode(CODE_SUCCESS);

		} else {
			response.setResponseStatus(ERROR_STATUS);
			response.setResponseCode(AUTH_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public BaseResponse updateParentTask(@RequestParam(value = "key") String key,
			@RequestBody com.spring.rest.model.ParentTask requentParentTask) {
		BaseResponse response = new BaseResponse();

		// If any error happen generic error will return
		response.setResponseStatus(ERROR_STATUS);
		response.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			if (requentParentTask.getParent_ID() == null) {

				response.setResponseStatus(WRONG_PARENT_TASK_STATUS);
				response.setResponseCode(WRONG_PARENT_TASK);
				return response;

			}

			ParentTask parentTask = parentTaskService.findById(requentParentTask.getParent_ID());

			if (parentTask == null) {
				response.setResponseStatus(WRONG_PARENT_TASK_STATUS);
				response.setResponseCode(WRONG_PARENT_TASK);
				return response;
			}

			// If user wants to update parentTask

			parentTask.setParent_Task(requentParentTask.getParent_Task());

			parentTaskService.update(parentTask);

			response.setResponseStatus(PARENT_TASK_UPDATED_STATUS);
			response.setResponseCode(CODE_SUCCESS);

		} else {
			response.setResponseStatus(ERROR_STATUS);
			response.setResponseCode(AUTH_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public BaseResponse deleteParentTask(@RequestParam(value = "key") String key,
			@RequestParam(value = "parent_ID") String parent_ID) {
		BaseResponse response = new BaseResponse();

		// If any error happen generic error will return
		response.setResponseStatus(ERROR_STATUS);
		response.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			if (parent_ID == null && parent_ID != "") {

				response.setResponseStatus(WRONG_PARENT_TASK_STATUS);
				response.setResponseCode(WRONG_PARENT_TASK);
				return response;

			}

			if (!isNumeric(parent_ID)) {
				response.setResponseStatus(WRONG_PARENT_TASK_STATUS);
				response.setResponseCode(WRONG_PARENT_TASK);
				return response;
			}

			ParentTask parentTask = parentTaskService.findById(Long.parseLong(parent_ID));

			if (parentTask == null) {
				response.setResponseStatus(WRONG_PARENT_TASK_STATUS);
				response.setResponseCode(WRONG_PARENT_TASK);
				return response;
			}

			parentTaskService.delete(parentTask);

			response.setResponseStatus(SUCCESS_STATUS);
			response.setResponseCode(CODE_SUCCESS);

		} else {
			response.setResponseStatus(ERROR_STATUS);
			response.setResponseCode(AUTH_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/getParentTaskById", method = RequestMethod.GET)
	public com.spring.rest.model.ParentTask getParentTaskById(@RequestParam(value = "key") String key,
			@RequestParam(value = "parent_ID") String parent_ID) {

		com.spring.rest.model.ParentTask responseParentTask = new com.spring.rest.model.ParentTask();

		// If any error happen generic error will return
		responseParentTask.setResponseStatus(ERROR_STATUS);
		responseParentTask.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			if (parent_ID == null && parent_ID != "") {

				responseParentTask.setResponseStatus(WRONG_PARENT_TASK_STATUS);
				responseParentTask.setResponseCode(WRONG_PARENT_TASK);
				return responseParentTask;

			}

			if (!isNumeric(parent_ID)) {
				responseParentTask.setResponseStatus(WRONG_PARENT_TASK_STATUS);
				responseParentTask.setResponseCode(WRONG_PARENT_TASK);
				return responseParentTask;
			}

			ParentTask parentTask = parentTaskService.findById(Long.parseLong(parent_ID));

			if (parentTask == null) {
				responseParentTask.setResponseStatus(WRONG_PARENT_TASK_STATUS);
				responseParentTask.setResponseCode(WRONG_PARENT_TASK);
				return responseParentTask;
			}

			responseParentTask = new com.spring.rest.model.ParentTask(parentTask);

			responseParentTask.setResponseStatus(SUCCESS_STATUS);
			responseParentTask.setResponseCode(CODE_SUCCESS);

		} else {
			responseParentTask.setResponseStatus(ERROR_STATUS);
			responseParentTask.setResponseCode(AUTH_FAILURE);
		}

		return responseParentTask;
	}

	@RequestMapping(value = "/getAllParentTasks", method = RequestMethod.GET)
	public List<com.spring.rest.model.ParentTask> getAllParentTasks(@RequestParam(value = "key") String key) {

		com.spring.rest.model.ParentTask responseParentTask = new com.spring.rest.model.ParentTask();
		List<com.spring.rest.model.ParentTask> responseParentTaskList = null; /*
																				 * =new ArrayList<com.spring.rest.model.
																				 * ParentTask>();
																				 */

		// If any error happen generic error will return
		responseParentTask.setResponseStatus(ERROR_STATUS);
		responseParentTask.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			List<ParentTask> parentTaskList = parentTaskService.list();
			responseParentTaskList = new ArrayList<com.spring.rest.model.ParentTask>();

			for (ParentTask parentTask : parentTaskList) {
				responseParentTask = new com.spring.rest.model.ParentTask(parentTask);

				responseParentTask.setResponseStatus(SUCCESS_STATUS);
				responseParentTask.setResponseCode(CODE_SUCCESS);
				responseParentTaskList.add(responseParentTask);
			}

		} else {
			responseParentTask.setResponseStatus(ERROR_STATUS);
			responseParentTask.setResponseCode(AUTH_FAILURE);
		}

		if (responseParentTaskList == null) {
			responseParentTaskList = new ArrayList<com.spring.rest.model.ParentTask>();
			responseParentTaskList.add(responseParentTask);
		}

		return responseParentTaskList;
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
