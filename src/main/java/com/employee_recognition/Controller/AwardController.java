package com.employee_recognition.Controller;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.employee_recognition.Entity.Award;
import com.employee_recognition.Entity.AwardType;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.User;
import com.employee_recognition.Repository.AwardTypeRepository;
import com.employee_recognition.Repository.EmployeeRepository;
import com.employee_recognition.Service.AwardService;
import com.employee_recognition.Service.LatexService;
import com.employee_recognition.Service.UserService;

@Controller
@SessionAttributes({"userID","user"})
@RequestMapping("/user")
public class AwardController {

	@Autowired
	private EmployeeRepository employeeDAO;
	
	@Autowired
	private AwardTypeRepository awardTypeDAO;
	
	@Autowired
	private UserService userDAO;
	
	@Autowired
	private AwardService awardDAO;
	
	@Autowired
	private LatexService latexService;
	
	private Award award = new Award();
	private AwardType at = new AwardType();

	private Timestamp formDate;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {

	        binder.registerCustomEditor(AwardType.class, "awardType", new PropertyEditorSupport() {
	         @Override
	         public void setAsText(String type) {
	            setValue((awardTypeDAO.findById(Long.parseLong((String) type))));
	         }
	     });
	        
	        binder.registerCustomEditor(Timestamp.class, "dateGiven", new PropertyEditorSupport() {
		         @Override
		         public void setAsText(String date) {
		        	 if (date.equals("")) {
		        		 formDate = new Timestamp(System.currentTimeMillis());
		        	 }
		        	 else {
		        		 SimpleDateFormat dateFormatObj = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
		        		 java.util.Date parsedDate = null;
						try {
							parsedDate = dateFormatObj.parse(date);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        		 formDate = new Timestamp(parsedDate.getTime());
		        	 }
		        	 	setValue(formDate);
		         }
		     });
	}
	
	@GetMapping("/award")
	public String employeeMainPage(@SessionAttribute("userID") Long userID, Model model) {
		User currentUser = userDAO.getUserById(userID); 
		award.setUser(currentUser);
		model.addAttribute("user", currentUser);
		//generate award list
		award.setAwardType(at);
		model.addAttribute("awardTypes", awardTypeDAO.getAwardTypeList());
		//generate employee list
		model.addAttribute("employees", employeeDAO.getEmployees());
		//add award
		model.addAttribute(award);
		return "award";
	}
	
	
	@PostMapping("/createAward")
	public String saveAward(@ModelAttribute("award")Award award, @SessionAttribute("userID") Long userId) {	
		User currentUser = userDAO.getUserById(userId); 
		award.setUser(currentUser);
		awardDAO.saveAward(award);
		
		latexService.CreateAwardFile(award);		
		
		return "redirect:";
	}
	
}
