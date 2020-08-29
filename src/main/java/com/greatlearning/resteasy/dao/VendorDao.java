package com.greatlearning.resteasy.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.greatlearning.resteasy.entities.Vendor;

public class VendorDao extends AbstractDao {
 
    public VendorDao() {
	}
	    
    public void save(Vendor entity) {
        getCurrentSession().save(entity);
    }
    
    public Vendor findById(String id) throws HibernateException {
    	openCurrentSession();
    	Vendor vendor = (Vendor) getCurrentSession().get(Vendor.class, id);
    	closeCurrentSession();
        return vendor; 
    }
    
    public List<Vendor> findAll() throws HibernateException {
    	openCurrentSession();
        List<Vendor> vendors = getCurrentSession().createQuery("from Vendor").list();
        closeCurrentSession();
        return vendors;
    }
    
}
