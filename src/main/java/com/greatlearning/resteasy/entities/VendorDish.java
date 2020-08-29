package com.greatlearning.resteasy.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vendor_dish database table.
 * 
 */
@Entity
@Table(name="vendor_dish")
@NamedQuery(name="VendorDish.findAll", query="SELECT v FROM VendorDish v")
public class VendorDish implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private int price;

	//bi-directional many-to-one association to Orderline
	@OneToMany(mappedBy="vendorDish")
	private List<Orderline> orderlines;

	//bi-directional many-to-one association to Dish
	@ManyToOne
	@JoinColumn(name="dishId")
	private Dish dish;

	//bi-directional many-to-one association to Vendor
	@ManyToOne
	@JoinColumn(name="vendorId")
	private Vendor vendor;

	public VendorDish() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Orderline> getOrderlines() {
		return this.orderlines;
	}

	public void setOrderlines(List<Orderline> orderlines) {
		this.orderlines = orderlines;
	}

	public Orderline addOrderline(Orderline orderline) {
		getOrderlines().add(orderline);
		orderline.setVendorDish(this);

		return orderline;
	}

	public Orderline removeOrderline(Orderline orderline) {
		getOrderlines().remove(orderline);
		orderline.setVendorDish(null);

		return orderline;
	}

	public Dish getDish() {
		return this.dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Vendor getVendor() {
		return this.vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

}