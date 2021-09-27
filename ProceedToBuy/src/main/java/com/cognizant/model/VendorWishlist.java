package com.cognizant.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author Vamsi Krishna
 *
 */
@Component
@Entity
@Table
public class VendorWishlist {
	@Id
	@Column
	private String vendorWishlistId;
	@Column
	private String vendorId;
	@Column
	private String productId;
	@Column
	private int quantity;
	@Column
	private Date dateAddedToWishlist;
	
	public String getVendorWishlistId() {
		return vendorWishlistId;
	}
	public void setVendorWishlistId(String vendorWishlistId) {
		this.vendorWishlistId = vendorWishlistId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getDateAddedToWishlist() {
		return dateAddedToWishlist;
	}
	public void setDateAddedToWishlist(Date dateAddedToWishlist) {
		this.dateAddedToWishlist = dateAddedToWishlist;
	}
	
	@Override
	public String toString() {
		return "VendorWishlist [vendorWishlistId=" + getVendorWishlistId() + ", vendorId=" + getVendorId() + ", productId="
				+ getProductId() + ", quantity=" + getQuantity() + ", dateAddedToWishlist=" + getDateAddedToWishlist() + "]";
	}
}
