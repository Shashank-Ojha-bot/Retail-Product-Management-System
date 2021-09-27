package com.cognizant.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.model.Product;
import com.cognizant.service.ProductService;

/**
 * @author Dhanush
 *
 */
@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	/**
	 * Gets all products present in the DB
	 * 
	 * @return List<Product>
	 */
	@GetMapping("/product")
	private List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	/**
	 * Searches product by id
	 * 
	 * @param id
	 * @return List<Product>
	 */
	@GetMapping("/searchProductById/{id}")
	private List<Product> getProduct(@PathVariable("id") String id) {
		return productService.getProductById(id);
	}

	/**
	 * Searches product by name
	 * 
	 * @param name
	 * @return List<Product>
	 */
	@GetMapping("/searchProductByName/{name}")
	private List<Product> getProducts(@PathVariable("name") String name) {
		return productService.getProductByName(name);
	}

	/**
	 * changes rating of the project using id
	 * 
	 * @param id
	 * @param rating
	 * @return String
	 */
	@RequestMapping(value = "/addProductRating", method = RequestMethod.POST)
	public String saveProduct(@RequestParam("id") String id, @RequestParam("rating") int rating) {
		if(rating<0 || rating>5)
			return id;
		productService.saveOrUpdate(id, rating);
		return id;
	}
}
