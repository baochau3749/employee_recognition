package com.employee_recognition.Controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.employee_recognition.Entity.Report;
import com.employee_recognition.Entity.User;
import com.employee_recognition.Service.AwardService;
import com.employee_recognition.Service.ReportService;
import com.employee_recognition.Service.UserService;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"title", "table", "total"})
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private AwardService awardService;
	
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
	
	
	@GetMapping("/award_report")
	public String showAwardReportingPage(Model model)
	{
		// Analytics Reports
		List<Report> reportList = new ArrayList<>();
		reportList.add(new Report("Gender Report", "gender"));
		reportList.add(new Report("Department Report", "department"));
		reportList.add(new Report("State Report", "state"));
		reportList.add(new Report("Position Report", "position"));
		reportList.add(new Report("Award Type Report", "awardType"));
		
		model.addAttribute("report", reportList);
		model.addAttribute("loggedInUser", userService.getLoggedInUser());
		
		return "award_report";
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
	
	@RequestMapping("/account/download_report")
	public void downloadReport(@RequestParam("label") String label, HttpServletRequest request, HttpServletResponse response, Model model)
	{
		// create CSV file based on label provided
		String filename = new String();
		String title = new String();
		Map<String, Integer> table = new HashMap<>();
		
		switch (label)
		{
			case "state":
			{
				for (Map.Entry<String, Map<String, Integer>> result : reportService.stateReport().entrySet())
				{
					filename = result.getKey();
					table = result.getValue();
				}

				title = "STATE REPORT";
				break;
			}
			case "department":
			{
				for (Map.Entry<String, Map<String, Integer>> result : reportService.departmentReport().entrySet())
				{
					filename = result.getKey();
					table = result.getValue();
				}

				title = "DEPARTMENT REPORT";
				break;
			}
			case "gender":
			{
				for (Map.Entry<String, Map<String, Integer>> result : reportService.genderReport().entrySet())
				{
					filename = result.getKey();
					table = result.getValue();
				}

				title = "GENDER REPORT";
				break;
			}
			case "position":
			{
				for (Map.Entry<String, Map<String, Integer>> result : reportService.positionReport().entrySet())
				{
					filename = result.getKey();
					table = result.getValue();
				}

				title = "POSITION REPORT";
				break;
			}
			case "awardType":
			{
				for (Map.Entry<String, Map<String, Integer>> result : reportService.awardTypeReport().entrySet())
				{
					filename = result.getKey();
					table = result.getValue();
				}

				title = "AWARD TYPE REPORT";
				break;
			}
		}
		
		model.addAttribute("title",title);
		model.addAttribute("table", table);
		model.addAttribute("total", awardService.getAwards().size());
		
		// target the new CSV file
		String filePath = System.getProperty("user.dir")+"\\src\\main\\webapp\\report_files";
		Path file = Paths.get(filePath, filename);
		
		// type of download
		response.setContentType("text/csv");
		response.addHeader("Content-Disposition", "attachment; filename="+filename);

		// download the file
		try
		{
			Files.copy(file, response.getOutputStream());
			response.getOutputStream().flush();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
