package com.greatlearning.resteasy.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@Table(name="customer_order")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	//@Temporal(TemporalType.TIMESTAMP)
	private Timestamp orderDate;

	private int totalAmount;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="customerId")
	private User user;

	//bi-directional many-to-one association to Orderline
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER)
	private List<Orderline> orderlines;

	public Order() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		/*
		 * try { this.orderDate = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(orderDate.); } catch
		 * (ParseException e) { System.out.println("DATE TIME LAFDA"); // TODO
		 * Auto-generated catch block //e.printStackTrace(); }
		 */
		this.orderDate = orderDate;
	}

	public int getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Orderline> getOrderlines() {
		return this.orderlines;
	}

	public void setOrderlines(List<Orderline> orderlines) {
		this.orderlines = orderlines;
	}

	public Orderline addOrderline(Orderline orderline) {
		getOrderlines().add(orderline);
		orderline.setOrder(this);

		return orderline;
	}

	public Orderline removeOrderline(Orderline orderline) {
		getOrderlines().remove(orderline);
		orderline.setOrder(null);

		return orderline;
	}

}