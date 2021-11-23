package com.taller2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.taller2.model.person.UserApp;
import com.taller2.model.person.UserType;


public interface UserRepository extends CrudRepository<UserApp, Long> {
	List<UserApp> findByUsername(String username);
	List<UserApp> findByType(UserType type);
}
