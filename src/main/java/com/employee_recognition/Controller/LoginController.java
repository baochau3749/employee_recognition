package com.employee_recognition.Controller;


import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.employee_recognition.Entity.Employee;
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
	
	@RequestMapping(value ="/reset", method= RequestMethod.GET)
	public String resetPassword(Model model)
	{
		User temp = new User();
		model.addAttribute("user",temp);
		return "user_reset_password";
	}
	
	@RequestMapping(value="/reset", method=RequestMethod.POST)
	public String sendNewPassword(@ModelAttribute("user") User current, Model model) throws EmailException
	{
		current = userService.getUserByEmail(current.getEmail());
		
		String message;
		
		if (current == null)
		{
			message = "Error: that email is not in our system";
			model.addAttribute("message", message);
			return "user_reset_password";
		}
		else
		{
			// updating the user's credential and storing it
			current.setPassword(userService.generateRandomPassword());
			current = userService.saveUser(current, "USER");
			
			// sending the email notification
			userService.sendEmailResetPassword(current);

			return "redirect:/reset_valid";
		}
	}
	
	@RequestMapping(value="reset_valid")
	public String passwordHasBeenReset(Model model)
	{
		String message = "An email containing a temporary password has been sent to the provided email.";
		model.addAttribute("message", message);
		return "user_reset_password_valid";
	}
}
