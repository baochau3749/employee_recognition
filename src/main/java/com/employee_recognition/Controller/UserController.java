package com.employee_recognition.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.employee_recognition.Entity.Award;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.User;
import com.employee_recognition.Repository.EmployeeRepository;
import com.employee_recognition.Service.AwardService;
import com.employee_recognition.Service.UserService;

@Controller
@SessionAttributes({"userID","user"})
public class UserController {

	@Autowired
	private UserService userDAO;
	
	@Autowired
	private EmployeeRepository employeeDAO;
	
	@Autowired
	private AwardService awardDAO;
	
	// User Main Page
	@GetMapping("/user")
	public String userMainPage(@SessionAttribute("userID") Long userID, Model model) {
		List<Award> awards;
		List<String> employees = new ArrayList<String>();
		User currentUser = userDAO.getUserById(userID); 
		awards = currentUser.getUserAwards();
		Employee emp = new Employee();
		for (int i = 0; i < awards.size(); i++) {
			Long empId = awards.get(i).getEmployee();
			//System.out.println("printing employee info " + employeeDAO.findById(empId).getFirstName() + " " + 
			//employeeDAO.findById(empId).getLastName());
			employees.add(employeeDAO.findById(empId).getFirstName() + " " + 
			employeeDAO.findById(empId).getLastName());
		}
		
		model.addAttribute("user", currentUser);
		model.addAttribute("awards", currentUser.getUserAwards());
		model.addAttribute("employees", employees);
		
		return "user";
	}
	
	// User Update Page GET
	@RequestMapping(value = "/user/{userID}", method=RequestMethod.GET )
	public String updateUserPage(@PathVariable("userID") Long userID, Model model)
	{
		User temp = userDAO.getUserById(userID);
		
		model.addAttribute("user", temp);
		
		return "user_update";
	}
	
	// User Update Page POST
	@RequestMapping(value = "/user/{userID}", method=RequestMethod.POST)
	public String saveUserPage(@ModelAttribute("user") User user)
	{
		System.out.println(user.toString());
		user = userDAO.saveUser(user,"USER");
		
		return "redirect:/user"; 
	}
	
	// Deleting an Award
	@RequestMapping(value="/delete_award")
	public String deleteAward(@RequestParam("id") Long id)
	{
		awardDAO.deleteAwardByID(id);
		return "redirect:/user";
	}
}
