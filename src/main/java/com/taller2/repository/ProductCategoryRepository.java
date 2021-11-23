package com.taller2.repository;

import org.springframework.data.repository.CrudRepository;

import com.taller2.model.prod.Productcategory;

public interface ProductCategoryRepository extends CrudRepository<Productcategory, Integer> {
	
}
