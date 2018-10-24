package com.employee_recognition.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee_recognition.Entity.User;
import com.employee_recognition.Service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	public AdminController() {
	}		

//	@GetMapping("")
//	public String showAdminMainPage(Model theModel) {
//		User loggedInUser = userService.getLoggedInUser();
//		System.out.println(">>> loggedInUser = " + loggedInUser);
//		theModel.addAttribute("loggedInUser", loggedInUser);
//		
//		return "admin";
//	}
	
	@GetMapping("/user_management")
	public String showUserManagementPage(Model theModel) {

		theModel.addAttribute("loggedInUser", userService.getLoggedInUser());
		
		List<User> users = this.userService.getAllUsers();
		theModel.addAttribute("users", users);
		
		return "user_management";
	}
	
	@GetMapping("/account/{id}")
	public String updateUser(@ModelAttribute("id") Long id, Model theModel) {		
		
		theModel.addAttribute("loggedInUser", userService.getLoggedInUser());
		
		User user = userService.getUserById(id);
		theModel.addAttribute("account", user);
		
		if (user.getRole().getRole().compareTo("USER") == 0)
			return "user_account";
		else
			return "admin_account";
	}
	
	@GetMapping("/account/add_user")
	public String addUser(Model theModel) {		
		theModel.addAttribute("loggedInUser", userService.getLoggedInUser());
		theModel.addAttribute("user", null);
		return "user_account";
	}
	
	@PostMapping("/account/save_user")
	public String saveUser(@ModelAttribute("user") User user, Model theModel) {		
		userService.saveUser(user, "USER");	
		return "redirect:/admin/user_management";
	}
	
	@GetMapping("/account/add_admin")
	public String addAdmin(Model theModel) {		
		theModel.addAttribute("loggedInUser", userService.getLoggedInUser());
		theModel.addAttribute("account", null);
		return "admin_account";
	}
	
	@PostMapping("/account/save_admin")
	public String saveAdmin(@ModelAttribute("account") User account, Model theModel) {		
		userService.saveUser(account, "ADMIN");	
		return "redirect:/admin/user_management";
	}
	
	@RequestMapping("/account/delete_account")
	public String deleteAccount(@RequestParam("id") Long id) {	
		userService.deleteUserById(id);
		return "redirect:/admin/user_management";
	}
}
