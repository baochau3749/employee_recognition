package com.employee_recognition.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class LoginController {
	
	@RequestMapping("/")
	public String loginPage(Authentication authentication) {

		UserDetails loggedInUser = (UserDetails)authentication.getPrincipal();
		GrantedAuthority authority = (GrantedAuthority)loggedInUser.getAuthorities().toArray()[0];

		if (authority.getAuthority().compareTo("ROLE_ADMIN") == 0) {
			return "admin";
		}
		
		return "user";
	}
}
