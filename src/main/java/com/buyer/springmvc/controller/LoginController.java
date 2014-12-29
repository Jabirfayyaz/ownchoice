package com.buyer.springmvc.controller;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.buyer.springmvc.common.Constants;
import com.buyer.springmvc.model.User;
import com.buyer.springmvc.service.UserService;
import com.buyer.springmvc.viewresolver.Landing;
import com.buyer.springmvc.viewresolver.LandingPageFactory;

@Controller
public class LoginController implements Constants{
	
	Logger logging = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;

	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String welcome( ) {
		
		return WELCOME_LANDING_PAGE_NAME;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@RequestParam(value="userName", required=true) String userName,
			@RequestParam(value="password", required=true) String password, HttpServletRequest request) {
		
		/////////////////////////////////////////////////////////////////
		
		User user  = userService.loginUser(userName, password);
		
		if (user != null){
			request.getSession().setAttribute(SESSION_ATTRIBUTE_USER, user);
			Landing landing = LandingPageFactory.getUserProfileObject(user.getUserType().getName());
			return landing.getLandingPageName();
		}
		else{
			request.setAttribute("error", messageSource.getMessage("user.invalid", null,Locale.ENGLISH));
			return WELCOME_LANDING_PAGE_NAME;
		}
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request ) {
		
		request.getSession().removeAttribute(SESSION_ATTRIBUTE_USER);
		request.getSession().invalidate();
		
		logging.log(Level.INFO, "logging out user..." );
		
		return WELCOME_LANDING_PAGE_NAME;
	}
	
}
