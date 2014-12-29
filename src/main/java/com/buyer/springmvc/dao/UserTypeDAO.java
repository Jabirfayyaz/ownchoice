/**
 * 
 */
package com.buyer.springmvc.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.buyer.springmvc.model.User;
import com.buyer.springmvc.model.UserType;

/**
 * @author Jay
 *
 */
@Repository
public class UserTypeDAO {
	
	private SessionFactory sessionFactory;
	private HibernateTemplate userTemplate;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		userTemplate = new HibernateTemplate(sessionFactory);
    }
	
	public UserType getUserType(int id){
		return (UserType) userTemplate.get(UserType.class, id);
	}
	
	public UserType getUserTypeByName(String name){
		@SuppressWarnings({ "unchecked", "unchecked" })
		List<UserType>  userList = sessionFactory.getCurrentSession()
    	        .createQuery( "from UserType u where u.name = :name" )
    	        .setString( "name", name )
    	        .list();

    	if (userList.size() > 0)
        	return userList.get(0);
        else return null;
	}

}
