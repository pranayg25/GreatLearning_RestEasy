package com.greatlearning.resteasy.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.greatlearning.resteasy.test.dao.UserDaoTest;

@RunWith(Suite.class)
@SuiteClasses({
	UserDaoTest.class
	})
public class DaoTestSuit {

}
