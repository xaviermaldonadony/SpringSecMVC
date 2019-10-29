package com.sec.dao;

import com.sec.model.User;

public interface UserDetailsDao {

	User findUserByUserName(String username);
	
	
}
