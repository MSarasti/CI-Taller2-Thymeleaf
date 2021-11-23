package com.taller2.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.taller2.model.sales.Specialoffer;
import com.taller2.model.sales.Specialofferproduct;
import com.taller2.model.sales.SpecialofferproductPK;

public interface SpecialofferproductRepository extends CrudRepository<Specialofferproduct, SpecialofferproductPK> {
	Optional<Specialofferproduct> findBySpecialoffer(Specialoffer specialoffer);
}
