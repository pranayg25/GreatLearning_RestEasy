package com.greatlearning.resteasy.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.greatlearning.resteasy.test.service.DishServiceTest;
import com.greatlearning.resteasy.test.service.OrderServiceTest;
import com.greatlearning.resteasy.test.service.UserServiceTest;
import com.greatlearning.resteasy.test.service.VendorServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	DishServiceTest.class, 
	OrderServiceTest.class,
	UserServiceTest.class,
	VendorServiceTest.class
	})
public class ServiceTestSuit {}
