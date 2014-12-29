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

/**
 * @author Jay
 *
 */
@Repository
public class UserDAO {

	private SessionFactory sessionFactory;
	private HibernateTemplate userTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		userTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * Find persons by user name/password.
	 */
	public User findUserByUserNamePassword(String userName, String password)
			throws DataAccessException {

		@SuppressWarnings({ "unchecked", "unchecked" })
		List<User> userList = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from User u where u.userName = :userName and u.password = :password")
				.setString("userName", userName)
				.setString("password", password).list();

		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

	/**
	 * Find persons by user name
	 */
	public User findUserByUserName(String userName) throws DataAccessException {

		@SuppressWarnings({ "unchecked", "unchecked" })
		List<User> userList = sessionFactory.getCurrentSession()
				.createQuery("from User u where u.userName = :userName")
				.setString("userName", userName).list();

		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

	/**
	 * Find all users
	 */
	public List<User> getAllUser() throws DataAccessException {

		@SuppressWarnings({ "unchecked", "unchecked" })
		List<User> userList = sessionFactory.getCurrentSession()
				.createQuery("from User u ").list();

		return userList;
	}

	/**
	 * Find persons by user name
	 */
	public void saveUser(User user) {
		userTemplate.save(user);
	}

	public void update(User user){
    	User candidate = findById(user.getId());
        if (candidate != null) {
            user.setId(candidate.getId());         
            sessionFactory.getCurrentSession().merge(user);
        }
    }

	public User findById(int id) {
		return (User) userTemplate.get(User.class, id);
	}

}
