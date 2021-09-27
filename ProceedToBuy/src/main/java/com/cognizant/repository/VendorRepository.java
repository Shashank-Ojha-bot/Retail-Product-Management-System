package com.cognizant.repository;

import org.springframework.data.repository.CrudRepository;

import com.cognizant.model.Vendor;

/**
 * @author Vamsi Krishna
 *
 */
public interface VendorRepository extends CrudRepository<Vendor, String>{
}