package com.retail.shop.retailShop.model;

import java.util.Date;

public class Cart {
	
	private String cartId;

	private String productId;

	private String zipcode;

	private Date deliveryDate;

	private Vendor vObject;
	
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Vendor getvObject() {
		return vObject;
	}
	public void setvObject(Vendor vObject) {
		this.vObject = vObject;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", productId=" + productId + ", zipcode=" + zipcode + ", deliveryDate="
				+ deliveryDate + ", vObject=" + vObject + "]";
	}
}
