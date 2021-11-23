package com.taller2.service.interfaces;

import java.util.Optional;

import com.taller2.model.person.UserApp;
import com.taller2.model.person.UserType;

public interface UserService {
	public void save(UserApp user);
	
	public void update(long id, UserApp user);
	
	public void delete(UserApp user);
	
	public Optional<UserApp> findById(long id);

	public Iterable<UserApp> findAll();

	public Iterable<UserApp> findAllAdmins();

	public Iterable<UserApp> findAllOperators();

	public UserType[] getTypes();
}
