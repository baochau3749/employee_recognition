package com.employee_recognition.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.employee_recognition.Entity.Award;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.User;
import com.employee_recognition.Entity.UserProfile;
import com.employee_recognition.Repository.EmployeeRepository;
import com.employee_recognition.Service.AwardService;
import com.employee_recognition.Service.UserService;

@Controller
@SessionAttributes({"userId","user"})
public class UserController {

	@Autowired
	private UserService userDAO;
	
	@Autowired
	private EmployeeRepository employeeDAO;
	
	@Autowired
	private AwardService awardDAO;
	
	 @Autowired
     private ServletContext context;
	 	
	// User Main Page
	@GetMapping("/user")
	public String userMainPage(@SessionAttribute("userId") Long userId, Model model) {
		List<Award> awards;
		List<String> employees = new ArrayList<String>();
		List<String> emails = new ArrayList<String>();
		User currentUser = userDAO.getUserById(userId); 
		awards = currentUser.getUserAwards();

		for (int i = 0; i < awards.size(); i++) {
			Long empId = awards.get(i).getEmployee();		
			Employee employee = employeeDAO.findById(empId);
			employees.add(employee.getFullName());
			emails.add(employee.getEmail());
		}
		
		model.addAttribute("user", currentUser);
		model.addAttribute("awards", currentUser.getUserAwards());
		model.addAttribute("employees", employees);
		model.addAttribute("emails", emails);
		
		return "user";
	}
	
	// User Update Page GET
	@RequestMapping(value = "/user/{id}", method=RequestMethod.GET )
	public String updateUserPage(@PathVariable("id") Long id, Model model)
	{
		User temp = userDAO.getUserById(id);		
		model.addAttribute("user", temp);		
		return "user_update";
	}
	
	// User Update Page POST
	@RequestMapping(value = "/user/{id}", method=RequestMethod.POST)
	public String saveUserPage(@PathVariable("id") Long id, 
				@Valid @ModelAttribute("user") User user,
				BindingResult bindingResult,
				@RequestParam("file") MultipartFile file, Model model) 
	{
		
		String uploadDirectory = context.getRealPath("/award_files");
		String newFileName = file.getOriginalFilename();
		String fExt = newFileName.replaceAll(".*\\.", "");
			
		// Additional custom validations.
		user.validate(bindingResult, userDAO);		
		user.getUserProfile().validate(bindingResult, newFileName, "userProfile");
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("user", user);
			return "user_update";
		}
		
		if (fExt.equals("jpeg") || fExt.equals("jpg") || fExt.equals("png")) {				
			try {
				String fileName = user.getUserId().toString() + "." + fExt;
				Path pathAndName = Paths.get(uploadDirectory, fileName);
				
				Files.write(pathAndName, file.getBytes());
				user.getUserProfile().setTargetFile(fileName);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		user = userDAO.saveUser(user,"USER");
		return "redirect:/user"; 
	}
	
	@RequestMapping(value = "/image")
    @ResponseBody
    public byte[] getImage(@ModelAttribute("user") User user) throws IOException {
		String uploadDirectory = context.getRealPath("/award_files");
		java.io.File serverFile = new java.io.File(uploadDirectory + "/" + user.getUserProfile().getTargetFile());

        return Files.readAllBytes(serverFile.toPath());
    }
	
	// Deleting an Award
	@RequestMapping(value="/delete_award")
	public String deleteAward(@RequestParam("id") Long id)
	{
		awardDAO.deleteAwardByID(id);
		return "redirect:/user";
	}
}
