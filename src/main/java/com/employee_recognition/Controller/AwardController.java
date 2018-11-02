package com.employee_recognition.Controller;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.ClassEditor;
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
import com.employee_recognition.Entity.Department;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.Position;
import com.employee_recognition.Entity.State;
import com.employee_recognition.Entity.User;
import com.employee_recognition.Repository.AwardTypeRepository;
import com.employee_recognition.Repository.EmployeeRepository;
import com.employee_recognition.Service.AwardService;
import com.employee_recognition.Service.UserService;

@Controller
@SessionAttributes({"userID","user"})
public class AwardController {

	@Autowired
	private EmployeeRepository employeeDAO;
	
	@Autowired
	private AwardTypeRepository awardTypeDAO;
	
	@Autowired
	private UserService userDAO;
	
	@Autowired
	private AwardService awardDAO;
	
	private Award award = new Award();
	private AwardType at = new AwardType();
	private List<AwardType> awardTypes;
	private List<Employee> employees;
	
	private Employee emp = new Employee();
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {

	        binder.registerCustomEditor(AwardType.class, "awardType", new PropertyEditorSupport() {
	         @Override
	         public void setAsText(String text) {
	            setValue((text.equals(""))?null:awardTypeDAO.findById((Long.parseLong((String) text))));
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
	
/*	@GetMapping("/employee")
	public String employeePage(Model model) {
		states = employeeDAO.getStateList();
		departments = employeeDAO.getDepartmentList();
		positions = employeeDAO.getPositionList();
		ArrayList<State> sList = employeeDAO.createStateList(states);
		ArrayList<Department> dList = employeeDAO.createDepartmentList(departments);
		ArrayList<Position> pList = employeeDAO.createPositionList(positions);
		model.addAttribute("employee", emp);
		model.addAttribute("states", sList);
		model.addAttribute("positions", pList);
		model.addAttribute("departments", dList);
		return "employee";
	}*/
	
	@PostMapping("/createAward")
	public String saveAward(@ModelAttribute("award")Award award) {	
		awardDAO.saveAward(award);
		return "redirect:/award";
	}
	
}
