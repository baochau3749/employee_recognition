package com.employee_recognition.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/user")
	public String userMainPage() {
		return "user";
	}
	
}
