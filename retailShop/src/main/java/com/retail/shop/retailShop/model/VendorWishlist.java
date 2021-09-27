package com.retail.shop.retailShop.model;

import java.util.Date;


public class VendorWishlist {

	private String vendorWishlistId;
	
	private String vendorId;
	
	private String productId;

	private int quantity;

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
		return "VendorWishlist [vendorWishlistId=" + vendorWishlistId + ", vendorId=" + vendorId + ", productId="
				+ productId + ", quantity=" + quantity + ", dateAddedToWishlist=" + dateAddedToWishlist + "]";
	}
}
