package com.sec.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sec.model.User;

@Repository

public interface UserRepository extends CrudRepository<User, String>{

	User findByUsername(String username);
	
}
