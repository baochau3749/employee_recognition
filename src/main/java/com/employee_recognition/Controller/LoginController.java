package com.employee_recognition.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employee_recognition.Entity.User;
import com.employee_recognition.Service.UserService;



@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String loginPage() {

		User user = userService.getLoggedInUser();
		
		if (user.getRole().getRole().compareTo("ADMIN") == 0) {
			return "admin";
		}
		
		return "user";
	}
}
