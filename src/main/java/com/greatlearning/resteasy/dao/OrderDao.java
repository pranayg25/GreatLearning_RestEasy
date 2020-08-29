package com.greatlearning.resteasy.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.greatlearning.resteasy.entities.Order;

public class OrderDao extends AbstractDao{

	
	public OrderDao() {
	}
	
    public void save(Order entity) {
    	openCurrentSessionwithTransaction();
        getCurrentSession().save(entity);
        closeCurrentSessionwithTransaction();
    }
	
    public List<Order> getByCriteria(Order order, String sort, String field) throws HibernateException {
    	
    	openCurrentSession();
    	Criteria criteria = getCurrentSession().createCriteria(Order.class);
    	
		/*
		 * if(vendorDish.getDish()!=null) { criteria.add(Restrictions.eq("dish",
		 * vendorDish.getDish())); } if(vendorDish.getVendor()!=null) {
		 * criteria.add(Restrictions.eq("vendor", vendorDish.getVendor())); }
		 */
    	
    	if(field!=null && field.equals("orderDate")) {
        	if(sort!=null && sort.equals("asc")) {
            	criteria.addOrder(org.hibernate.criterion.Order.asc("orderDate"));    		
        	}else if(sort!=null && sort.equals("desc")) {
            	criteria.addOrder(org.hibernate.criterion.Order.desc("orderDate"));    		
        	}
    	}
    	
    	if(field!=null && field.equals("totalAmount")) {
        	if(sort!=null && sort.equals("asc")) {
            	criteria.addOrder(org.hibernate.criterion.Order.asc("totalAmount"));    		
        	}else if(sort!=null && sort.equals("desc")) {
            	criteria.addOrder(org.hibernate.criterion.Order.desc("totalAmount"));    		
        	}
    	}
    	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    	List<Order> orders = criteria.list(); 
    	closeCurrentSession();
    	
    	return orders;
    	
    }
	
}
