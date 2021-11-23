package com.taller2.repository;

import org.springframework.data.repository.CrudRepository;

import com.taller2.model.sales.Salesorderdetail;
import com.taller2.model.sales.SalesorderdetailPK;

public interface SalesorderdetailRepository extends CrudRepository<Salesorderdetail, SalesorderdetailPK> {
	
}
