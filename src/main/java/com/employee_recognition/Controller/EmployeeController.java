package com.employee_recognition.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/addEmployee")
	public String addEmployee(@ModelAttribute("employee")Employee emp) {	
		employeeDAO.save(emp);
		return "redirect:/user/employees";
	}
	
	// Deleting an Award
		@RequestMapping(value="/delete_employee")
		public String deleteEmployee(@RequestParam("id") Long id)
		{
			employeeDAO.deleteByID(id);
			return "redirect:/user/employees";
		}
	
}
