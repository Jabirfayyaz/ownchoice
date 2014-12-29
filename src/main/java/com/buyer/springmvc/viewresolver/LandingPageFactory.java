/**
 * 
 */
package com.buyer.springmvc.viewresolver;

import com.buyer.springmvc.common.Constants;

/**
 * @author Jay
 *
 */
public class LandingPageFactory implements Constants{
	
	public static Landing getUserProfileObject(String type){
		
		if (ADMIN_USER_TYPE_NAME.equals(type)){
			return new Administrator();
		}
		else if (CORPORATE_USER_TYPE_NAME.equals(type)){
			return new Corporate();
		}
		else if (SUPPLIER_USER_TYPE_NAME.equals(type)){
			return new Supplier();
		}
		else {
			return new Employee();
		}
	}

}
