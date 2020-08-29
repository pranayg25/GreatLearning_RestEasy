package com.greatlearning.resteasy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.greatlearning.resteasy.dao.DishDao;
import com.greatlearning.resteasy.dao.VendorDishDao;
import com.greatlearning.resteasy.entities.Dish;
import com.greatlearning.resteasy.entities.VendorDish;
import com.greatlearning.resteasy.utils.PriceComparator;

public class DishService {

	private DishDao dishDao;
	private VendorDishDao vendorDishDao;
	
	public DishService() {
		dishDao = new DishDao();
		vendorDishDao = new VendorDishDao();
	}
	
	public List<VendorDish> searchDishs(String search, String sort){
		
		List<VendorDish> vendorDishs = new ArrayList<VendorDish>();
		
		List<Dish> dishs = dishDao.searchDish(search);
		
		if(dishs!=null && dishs.size()>0) {
			for(Dish dish: dishs) {
				VendorDish vendorDish = new VendorDish();
				vendorDish.setDish(dish);
				vendorDishs.addAll(vendorDishDao.getByCriteria(vendorDish, null));
			}
		}
		
		if(sort!=null && !sort.trim().equals("")) {
			if(sort.equals("asc")) {
				Collections.sort(vendorDishs, new PriceComparator());
			}else {
				Collections.sort(vendorDishs, new PriceComparator().reversed());
			}

		}
		
		return vendorDishs;
	}
}
