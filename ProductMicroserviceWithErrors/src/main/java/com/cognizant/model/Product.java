package com.cognizant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Dhanush
 *
 */
@Entity
@Table(name = "PRODUCT")
public class Product {
	@Id
	@Column
	private String id;

	@Column
	private int price;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	@Max(value = 5)
	@Min(value = 1)
	private int rating;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Product(String id, String description, String name, int price, int rating) {
		this.id = id;
		this.description = description;
		this.name = name;
		this.price = price;
		this.rating = rating;
	}

	public Product() {
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + ", name=" + name + ", description=" + description
				+ ", rating=" + rating + "]";
	}

}