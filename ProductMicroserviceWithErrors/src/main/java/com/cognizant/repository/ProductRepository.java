package com.cognizant.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.cognizant.model.Product;
public interface ProductRepository extends CrudRepository<Product, String>
{
	Optional<Product> findById(String id);
}
