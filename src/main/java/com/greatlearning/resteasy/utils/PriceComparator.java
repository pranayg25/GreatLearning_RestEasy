package com.greatlearning.resteasy.utils;

import java.util.Comparator;

import com.greatlearning.resteasy.entities.VendorDish;

public class PriceComparator implements Comparator<VendorDish> {

	@Override
	public int compare(VendorDish o1, VendorDish o2) {
		if(o1.getPrice()>o2.getPrice()) {
			return 1;
		}else if(o1.getPrice()<o2.getPrice()){
			return -1;
		}
		return 0;
	}

	
}
