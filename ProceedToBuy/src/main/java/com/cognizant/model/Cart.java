package com.cognizant.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author Vamsi Krishna
 *
 */
@Component
@Entity
@Table
public class Cart {
	@Id
	@Column
	private String cartId;
	@Column
	private String productId;
	@Column
	private String zipcode;
	@Column
	private Date deliveryDate;
	@ManyToOne(targetEntity=Vendor.class)  
	@JoinColumn(name="vendor_id", referencedColumnName = "vendorId")
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
