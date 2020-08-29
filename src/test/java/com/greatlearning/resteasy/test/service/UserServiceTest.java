package com.greatlearning.resteasy.test.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.greatlearning.resteasy.entities.User;
import com.greatlearning.resteasy.service.UserService;
import com.greatlearning.resteasy.utils.Role;

public class UserServiceTest {

	
	private UserService userService = new UserService();
	
	@Test
	public void testLogin1() {
	
		String userId = "1";
		String userName = "pranay";
		String password = "pranay";
		Role role = Role.CUSTOMER;
		
		User user = userService.login(userName, password, role);
		
		assertEquals(userId, user.getId());
	}
	
	@Test
	public void testLogin2() {
	
		String userId = "0";
		String userName = "admin";
		String password = "admin";
		Role role = Role.ADMIN;
		
		User user = userService.login(userName, password, role);
		
		assertEquals(userId, user.getId());
	}
	
	@Test
	public void testLogin3() {
	
		String userName = "abc";
		String password = "abc";
		Role role = Role.CUSTOMER;
		
		User user = userService.login(userName, password, role);
		
		assertEquals(null, user);
	}
}
