package com.greatlearning.resteasy.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the orderline database table.
 * 
 */
@Entity
@NamedQuery(name="Orderline.findAll", query="SELECT o FROM Orderline o")
public class Orderline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private int quantity;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="orderId")
	private Order order;

	//bi-directional many-to-one association to VendorDish
	@ManyToOne
	@JoinColumn(name="vendorDishId")
	private VendorDish vendorDish;

	public Orderline() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public VendorDish getVendorDish() {
		return this.vendorDish;
	}

	public void setVendorDish(VendorDish vendorDish) {
		this.vendorDish = vendorDish;
	}

}