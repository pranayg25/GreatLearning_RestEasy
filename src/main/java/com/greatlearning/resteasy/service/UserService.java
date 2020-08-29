package com.greatlearning.resteasy.service;

import com.greatlearning.resteasy.dao.UserDao;
import com.greatlearning.resteasy.entities.User;
import com.greatlearning.resteasy.utils.Role;


public class UserService {

	private UserDao userDao;

	public UserService() {
		this.userDao = new UserDao();
	}
	
	public User login(String userName, String password, Role role) {

		User user = userDao.login(userName, password, role);
		
		return user;
	}
	
	
}
