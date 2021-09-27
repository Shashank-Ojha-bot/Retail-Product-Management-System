package com.cognizant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.cognizant.model.Product;
import com.cognizant.repository.ProductRepository;

/**
 * @author Dhanush
 *
 */
@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	/**
	 * @return List<Product>
	 */
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		productRepository.findAll().forEach(product -> products.add(product));
		return products;
	}

	/**
	 * @param id
	 * @return List<Product>
	 */
	public List<Product> getProductById(String id) {
		List<Product> products = new ArrayList<Product>();
		productRepository.findAll().forEach(product -> products.add(product));
		return products.stream().filter(c -> (c.getId().toLowerCase()).contains(id.toLowerCase()))
				.collect(Collectors.toList());
	}

	/**
	 * @param name
	 * @return List<Product>
	 */
	public List<Product> getProductByName(String name) {
		List<Product> products = new ArrayList<Product>();
		productRepository.findAll().forEach(product -> products.add(product));
		return products.stream().filter(c -> (c.getName().toLowerCase()).contains(name.toLowerCase()))
				.collect(Collectors.toList());
	}

	/**
	 * @param id
	 * @param rating
	 */
	public void saveOrUpdate(String id, int rating) {
//		if(bindingRes.hasErrors())
//		{
//			Product product = productRepository.findById(id).get();
//			productRepository.save(product);
//		}
//		else
//		{
		Product product = productRepository.findById(id).get();
		product.setRating(rating);
		productRepository.save(product);
		
	}

}