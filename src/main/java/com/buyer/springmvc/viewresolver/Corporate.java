/**
 * 
 */
package com.buyer.springmvc.viewresolver;

import com.buyer.springmvc.common.Constants;

/**
 * @author Jay
 *
 */
public class Corporate implements Landing, Constants{

	/* (non-Javadoc)
	 * @see com.buyer.springmvc.viewresolver.Landing#getLandingPage()
	 */
	@Override
	public String getLandingPageName() {
		return CORPORATE_LANDING_PAGE;

	}

}
