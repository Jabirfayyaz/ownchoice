/**
 * 
 */
package com.buyer.springmvc.parameters;

/**
 * @author Jay
 *
 */
public abstract class JsonResponse {
	
	private String message;
	private boolean success = true;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
		

}
