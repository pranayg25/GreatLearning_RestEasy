package com.greatlearning.resteasy.test.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.greatlearning.resteasy.entities.Vendor;
import com.greatlearning.resteasy.entities.VendorDish;
import com.greatlearning.resteasy.service.VendorService;

public class VendorServiceTest {

	private VendorService vendorService = new VendorService();
	
	@Test
	public void getAllVendorsTest1() {
		
		assertEquals(3, vendorService.getAllVendors().size());
		
	}
	
	@Test
	public void getVendorDishsTest1() {
		
		Vendor vendor = new Vendor();
		vendor.setId("2");
		
		List<VendorDish> vendorDishs = vendorService.getVendorDishs(vendor, "desc");
		
		assertEquals(2, vendorDishs.size());
		
	}
	
}
