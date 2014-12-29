package com.buyer.springmvc.controller;

import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javassist.NotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.buyer.springmvc.model.User;
import com.buyer.springmvc.parameters.RestUserResponse;
import com.buyer.springmvc.parameters.SaveUserRequest;
import com.buyer.springmvc.service.UserService;

@RestController
public class AdminController {

	Logger logging = Logger.getLogger(this.getClass().getName());

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@RequestMapping(value = "/account", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RestUserResponse saveUser(@RequestBody SaveUserRequest userRequest) {

		RestUserResponse response = new RestUserResponse();
		User user = null;
		

		try {
			user = userService.saveUser(userRequest);
			response.getUser().add(user);
			response.setMessage(messageSource.getMessage("user.create", null,Locale.ENGLISH));
		} catch (Exception e) {
			response.setMessage("Error: "+ e.getMessage());
			response.setSuccess(false);
		}

		

		return response;

	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RestUserResponse udpateUser(@PathVariable int userId,
			@RequestBody SaveUserRequest userRequest) {


		RestUserResponse response = new RestUserResponse();
		User user = null;

		try {
			user = userService.updateUser(userRequest, userId);
			response.getUser().add(user);
			response.setMessage(messageSource.getMessage("user.update", null,Locale.ENGLISH));
		} catch (NotFoundException e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
		}

		return response;

	}
	
	
	@RequestMapping(value = "/users/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RestUserResponse getAllUser(HttpServletRequest request) {
		
		User user = (User) request.getSession().getAttribute("user");
		RestUserResponse response = new RestUserResponse();
		List<User> userList;
		try {
			userList = userService.getAllUser(user);
			response.getUser().addAll(userList);
		} catch (NotFoundException e) {
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			e.printStackTrace();
			
		}

		return response;
	}
	

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable int userId, HttpServletRequest request) {

		User user = userService.findUserByUserId(userId);

		logging.log(Level.INFO, "user name is : " + user.getUserName());

		return user;

	}
	
}
