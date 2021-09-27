package com.retail.shop.retailShop.model;

public class Retail {
	private String id;
	private int price;
	private String name;
	private String description;
	private int rating;
	
	public String getId() 
	{
		return id;
	}
	
	public void setId(String id) 
	{
		this.id = id;
	}
	
	public int getPrice() 
	{
		return price;
	}
	
	public void setPrice(int price) 
	{
		this.price = price;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public int getRating() 
	{
		return rating;
	}
	public void setRating(int rating) 
	{
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Retail [id=" + id + ", price=" + price + ", name=" + name + ", description=" + description + ", rating="
				+ rating + "]";
	}
	
	
}