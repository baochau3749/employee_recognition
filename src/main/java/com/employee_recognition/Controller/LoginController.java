package com.employee_recognition.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
	
	@RequestMapping("/")
	public String loginPage() {
		
		System.out.println("Hello World");
		
		return "login";
	}
	
}
