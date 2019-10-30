package com.sec.service;
import com.sec.model.User;
import com.sec.repository.UserRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service("userDetailsService")
//@Transactional
public class UserService implements UserDetailsService{

	
	@Autowired
	UserRepository userRepository;
	
	public void saveUer(User user) {
		userRepository.save(user);		
	}
	//Spring Security Specific
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	// From DB
		User user = userRepository.findByUsername(username);
		// to comply with spring sec user
		UserBuilder builder = null;
		
		if(user != null) {
			
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(user.getPassword());
			builder.disabled(!user.isEnabled());
			String[] authorities = user.getAuthorities().stream()
					.map(
							a-> a.getAuthority()).toArray(String[]::new);
			
			builder.authorities(authorities);										
		}
		else 
		{
			throw new UsernameNotFoundException("user Not found");
		}
		
		return builder.build();
		}
		
}
		



