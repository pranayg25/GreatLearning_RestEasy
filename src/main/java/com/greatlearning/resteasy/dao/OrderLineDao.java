package com.greatlearning.resteasy.dao;

import org.hibernate.HibernateException;

import com.greatlearning.resteasy.entities.Orderline;

public class OrderLineDao extends AbstractDao {

	public OrderLineDao() {
	}
	
    public void save(Orderline entity) {
    	openCurrentSessionwithTransaction();
        getCurrentSession().save(entity);
        closeCurrentSessionwithTransaction();
    }
}
