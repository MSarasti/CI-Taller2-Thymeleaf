package com.taller2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.taller2.model.prod.*;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	List<Product> findByName(Object name);
}
