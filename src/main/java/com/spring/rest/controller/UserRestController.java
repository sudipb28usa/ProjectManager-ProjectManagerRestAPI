package com.spring.rest.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.spring.service.TaskService;
import com.spring.service.UserService;

@RestController
@RequestMapping("/User")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TaskService taskService;

	private final String sharedKey = "SHARED_KEY";
	private static final String SUCCESS_STATUS = "success";
	private static final String USER_ADDED_STATUS = "New User Has Been Sucessfully Added";
	private static final String USER_UPDATED_STATUS = "User Has Been Sucessfully Updated";
	// private static final String WRONG_SUBJECT_STATUS = "Wrong subject provided";
	private static final String WRONG_USER_STATUS = "User ID Is Wrong";
	private static final String ERROR_STATUS = "error";
	private static final int CODE_SUCCESS = 100;
	private static final int AUTH_FAILURE = 102;
	// private static final int WRONG_SUBJECT = 103;
	private static final int WRONG_USER = 104;
	private static final int ERROR = -100;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public BaseResponse addUser(@RequestParam(value = "key") String key,
			@RequestBody com.spring.rest.model.User requentUser) {
		BaseResponse response = new BaseResponse();

		// If any error happen generic error will return
		response.setResponseStatus(ERROR_STATUS);
		response.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			Task task = taskService.findById(requentUser.getTask_ID());
			Project project = projectService.findById(requentUser.getProject_ID());

			User user = new User(requentUser.getFirst_Name(), requentUser.getLast_Name(), requentUser.getEmployee_ID(), project, task);

			userService.save(user);

			response.setResponseStatus(USER_ADDED_STATUS);
			response.setResponseCode(CODE_SUCCESS);

		} else {
			response.setResponseStatus(ERROR_STATUS);
			response.setResponseCode(AUTH_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public BaseResponse updateUser(@RequestParam(value = "key") String key,
			@RequestBody com.spring.rest.model.User requentUser) {
		BaseResponse response = new BaseResponse();

		// If any error happen generic error will return
		response.setResponseStatus(ERROR_STATUS);
		response.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			if (requentUser.getUser_ID() == null) {

				response.setResponseStatus(WRONG_USER_STATUS);
				response.setResponseCode(WRONG_USER);
				return response;

			}
			
			User user = userService.findById(requentUser.getUser_ID());

			if (user == null) {
				response.setResponseStatus(WRONG_USER_STATUS);
				response.setResponseCode(WRONG_USER);
				return response;
			}

			// If user wants to update user

			user.setFirst_Name(requentUser.getFirst_Name()); 
			user.setLast_Name(requentUser.getLast_Name()); 
			user.setEmployee_ID(requentUser.getEmployee_ID()); 

			userService.update(user);

			response.setResponseStatus(USER_UPDATED_STATUS);
			response.setResponseCode(CODE_SUCCESS);

		} else {
			response.setResponseStatus(ERROR_STATUS);
			response.setResponseCode(AUTH_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public BaseResponse deleteUser(@RequestParam(value = "key") String key,
			@RequestParam(value = "user_ID") String user_ID) {
		BaseResponse response = new BaseResponse();

		// If any error happen generic error will return
		response.setResponseStatus(ERROR_STATUS);
		response.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			if (user_ID == null && user_ID != "") {

				response.setResponseStatus(WRONG_USER_STATUS);
				response.setResponseCode(WRONG_USER);
				return response;

			}

			if (!isNumeric(user_ID)) {
				response.setResponseStatus(WRONG_USER_STATUS);
				response.setResponseCode(WRONG_USER);
				return response;
			}

			User user = userService.findById(Long.parseLong(user_ID));

			if (user == null) {
				response.setResponseStatus(WRONG_USER_STATUS);
				response.setResponseCode(WRONG_USER);
				return response;
			}

			userService.delete(user);

			response.setResponseStatus(SUCCESS_STATUS);
			response.setResponseCode(CODE_SUCCESS);

		} else {
			response.setResponseStatus(ERROR_STATUS);
			response.setResponseCode(AUTH_FAILURE);
		}

		return response;
	}

	@RequestMapping(value = "/getUserById", method = RequestMethod.GET)
	public com.spring.rest.model.User getUserById(@RequestParam(value = "key") String key,
			@RequestParam(value = "user_ID") String user_ID) {

		com.spring.rest.model.User responseUser = new com.spring.rest.model.User();

		// If any error happen generic error will return
		responseUser.setResponseStatus(ERROR_STATUS);
		responseUser.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			if (user_ID == null && user_ID != "") {

				responseUser.setResponseStatus(WRONG_USER_STATUS);
				responseUser.setResponseCode(WRONG_USER);
				return responseUser;

			}

			if (!isNumeric(user_ID)) {
				responseUser.setResponseStatus(WRONG_USER_STATUS);
				responseUser.setResponseCode(WRONG_USER);
				return responseUser;
			}

			User user = userService.findById(Long.parseLong(user_ID));

			if (user == null) {
				responseUser.setResponseStatus(WRONG_USER_STATUS);
				responseUser.setResponseCode(WRONG_USER);
				return responseUser;
			}

			responseUser = new com.spring.rest.model.User(user);

			responseUser.setResponseStatus(SUCCESS_STATUS);
			responseUser.setResponseCode(CODE_SUCCESS);

		} else {
			responseUser.setResponseStatus(ERROR_STATUS);
			responseUser.setResponseCode(AUTH_FAILURE);
		}

		return responseUser;
	}
	
	 
	@RequestMapping(value = "/getUserByFirstName", method = RequestMethod.GET)
	public List<com.spring.rest.model.User> getUserByFirstName(@RequestParam(value = "key") String key,
			@RequestParam(value = "first_Name") String first_Name) {

		com.spring.rest.model.User responseUser = new com.spring.rest.model.User();
		List<com.spring.rest.model.User> responseUserList = null; /*
																	 * =new ArrayList<com.spring.rest.model. User>();
																	 */

		// If any error happen generic error will return
		responseUser.setResponseStatus(ERROR_STATUS);
		responseUser.setResponseCode(ERROR);
		

		if (sharedKey.equalsIgnoreCase(key)) {
			
			if(first_Name==null || first_Name=="") {
				
				List<com.spring.rest.model.User> userList = getAllUser(key);
				
				return userList;
				
			}

			List<User> userList = userService.listByFirst_Name(first_Name);
			
			responseUserList = new ArrayList<com.spring.rest.model.User>();

			for (User user : userList) {
				responseUser = new com.spring.rest.model.User(user);

				responseUser.setResponseStatus(SUCCESS_STATUS);
				responseUser.setResponseCode(CODE_SUCCESS);
				responseUserList.add(responseUser);
			}

		} else {
			responseUser.setResponseStatus(ERROR_STATUS);
			responseUser.setResponseCode(AUTH_FAILURE);
		}

		if (responseUserList == null) {
			responseUserList = new ArrayList<com.spring.rest.model.User>();
			responseUserList.add(responseUser);
		}

		return responseUserList;
	}

	
	
	 

	
	
	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	public List<com.spring.rest.model.User> getAllUser(@RequestParam(value = "key") String key) {

		com.spring.rest.model.User responseUser = new com.spring.rest.model.User();
		List<com.spring.rest.model.User> responseUserList = null; /*
																	 * =new ArrayList<com.spring.rest.model. User>();
																	 */

		// If any error happen generic error will return
		responseUser.setResponseStatus(ERROR_STATUS);
		responseUser.setResponseCode(ERROR);

		if (sharedKey.equalsIgnoreCase(key)) {

			List<User> userList = userService.list();
			responseUserList = new ArrayList<com.spring.rest.model.User>();

			for (User user : userList) {
				responseUser = new com.spring.rest.model.User(user);

				responseUser.setResponseStatus(SUCCESS_STATUS);
				responseUser.setResponseCode(CODE_SUCCESS);
				responseUserList.add(responseUser);
			}

		} else {
			responseUser.setResponseStatus(ERROR_STATUS);
			responseUser.setResponseCode(AUTH_FAILURE);
		}

		if (responseUserList == null) {
			responseUserList = new ArrayList<com.spring.rest.model.User>();
			responseUserList.add(responseUser);
		}

		return responseUserList;
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
