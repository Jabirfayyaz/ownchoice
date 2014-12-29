/**
 * 
 */
package com.buyer.springmvc.viewresolver;

import com.buyer.springmvc.common.Constants;

/**
 * @author Jay
 *
 */
public class Employee implements Landing, Constants{

	/* (non-Javadoc)
	 * @see com.buyer.springmvc.viewresolver.Landing#getLandingPage()
	 */
	@Override
	public String getLandingPageName() {
		return EMPLOYEE_LANDING_PAGE;

	}

}
