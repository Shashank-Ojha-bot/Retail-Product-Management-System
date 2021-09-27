package com.cognizant.repository;

import org.springframework.data.repository.CrudRepository;

import com.cognizant.model.VendorWishlist;

/**
 * @author Vamsi Krishna
 *
 */
public interface VendorWishlistRepository extends CrudRepository<VendorWishlist, String>{
}
