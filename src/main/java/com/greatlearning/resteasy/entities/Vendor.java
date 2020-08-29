package com.greatlearning.resteasy.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vendor database table.
 * 
 */
@Entity
@NamedQuery(name="Vendor.findAll", query="SELECT v FROM Vendor v")
public class Vendor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	//bi-directional many-to-one association to VendorDish
	@OneToMany(mappedBy="vendor")
	private List<VendorDish> vendorDishs;

	public Vendor() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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
		vendorDish.setVendor(this);

		return vendorDish;
	}

	public VendorDish removeVendorDish(VendorDish vendorDish) {
		getVendorDishs().remove(vendorDish);
		vendorDish.setVendor(null);

		return vendorDish;
	}

}