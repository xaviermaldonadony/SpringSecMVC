package com.sec.controller;

import com.sec.model.Authorities;
import com.sec.model.User;
import com.sec.repository.UserRepository;
import com.sec.service.UserService;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.transaction.annotation.Transactional;



@Controller
@Transactional
public class InitAdminIfNotExists {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	

	//Optional
	@RequestMapping("/login")
	public void initAdmin() {
	
		User user = userRepository.findByUsername("kun");
		
		if(user == null) {
			System.out.println("Creating admin...");
			User adminUser = new User();
			adminUser.setName("kun");
			adminUser.setUsername("kun");
			String encoded = new BCryptPasswordEncoder().encode("123456");
			adminUser.setPassword(encoded);
			adminUser.setEnabled(true);

			Authorities role1 = new Authorities();
			role1.setUser(adminUser);
			role1.setAuthority("ADMIN");
			
//			Authorities role2 = new Authorities();
//			role2.setUser(adminUser);
//			role2.setAuthority("USER");
			
			adminUser.getAuthorities().add(role1);
//			adminUser.getAuthorities().add(role2);
			
			userService.saveUer(adminUser);
		}
		
	}
	
	
	
}
