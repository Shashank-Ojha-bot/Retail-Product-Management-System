package com.cognizant.repository;

import org.springframework.data.repository.CrudRepository;

import com.cognizant.model.Cart;

/**
 * @author Vamsi Krishna
 *
 */
public interface CartRepository extends CrudRepository<Cart, String>{
}
