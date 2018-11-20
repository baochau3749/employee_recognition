package com.employee_recognition.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.jni.File;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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
		//System.out.println(uploadDirectory);
		//System.out.println(user.toString());
		//System.out.println(file.getContentType());
		//System.out.println("printing test " + file.getOriginalFilename().replaceAll(".*\\.", ""));
		//System.out.println(file.getOriginalFilename());
		//System.out.println("upload directory is  " + uploadDirectory);
		
		//System.out.println("path is  r " + pathc);
		String f = file.getOriginalFilename();
		//replaces everything before the "."
		//System.out.println("f is " + f + (f.equals("")));
		
		//get extension
		String fExt = f.replaceAll(".*\\.", "");
		//System.out.println(fExt);
		if (fExt.equals("jpeg") || fExt.equals("png") || fExt.equals("bmp") || fExt.equals("gif")){
			UserProfile profile = user.getUserProfile();
			String fileName = user.getId().toString() + "." + fExt;
			//System.out.println("filename is " + fileName);
			Path pathAndName = Paths.get(uploadDirectory, fileName);
			profile.setTargetFile(fileName);
			try {
				Files.write(pathAndName, file.getBytes());
				//FileCopyUtils.copy(file.getBytes(), new java.io.File(uploadDirectory));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user = userDAO.saveUser(user,"USER");
			return "redirect:/user"; 
		}
		else if (f.equals("")){
			user = userDAO.saveUser(user,"USER");
			return "redirect:/user"; 
		}
		else {
			String er = "improper file format";
			user = userDAO.saveUser(user,"USER");
			
			model.addAttribute("user", user);
			//java.io.File fa = new java.io.File(uploadDirectory);
			//fa.getAbsolutePath();
			model.addAttribute("er", er);
			return "user_update";
		}

	}
	
	@RequestMapping(value = "/image")
    @ResponseBody
    public byte[] getImage(@ModelAttribute("user") User user) throws IOException {
		
		String uploadDirectory = context.getRealPath("/");
		System.out.println("up " + uploadDirectory);
		//java.io.File serverFile = new java.io.File(uploadDirectory + "/signature_files/" + user.getUserProfile().getTargetFile());
		java.io.File serverFile = new java.io.File(uploadDirectory + "/signature_files/1.txt");

		System.out.println("uploadDirectory = " + uploadDirectory);
		System.out.println("serverFile.getPath() = " + serverFile.getPath());
		
		return Files.readAllBytes(serverFile.toPath());
    }
	
	// Deleting an Award
	@RequestMapping(value="/delete_award")
	public String deleteAward(@RequestParam("id") Long id)
	{
		awardDAO.deleteAwardByID(id);
		return "redirect:/user";
	}	
	
	// show an Award
	@RequestMapping(value="/user/show_award")
	public void showAward(HttpServletResponse response)
	{
		String mainDirectory = context.getRealPath("/");
		
		java.io.File awardFile = new java.io.File(mainDirectory + "/award_files/award.pdf");
		System.out.println("awardFile.getPath() = " + awardFile.getPath());

		try {
			// get your file as InputStream
			InputStream targetStream = new FileInputStream(awardFile);

			// copy it to response's OutputStream
			IOUtils.copy(targetStream, response.getOutputStream());
			response.flushBuffer();

		} 
		catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		}

	}	
}













//@RequestMapping(value = "/awardImage")
//public void getAwardImage(HttpServletResponse response) throws IOException {
//	String mainDirectory = context.getRealPath("/");
//	
//	java.io.File signatureFile = new java.io.File(mainDirectory + "/signature_files/1.png");
//	java.io.File awardFile = new java.io.File(mainDirectory + "/award_files/1.png");
//
//	System.out.println("signatureFile.getPath() = " + signatureFile.getPath());
//	System.out.println("awardFile.getPath() = " + awardFile.getPath());
//	
////	Process p, pa;
////	String s = null;
////	
////	pa = Runtime.getRuntime().exec("ls");
////	//p.waitFor();
////	BufferedReader stdInput = new BufferedReader(new InputStreamReader(pa.getInputStream()));		
////	BufferedReader stdError = new BufferedReader(new InputStreamReader(pa.getErrorStream()));
////	
////	
////	System.out.println("Result Status:");
////  while ((s = stdInput.readLine()) != null) { System.out.println(s); }     
////  
////  System.out.println("Result error:");
////  while ((s = stdError.readLine()) != null) { System.out.println(s); }
////  
////	p = Runtime.getRuntime().exec(uploadDirectory + "/latex_compiler");
////	//p.waitFor();
////	stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));		
////	stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
////			
////	System.out.println("Result Status:");
////  while ((s = stdInput.readLine()) != null) { System.out.println(s); }   
////  
////  System.out.println("Result error:");
////  while ((s = stdError.readLine()) != null) { System.out.println(s); }
////  
////  
////  
////	p = Runtime.getRuntime().exec("ls -l " + uploadDirectory + "/signature_files");
////	//p.waitFor();
////	stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));		
////	stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
////			
////	System.out.println("Result Status:");
////  while ((s = stdInput.readLine()) != null) { System.out.println(s); }   
////  
////  System.out.println("Result error:");
////  while ((s = stdError.readLine()) != null) { System.out.println(s); }
////  
//	
//	
//	
//			
//  try {
//    // get your file as InputStream
//    InputStream targetStream = new FileInputStream(signatureFile);  
//	      
//    // copy it to response's OutputStream
//    IOUtils.copy(targetStream, response.getOutputStream());
//    response.flushBuffer();	       
//    
//  } catch (IOException ex) {
//  	throw new RuntimeException("IOError writing file to output stream");
//  }
//}
