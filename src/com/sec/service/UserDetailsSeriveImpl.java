package com.sec.service;
import com.sec.dao.UserDetailsDao;
import com.sec.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service("userDetailsService")
public class UserDetailsSeriveImpl implements UserDetailsService{

	
	
	@Autowired
	UserDetailsDao userDetailsDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	
		User user = userDetailsDao.findUserByUserName(username);
		// to comply with spring sec user
		UserBuilder builder = null;
		
		if(user != null) {
			
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(user.getPassword());
			builder.disabled(!user.isEnabled());
			String[] authorities = user.getAuthorities().stream()
					.map(a-> a.getAuthority()).toArray(String[]::new);
			
			builder.authorities(authorities);
			
										
		}
		else 
		{
			throw new UsernameNotFoundException("user Not found");
		}
		
		return builder.build();
		}
		
}
		



