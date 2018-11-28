package com.employee_recognition.Controller;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.employee_recognition.Entity.User;
import com.employee_recognition.Repository.AwardTypeRepository;
import com.employee_recognition.Repository.EmployeeRepository;
import com.employee_recognition.Service.AwardService;
import com.employee_recognition.Service.LatexService;
import com.employee_recognition.Service.UserService;

@Controller
@SessionAttributes({ "userID", "user" })
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

	private String patterns[] = {"MM/dd/yyyy hh:mm aa", "MM/dd/yyyy hh:mm", "MM/dd/yyyy"};
	private static String errorMsg = "Invalid date and time. Please enter date " +
			"in one of these format: \"MM/dd/yyyy hh:mm am/pm\", " +
			"\"MM/dd/yyyy hh:mm\", or \"MM/dd/yyyy\".";
	
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
				Timestamp formDate = null;
			
				if (date.equals("")) {
					formDate = new Timestamp(System.currentTimeMillis());
				} else {
					for (int i = 0; i < patterns.length; i++) {						
						SimpleDateFormat dateFormatObj = new SimpleDateFormat(patterns[i]);
						try {
							java.util.Date parsedDate = dateFormatObj.parse(date);
							formDate = new Timestamp(parsedDate.getTime());
							break;
						} catch (ParseException e) {
							formDate = null;
						}
					}					
				}
				setValue(formDate);
			}
		});
	}

	@GetMapping("/award")
	public String employeeMainPage(@SessionAttribute("userID") Long userID, Model model) {
		Award award = new Award();
		AwardType at = new AwardType();
		
//		User currentUser = userDAO.getUserById(userID);
//		award.setUser(currentUser);
//		model.addAttribute("user", currentUser);
		
		// generate award list
		award.setAwardType(at);
		model.addAttribute("awardTypes", awardTypeDAO.getAwardTypeList());
		
		// generate employee list
		model.addAttribute("employees", employeeDAO.getEmployees());	
		
		// add award
		model.addAttribute("award", award);
		return "award";
	}
	
	@PostMapping("/createAward")
	public String saveAward(@ModelAttribute("dateGiven") String dateGiven,
			@ModelAttribute("award") Award award, 
			BindingResult bindingResult, 
			@SessionAttribute("userID") Long userId, Model model) {
					
		if (award.getdateGiven() == null) {
			model.addAttribute("awardTypes", awardTypeDAO.getAwardTypeList());
			model.addAttribute("employees", employeeDAO.getEmployees());
			model.addAttribute("dateGiven", dateGiven);			
			model.addAttribute("award", award);
			bindingResult.rejectValue("dateGiven", "dateGiven", errorMsg);
			return "award";
		}
		else {
			User currentUser = userDAO.getUserById(userId);
			award.setUser(currentUser);
			awardDAO.saveAward(award);
			
			String awardFile = latexService.CreateAwardFile(award);

			try {
				userDAO.sendEmailAward(awardFile, award, currentUser);
			} catch (EmailException e) {
				e.printStackTrace();
			}
			
			return "redirect:";
		}
	}

}
