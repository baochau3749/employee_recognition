package com.employee_recognition.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	// new commit here
	@GetMapping("/admin")
	public String adminMainPage() {
		return "admin";
	}
	
}
