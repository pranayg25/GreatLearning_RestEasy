package com.greatlearning.resteasy.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import com.greatlearning.resteasy.dao.OrderDao;
import com.greatlearning.resteasy.dao.OrderLineDao;
import com.greatlearning.resteasy.entities.Order;
import com.greatlearning.resteasy.entities.Orderline;
import com.greatlearning.resteasy.utils.Util;

public class OrderService {

	private OrderDao orderDao;
	private OrderLineDao orderLineDao;
	
	public OrderService() {
		this.orderDao = new OrderDao();
		this.orderLineDao = new OrderLineDao();
	}
	
	public List<Order> getAllOrders(String sort, String field) {
		return orderDao.getByCriteria(null, sort, field);
	}

	@Transactional
	public void generateOrder(Order order) throws IllegalArgumentException{
		if(order!=null) {
			
			if(order.getUser()==null) {
				throw new IllegalArgumentException("user not mentioned");
			}
			if(order.getOrderlines()==null || (order.getOrderlines()!=null && order.getOrderlines().size()==0) ) {
				throw new IllegalArgumentException("no dish added to order");
			}
			
			order.setId(Util.generateGUID());
			order.setOrderDate(new Timestamp(new GregorianCalendar().getTimeInMillis()));
			
			orderDao.save(order);				

			
			for(Orderline ool: order.getOrderlines()) {
				ool.setId(Util.generateGUID());
				ool.setOrder(order);
				orderLineDao.save(ool);	
			}
		}
	}
}
