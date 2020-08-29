package com.greatlearning.resteasy.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.greatlearning.resteasy.entities.User;
import com.greatlearning.resteasy.entities.Vendor;
import com.greatlearning.resteasy.entities.VendorDish;

public class VendorDishDao extends AbstractDao{


    public void save(Vendor entity) throws Exception{
        getCurrentSession().save(entity);
    }
    
    public List<VendorDish> getByCriteria(VendorDish vendorDish, String sort) throws HibernateException {
    	
    	openCurrentSession();
    	Criteria criteria = getCurrentSession().createCriteria(VendorDish.class);
    	
    	if(vendorDish.getDish()!=null) {
        	criteria.add(Restrictions.eq("dish", vendorDish.getDish()));    		
    	}
    	if(vendorDish.getVendor()!=null) {
        	criteria.add(Restrictions.eq("vendor", vendorDish.getVendor()));    		
    	}
    	
    	if(sort!=null && sort.equals("asc")) {
        	criteria.addOrder(Order.asc("price"));    		
    	}else if(sort!=null && sort.equals("desc")) {
        	criteria.addOrder(Order.desc("price"));    		
    	}


    	List<VendorDish> vendorDishs = (List<VendorDish>)criteria.list(); 
    	closeCurrentSession();
    	
    	return vendorDishs;
    	
    }
	
}
