package com.employee_recognition.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employee_recognition.Entity.Report;
import com.employee_recognition.Entity.User;
import com.employee_recognition.Entity.UserProfile;
import com.employee_recognition.Repository.UserProfileRepository;
import com.employee_recognition.Service.AwardService;
import com.employee_recognition.Service.ReportService;
import com.employee_recognition.Service.UserService;

@Controller
@RequestMapping("/admin")
@SessionAttributes({ "title", "table", "total" })
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private ReportService reportService;

	@Autowired
	private AwardService awardService;

	@Autowired
	private ServletContext context;

	@Autowired
	private UserProfileRepository profileDAO;

	public AdminController() {
	}

	@GetMapping("/user_management")
	public String showUserManagementPage(Model theModel) {

		theModel.addAttribute("loggedInUser", userService.getLoggedInUser());

		List<User> users = this.userService.getAllUsers();
		theModel.addAttribute("users", users);

		return "user_management";
	}

	@GetMapping("/award_report")
	public String showAwardReportingPage(Model model) {
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

	// Brian's changes start here
	@PostMapping("/account/save_user")
	public String saveUser(@ModelAttribute("user") User user, @ModelAttribute("sig") String sig,
			@ModelAttribute("userProfile") UserProfile prof, @ModelAttribute("userProfileId") Long pid,
			@RequestParam("file") MultipartFile file, RedirectAttributes attr, Model theModel) {

		String uploadDirectory = context.getRealPath("/award_files");
		String f = file.getOriginalFilename();
		String fExt = f.replaceAll(".*\\.", "");

		if (fExt.equals("jpeg") || fExt.equals("jpg") || fExt.equals("png") || fExt.equals("bmp")
				|| fExt.equals("gif")) {
			user = userService.saveUser(user, "USER");
			String fileName = user.getId().toString() + "." + fExt;
			Path pathAndName = Paths.get(uploadDirectory, fileName);

			try {
				Files.write(pathAndName, file.getBytes());

			} catch (IOException e) {
				e.printStackTrace();
			}
			prof.setTargetFile(fileName);
			user.setUserProfile(prof);
			// if user already has a profile, delete the old one
			if (pid >= 0) {
				UserProfile oldProf = profileDAO.findById(pid);
				profileDAO.removeProfile(oldProf);
			}
			userService.saveUser(user, "USER");
			return "redirect:/admin/user_management";
		}
		// only enters if no sig file was included and if updating user,
		// due to sig != null condition, will not enter block on new user creation
		else if (f.equals("") && (!sig.equals(""))) {
			user.setUserProfile(prof);
			prof.setTargetFile(sig);
			UserProfile oldProf = profileDAO.findById(pid);
			profileDAO.removeProfile(oldProf);
			user = userService.saveUser(user, "USER");
			return "redirect:/admin/user_management";
		}
		// improper file format for new user or current user, or no file chosen
		else {

			if (!sig.equals("")) {
				long userId = user.getId();
				attr.addFlashAttribute("er", "Error: File must be an image");
				return "redirect:/admin/account/" + userId;
			}
			if (f.equals("") && (sig.equals(""))) {
				attr.addFlashAttribute("er", "Error: Please upload a signature image");
			} else {
				attr.addFlashAttribute("er", "Error: File must be an image");
			}
			return "redirect:/admin/account/add_user";
		}
	}
	// Brian's changes end here

	@GetMapping("/account/add_admin")
	public String addAdmin(Model theModel) {
		theModel.addAttribute("loggedInUser", userService.getLoggedInUser());
		theModel.addAttribute("account", null);
		return "admin_account";
	}

	@PostMapping("/account/save_admin")
	public String saveAdmin(@Valid @ModelAttribute("account") User account, 
			BindingResult bindingResult, Model theModel) {       		
		
		if (bindingResult.hasErrors()) {
			theModel.addAttribute("loggedInUser", userService.getLoggedInUser());
			return "admin_account";
		}
		else {
			userService.saveUser(account, "ADMIN");
			return "redirect:/admin/user_management";
		}				
	}

	@RequestMapping("/account/delete_account")
	public String deleteAccount(@RequestParam("id") Long id) {
		userService.deleteUserById(id);
		return "redirect:/admin/user_management";
	}

	@RequestMapping("/account/download_report")
	public void downloadReport(@RequestParam("label") String label, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// create CSV file based on label provided
		String filename = new String();
		String title = new String();
		Map<String, Integer> table = new HashMap<>();

		switch (label) {
		case "state": {
			for (Map.Entry<String, Map<String, Integer>> result : reportService.stateReport().entrySet()) {
				filename = result.getKey();
				table = result.getValue();
			}

			title = "STATE REPORT";
			break;
		}
		case "department": {
			for (Map.Entry<String, Map<String, Integer>> result : reportService.departmentReport().entrySet()) {
				filename = result.getKey();
				table = result.getValue();
			}

			title = "DEPARTMENT REPORT";
			break;
		}
		case "gender": {
			for (Map.Entry<String, Map<String, Integer>> result : reportService.genderReport().entrySet()) {
				filename = result.getKey();
				table = result.getValue();
			}

			title = "GENDER REPORT";
			break;
		}
		case "position": {
			for (Map.Entry<String, Map<String, Integer>> result : reportService.positionReport().entrySet()) {
				filename = result.getKey();
				table = result.getValue();
			}

			title = "POSITION REPORT";
			break;
		}
		case "awardType": {
			for (Map.Entry<String, Map<String, Integer>> result : reportService.awardTypeReport().entrySet()) {
				filename = result.getKey();
				table = result.getValue();
			}

			title = "AWARD TYPE REPORT";
			break;
		}
		}

		model.addAttribute("title", title);
		model.addAttribute("table", table);
		model.addAttribute("total", awardService.getAwards().size());

		// target the new CSV file
		String filePath = System.getProperty("user.dir") + "\\src\\main\\webapp\\report_files";
		Path file = Paths.get(filePath, filename);

		// type of download
		response.setContentType("text/csv");
		response.addHeader("Content-Disposition", "attachment; filename=" + filename);

		// download the file
		try {
			Files.copy(file, response.getOutputStream());
			response.getOutputStream().flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}


//package com.employee_recognition.Controller;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.employee_recognition.Entity.Report;
//import com.employee_recognition.Entity.User;
//import com.employee_recognition.Entity.UserProfile;
//import com.employee_recognition.Repository.UserProfileRepository;
//import com.employee_recognition.Service.AwardService;
//import com.employee_recognition.Service.ReportService;
//import com.employee_recognition.Service.UserService;
//
//@Controller
//@RequestMapping("/admin")
//@SessionAttributes({ "title", "table", "total" })
//public class AdminController {
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private ReportService reportService;
//
//	@Autowired
//	private AwardService awardService;
//
//	@Autowired
//	private ServletContext context;
//
//	@Autowired
//	private UserProfileRepository profileDAO;
//
//	public AdminController() {
//	}
//
//	@InitBinder
//	public void initBinder(WebDataBinder binder, WebRequest request) {
//		
//	}
//	
//	@GetMapping("/user_management")
//	public String showUserManagementPage(Model theModel) {
//
//		theModel.addAttribute("loggedInUser", userService.getLoggedInUser());
//
//		List<User> users = this.userService.getAllUsers();
//		theModel.addAttribute("users", users);
//
//		return "user_management";
//	}
//
//	@GetMapping("/award_report")
//	public String showAwardReportingPage(Model model) {
//		// Analytics Reports
//		List<Report> reportList = new ArrayList<>();
//		reportList.add(new Report("Gender Report", "gender"));
//		reportList.add(new Report("Department Report", "department"));
//		reportList.add(new Report("State Report", "state"));
//		reportList.add(new Report("Position Report", "position"));
//		reportList.add(new Report("Award Type Report", "awardType"));
//
//		model.addAttribute("report", reportList);
//		model.addAttribute("loggedInUser", userService.getLoggedInUser());
//
//		return "award_report";
//	}
//
//	@GetMapping("/account/{id}")
//	public String updateUser(@ModelAttribute("id") Long id, Model theModel) {
//
//		theModel.addAttribute("loggedInUser", userService.getLoggedInUser());
//
//		User user = userService.getUserById(id);
//		theModel.addAttribute("account", user);
//
//		if (user.getRole().getRole().compareTo("USER") == 0)
//			return "user_account";
//		else
//			return "admin_account";
//	}
//
//	@GetMapping("/account/add_user")
//	public String addUser(Model theModel) {
//		theModel.addAttribute("loggedInUser", userService.getLoggedInUser());
//		theModel.addAttribute("user", null);
//		return "user_account";
//	}
//
//	// Brian's changes start here
//	@PostMapping("/account/save_user")
//	public String saveUser(@ModelAttribute("user") User user, @ModelAttribute("sig") String sig,
//			@ModelAttribute("userProfile") UserProfile prof, @ModelAttribute("userProfileId") Long pid,
//			@RequestParam("file") MultipartFile file, RedirectAttributes attr, Model theModel) {
//
//		String uploadDirectory = context.getRealPath("/award_files");
//		String f = file.getOriginalFilename();
//		String fExt = f.replaceAll(".*\\.", "");
//
//		if (fExt.equals("jpeg") || fExt.equals("jpg") || fExt.equals("png") || fExt.equals("bmp")
//				|| fExt.equals("gif")) {
//			user = userService.saveUser(user, "USER");
//			String fileName = user.getId().toString() + "." + fExt;
//			Path pathAndName = Paths.get(uploadDirectory, fileName);
//
//			try {
//				Files.write(pathAndName, file.getBytes());
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			prof.setTargetFile(fileName);
//			user.setUserProfile(prof);
//			// if user already has a profile, delete the old one
//			if (pid >= 0) {
//				UserProfile oldProf = profileDAO.findById(pid);
//				profileDAO.removeProfile(oldProf);
//			}
//			userService.saveUser(user, "USER");
//			return "redirect:/admin/user_management";
//		}
//		// only enters if no sig file was included and if updating user,
//		// due to sig != null condition, will not enter block on new user creation
//		else if (f.equals("") && (!sig.equals(""))) {
//			user.setUserProfile(prof);
//			prof.setTargetFile(sig);
//			UserProfile oldProf = profileDAO.findById(pid);
//			profileDAO.removeProfile(oldProf);
//			user = userService.saveUser(user, "USER");
//			return "redirect:/admin/user_management";
//		}
//		// improper file format for new user or current user, or no file chosen
//		else {
//
//			if (!sig.equals("")) {
//				long userId = user.getId();
//				attr.addFlashAttribute("er", "Error: File must be an image");
//				return "redirect:/admin/account/" + userId;
//			}
//			if (f.equals("") && (sig.equals(""))) {
//				attr.addFlashAttribute("er", "Error: Please upload a signature image");
//			} else {
//				attr.addFlashAttribute("er", "Error: File must be an image");
//			}
//			return "redirect:/admin/account/add_user";
//		}
//	}
//	// Brian's changes end here
//
//	@GetMapping("/account/add_admin")
//	public String addAdmin(Model theModel) {
//		theModel.addAttribute("loggedInUser", userService.getLoggedInUser());
//		theModel.addAttribute("account", new User());
//		return "admin_account";
//	}
//
////	@PostMapping("/account/save_admin")
////	public String saveAdmin(@ModelAttribute("account") User account, Model theModel) {
////		userService.saveUser(account, "ADMIN");
////		return "redirect:/admin/user_management";
////	}
//
//	@PostMapping("/account/save_admin")
//	public String saveAdmin(@Valid @ModelAttribute("account") User account, 
//			BindingResult bindingResult, Model theModel) {       		
//		
//		theModel.addAttribute("loggedInUser", userService.getLoggedInUser());
//		
//		
//		if (bindingResult.hasErrors()) {		
//			theModel.addAttribute("user_account", account);
//			return "admin_account";
//		}
//		else {			
//			if (account == null ) {
//				System.out.println("account = NULL");
//			}
//			else {
//				System.out.println("account = " + account);
//			}
//			
//			userService.saveUser(account, "ADMIN");			
//			return "redirect:/admin/user_management";
//		}				
//	}
//	
//	@RequestMapping("/account/delete_account")
//	public String deleteAccount(@RequestParam("id") Long id) {
//		userService.deleteUserById(id);
//		return "redirect:/admin/user_management";
//	}
//
//	@RequestMapping("/account/download_report")
//	public void downloadReport(@RequestParam("label") String label, HttpServletRequest request,
//			HttpServletResponse response, Model model) {
//		// create CSV file based on label provided
//		String filename = new String();
//		String title = new String();
//		Map<String, Integer> table = new HashMap<>();
//
//		switch (label) {
//		case "state": {
//			for (Map.Entry<String, Map<String, Integer>> result : reportService.stateReport().entrySet()) {
//				filename = result.getKey();
//				table = result.getValue();
//			}
//
//			title = "STATE REPORT";
//			break;
//		}
//		case "department": {
//			for (Map.Entry<String, Map<String, Integer>> result : reportService.departmentReport().entrySet()) {
//				filename = result.getKey();
//				table = result.getValue();
//			}
//
//			title = "DEPARTMENT REPORT";
//			break;
//		}
//		case "gender": {
//			for (Map.Entry<String, Map<String, Integer>> result : reportService.genderReport().entrySet()) {
//				filename = result.getKey();
//				table = result.getValue();
//			}
//
//			title = "GENDER REPORT";
//			break;
//		}
//		case "position": {
//			for (Map.Entry<String, Map<String, Integer>> result : reportService.positionReport().entrySet()) {
//				filename = result.getKey();
//				table = result.getValue();
//			}
//
//			title = "POSITION REPORT";
//			break;
//		}
//		case "awardType": {
//			for (Map.Entry<String, Map<String, Integer>> result : reportService.awardTypeReport().entrySet()) {
//				filename = result.getKey();
//				table = result.getValue();
//			}
//
//			title = "AWARD TYPE REPORT";
//			break;
//		}
//		}
//
//		model.addAttribute("title", title);
//		model.addAttribute("table", table);
//		model.addAttribute("total", awardService.getAwards().size());
//
//		// target the new CSV file
//		String filePath = System.getProperty("user.dir") + "\\src\\main\\webapp\\report_files";
//		Path file = Paths.get(filePath, filename);
//
//		// type of download
//		response.setContentType("text/csv");
//		response.addHeader("Content-Disposition", "attachment; filename=" + filename);
//
//		// download the file
//		try {
//			Files.copy(file, response.getOutputStream());
//			response.getOutputStream().flush();
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}
//}
