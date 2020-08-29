package com.greatlearning.resteasy.service;

import java.util.List;

import com.greatlearning.resteasy.dao.VendorDao;
import com.greatlearning.resteasy.dao.VendorDishDao;
import com.greatlearning.resteasy.entities.Vendor;
import com.greatlearning.resteasy.entities.VendorDish;

public class VendorService {

	private VendorDao vendorDao;
	private VendorDishDao vendorDishDao;
	
	public VendorService() {
		vendorDao = new VendorDao();
		vendorDishDao = new VendorDishDao();
	}
	
	public List<Vendor> getAllVendors(){
		
		return vendorDao.findAll();
		
	}

	public List<VendorDish> getVendorDishs(Vendor vendor, String sort) {
		
		//List<Dish> dishs = new ArrayList<Dish>();
		VendorDish vendorDish = new VendorDish();
		vendorDish.setVendor(vendor);
		List<VendorDish> vendorDishs = vendorDishDao.getByCriteria(vendorDish,sort);
		/*
		 * for (VendorDish vd : vendorDishs) { dishs.add(vd.getDish()); }
		 */
		return vendorDishs;
	}
}
