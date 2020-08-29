package com.greatlearning.resteasy.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.greatlearning.resteasy.entities.VendorDish;
import com.greatlearning.resteasy.service.DishService;

public class DishServiceTest {

	private DishService dishService = new DishService();
	
	//Search for 'piz' should give 4 results
	@Test
	public void dishSearch1(){
		String search = "piz";
		String sort = "";
		
		List<VendorDish> vendorDishs = dishService.searchDishs(search, sort);
		
		assertTrue(vendorDishs.size()>0);
		assertEquals(4, vendorDishs.size());
	}
	
	//Search for 'abc' should give no results
	@Test
	public void dishSearch2(){
		String search = "abc";
		String sort = "";
		
		List<VendorDish> vendorDishs = dishService.searchDishs(search, sort);
		
		assertTrue(vendorDishs.size()==0);
		assertEquals(0, vendorDishs.size());
	}
	
	
}
