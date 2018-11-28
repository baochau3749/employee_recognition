package com.employee_recognition.Controller;

import java.awt.Component;
import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employee_recognition.Entity.Award;
import com.employee_recognition.Entity.AwardType;
import com.employee_recognition.Entity.Department;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.Position;
import com.employee_recognition.Entity.State;
import com.employee_recognition.Repository.EmployeeRepository;

@Controller
@RequestMapping("/user")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeDAO;
	
	private List<Employee> employees;
	private Employee emp = new Employee();
	private ArrayList<String> states;
	private ArrayList<String> departments;
	private ArrayList<String> positions;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");		
		binder.registerCustomEditor(Date.class, "birthDate", new CustomDateEditor(format, true));				    
	}
	
	@GetMapping("/employees")
	public String employeeMainPage(Model model) {
		employees = employeeDAO.getEmployees();
		model.addAttribute("employees", employees);
		return "employee_management";
	}
	
	@GetMapping("/employee")
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
	}
	
	@PostMapping("/addEmployee")
	public String addEmployee(@Valid @ModelAttribute("employee") Employee empl,
			BindingResult bindingResult, 
			@SessionAttribute("userID") Long userId, Model model) {	
								
		if (bindingResult.hasErrors()) {
			states = employeeDAO.getStateList();
			departments = employeeDAO.getDepartmentList();
			positions = employeeDAO.getPositionList();
			ArrayList<State> sList = employeeDAO.createStateList(states);
			ArrayList<Department> dList = employeeDAO.createDepartmentList(departments);
			ArrayList<Position> pList = employeeDAO.createPositionList(positions);
			model.addAttribute("employee", empl);
			model.addAttribute("states", sList);
			model.addAttribute("positions", pList);
			model.addAttribute("departments", dList);
			return "employee";
		}
		
		empl = employeeDAO.save(empl);
		return "redirect:/user/employees";
	}
	
	
	// Brian's changes start here

	// Deleting an employee
	@RequestMapping(value = "/delete_employee")
	public String deleteEmployee(@RequestParam("id") Long id) {
		employeeDAO.deleteByID(id);
		return "redirect:/user/employees";
	}

	// Deleting an employee
	@RequestMapping(value = "/update_employee")
	public String updateEmployee(@Valid @ModelAttribute("employee") Employee empl,
			BindingResult bindingResult, 
			@SessionAttribute("userID") Long userId, Model model) {

		if (bindingResult.hasErrors()) {
			states = employeeDAO.getStateList();
			departments = employeeDAO.getDepartmentList();
			positions = employeeDAO.getPositionList();
			ArrayList<State> sList = employeeDAO.createStateList(states);
			ArrayList<Department> dList = employeeDAO.createDepartmentList(departments);
			ArrayList<Position> pList = employeeDAO.createPositionList(positions);
			model.addAttribute("employee", empl);
			model.addAttribute("states", sList);
			model.addAttribute("positions", pList);
			model.addAttribute("departments", dList);
			return "employee_update";
		}
		
		employeeDAO.save(empl);
		return "redirect:/user/employees";
	}

	@GetMapping("/editEmployee")
	public String employeeEdit(@RequestParam("id") Long id, Model model) {
		Employee curEmp = employeeDAO.findById(id);
		states = employeeDAO.getStateList();
		departments = employeeDAO.getDepartmentList();
		positions = employeeDAO.getPositionList();
		ArrayList<State> sList = employeeDAO.createStateList(states);
		ArrayList<Department> dList = employeeDAO.createDepartmentList(departments);
		ArrayList<Position> pList = employeeDAO.createPositionList(positions);
		model.addAttribute("employee", curEmp);
		model.addAttribute("states", sList);
		model.addAttribute("positions", pList);
		model.addAttribute("departments", dList);
		return "employee_update";
	}

}
