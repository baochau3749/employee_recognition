package com.employee_recognition.Controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employee_recognition.Entity.DaysMonthsYears;
import com.employee_recognition.Entity.Department;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.EmployeeForm;
import com.employee_recognition.Entity.Position;
import com.employee_recognition.Entity.State;
import com.employee_recognition.Entity.StringDate;
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
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
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
	public String addEmployee(@ModelAttribute("employee")Employee empl) {	
		//System.out.println("printing id: " + emp.getId());
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
	public String updateEmployee(@ModelAttribute("employeeForm")EmployeeForm emplfr, BindingResult br,
			RedirectAttributes attr) {
		Employee empl = emplfr.getEmployee();
		StringDate sd = emplfr.getStringDate();
		if (empl.getFirstName().equals("") || empl.getLastName().equals("") || empl.getEmail().equals(""))
		{
			attr.addFlashAttribute("er", "Error: Please fill in all fields");
			return "redirect:/user/editEmployee?id=" + empl.getId();
		}

//		if (br.hasErrors()) {
//			attr.addFlashAttribute("er2", "Error: Please fill in a birth date with MM/dd/yyyy format. Ex: 08/20/1980");
//			return "redirect:/user/editEmployee?id=" + empl.getId();
//	    }
		String bd = "10/" + sd.getDay() + "/" + sd.getYear();
		try {
			empl.setBirthDate(bd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employeeDAO.save(empl);
		return "redirect:/user/employees";
	}

	@GetMapping("/editEmployee")
	public String employeeEdit(@RequestParam("id") Long id, Model model) {
		Employee curEmp = employeeDAO.findById(id);
		StringDate stringDate = new StringDate();
		EmployeeForm employeeForm = new EmployeeForm(curEmp, stringDate);
		DaysMonthsYears dmy = new DaysMonthsYears();
		states = employeeDAO.getStateList();
		departments = employeeDAO.getDepartmentList();
		positions = employeeDAO.getPositionList();
		ArrayList<State> sList = employeeDAO.createStateList(states);
		ArrayList<Department> dList = employeeDAO.createDepartmentList(departments);
		ArrayList<Position> pList = employeeDAO.createPositionList(positions);
		model.addAttribute("dmy", dmy);
		model.addAttribute("employeeForm", employeeForm);
		model.addAttribute("states", sList);
		model.addAttribute("positions", pList);
		model.addAttribute("departments", dList);
		return "employee_update";
	}

}
