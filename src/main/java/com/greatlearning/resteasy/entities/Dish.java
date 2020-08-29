package com.greatlearning.resteasy.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dish database table.
 * 
 */
@Entity
@NamedQuery(name="Dish.findAll", query="SELECT d FROM Dish d")
public class Dish implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private int calories;

	private String name;

	//bi-directional many-to-one association to VendorDish
	@OneToMany(mappedBy="dish")
	private List<VendorDish> vendorDishs;

	public Dish() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCalories() {
		return this.calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VendorDish> getVendorDishs() {
		return this.vendorDishs;
	}

	public void setVendorDishs(List<VendorDish> vendorDishs) {
		this.vendorDishs = vendorDishs;
	}

	public VendorDish addVendorDish(VendorDish vendorDish) {
		getVendorDishs().add(vendorDish);
		vendorDish.setDish(this);

		return vendorDish;
	}

	public VendorDish removeVendorDish(VendorDish vendorDish) {
		getVendorDishs().remove(vendorDish);
		vendorDish.setDish(null);

		return vendorDish;
	}

}