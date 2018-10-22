package com.employee_recognition.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.employee_recognition.Entity.User;
import com.employee_recognition.Entity.UserDetailsImpl;
import com.employee_recognition.Repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		// Email serves as user name in this app.
		User user = userRepository.findByEmail(username);
				
		if (user == null)
			throw new UsernameNotFoundException("User name is not found");
		
		System.out.println(user);
		
		return new UserDetailsImpl(user);
	}

}
