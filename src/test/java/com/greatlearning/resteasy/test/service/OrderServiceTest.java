package com.greatlearning.resteasy.test.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;

import com.greatlearning.resteasy.entities.Order;
import com.greatlearning.resteasy.entities.Orderline;
import com.greatlearning.resteasy.entities.User;
import com.greatlearning.resteasy.entities.VendorDish;
import com.greatlearning.resteasy.service.OrderService;
import com.greatlearning.resteasy.utils.Util;

public class OrderServiceTest {

	private OrderService orderService = new OrderService();
	
	//empty order should throw exception
	@Test(expected = IllegalArgumentException.class)
	public void generateOrderTest1(){

		Order order = new Order();	
		orderService.generateOrder(order);
	}
	
	//empty order should throw exception
	@Test
	@Transactional
	public void generateOrderTest2(){
		
		VendorDish vendorDish = new VendorDish();
		vendorDish.setId("1");


		User user = new User();
		user.setId("1");
		user.setName("pranay");
		

		Order order = new Order();	
		order.setUser(user);
		order.setTotalAmount(999);
		
		Orderline orderline = new Orderline();
		orderline.setId(Util.generateGUID());
		orderline.setQuantity(1);
		orderline.setVendorDish(vendorDish);
		orderline.setOrder(order);
		
		List<Orderline> orderlines = new ArrayList<Orderline>();
		orderlines.add(orderline);
		
		order.setOrderlines(orderlines);
		
		orderService.generateOrder(order);
		
		assertEquals(order.getId(), orderService.getAllOrders("desc", "orderDate").get(0).getId());
		
	}

	@Test
	public void getAllOrdersTest1(){
		
		
		VendorDish vendorDish = new VendorDish();
		vendorDish.setId("1");


		User user = new User();
		user.setId("1");
		user.setName("pranay");
		

		Order order = new Order();	
		order.setUser(user);
		order.setTotalAmount(999);
		
		Orderline orderline = new Orderline();
		orderline.setId(Util.generateGUID());
		orderline.setQuantity(1);
		orderline.setVendorDish(vendorDish);
		orderline.setOrder(order);
		
		List<Orderline> orderlines = new ArrayList<Orderline>();
		orderlines.add(orderline);
		
		order.setOrderlines(orderlines);
		
		List<Order> orders = orderService.getAllOrders(null,null);
		
		orderService.generateOrder(order);
		
		assertEquals(orders.size()+1, orderService.getAllOrders(null,null).size());
		
	}
	
	
	
}
