/**
 * 
 */
package com.buyer.springmvc.parameters;

import java.util.ArrayList;
import java.util.List;

import com.buyer.springmvc.model.User;

/**
 * @author Jay
 *
 */
public class RestUserResponse extends JsonResponse{
	
	private List<User> user = new ArrayList<User>();

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}
	
	

}
