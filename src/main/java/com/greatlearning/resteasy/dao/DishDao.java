package com.greatlearning.resteasy.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.greatlearning.resteasy.entities.Dish;
import com.greatlearning.resteasy.entities.Vendor;
import com.greatlearning.resteasy.entities.VendorDish;

public class DishDao extends AbstractDao{

	public List<Dish> searchDish(String search) throws HibernateException {
		openCurrentSession();
		//List<Dish> dishs = getCurrentSession().createQuery("from Dish where name like %"+search+"%").list();

		Criteria criteria = getCurrentSession().createCriteria(Dish.class);

		criteria.add(Restrictions.ilike("name", search,MatchMode.ANYWHERE));
		

		List<Dish> dishs = (List<Dish>)criteria.list(); 
		closeCurrentSession();
		return dishs;
	}
}
