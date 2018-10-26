package com.employee_recognition.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.employee_recognition.Entity.User;
import com.employee_recognition.Service.UserService;



@Controller
@SessionAttributes("userID")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String loginPage(Model theModel) {

		User user = userService.getLoggedInUser();
		theModel.addAttribute("loggedInUser", user);
		
		if (user.getRole().getRole().compareTo("ADMIN") == 0) {
			return "admin";
		}
		else
		{
			Long userID = user.getId();
			theModel.addAttribute("userID",userID);
			return "redirect:/user";
		}
	}
}
