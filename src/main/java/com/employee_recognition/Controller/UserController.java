package com.employee_recognition.Controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

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
@SessionAttributes({"userID","user"})
public class UserController {

	@Autowired
	private UserService userDAO;
	
	@Autowired
	private EmployeeRepository employeeDAO;
	
	@Autowired
	private AwardService awardDAO;
	
	 @Autowired
     private ServletContext context;
	 
	 AWSCredentials cred = new BasicAWSCredentials(
			 "AKIAITT4IL4UYY34C3OQ",
			 "U7VB7Ijj+E+0LeaLRt75WbL8TK5KSXs97Iy7GxTJ"
	 );
	
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
	public String saveUserPage(@ModelAttribute("user") User user, @RequestParam("file") 
	MultipartFile file, Model model)
	{
		String uploadDirectory = context.getRealPath("/signature_files");
		System.out.println(uploadDirectory);
		System.out.println("upload directory is  " + uploadDirectory);

		String f = file.getOriginalFilename();
		String fExt = f.replaceAll(".*\\.", "");

		if (fExt.equals("jpeg") || fExt.equals("jpg") || fExt.equals("png") || fExt.equals("bmp") || fExt.equals("gif")){
			UserProfile profile = user.getUserProfile();
			String fileName = user.getId().toString() + "." + fExt;

			Path pathAndName = Paths.get(uploadDirectory, fileName);
			profile.setTargetFile(fileName);
			try {
				Files.write(pathAndName, file.getBytes());

			} catch (IOException e) {
				e.printStackTrace();
			}
			user = userDAO.saveUser(user,"USER");
			uploadaws(fileName);
			return "redirect:/user"; 
		}
		else if (f.equals("")){
			user = userDAO.saveUser(user,"USER");
			return "redirect:/user"; 
		}
		else {
			user = userDAO.saveUser(user,"USER");
			
			model.addAttribute("user", user);
			
			model.addAttribute("er", "Error: File must be an image");
			return "user_update";
		}

	}
	
	@RequestMapping(value = "/image")
    @ResponseBody
    public byte[] getImage(@ModelAttribute("user") User user) throws IOException {
		String uploadDirectory = context.getRealPath("/signature_files");
		System.out.println("up " + uploadDirectory);
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
	
	public void uploadaws(String fileName) {
		final AmazonS3 s3 = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(cred))
				.withRegion(Regions.US_EAST_2)
				.build();
		String uploadDirectory = context.getRealPath("/signature_files");
		s3.putObject(
				"empb",
				fileName,
				new java.io.File(uploadDirectory + "/" + fileName)
		);
		
	}
}
