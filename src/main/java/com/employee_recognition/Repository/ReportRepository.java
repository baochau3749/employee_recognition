package com.employee_recognition.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee_recognition.Entity.Award;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.State;
import com.employee_recognition.Service.AwardService;
import com.employee_recognition.Service.EmployeeService;

@Repository
public class ReportRepository
{
	@Autowired
	private AwardService awardDAO;

	@Autowired
	private EmployeeService employeeDAO;

	// get all awarded employees
	public List<Employee> getEmployees()
	{
		// get all the awards
		List<Award> awardList = awardDAO.getAwards();

		// list of employees with awards
		List<Employee> employeeList = new ArrayList<>();

		// collecting the employees which have received an award
		for (Award current : awardList)
		{
			employeeList.add(employeeDAO.getEmployeeById(current.getEmployee()));
		}

		return employeeList;
	}

	// generate csv file
	public void generateFile(Map<String, Integer> table, String fileName, String title)
	{
		String filePath = System.getProperty("user.dir") + "\\src\\main\\webapp\\report_files\\";
		try (PrintWriter output = new PrintWriter(new File(filePath + fileName)))
		{
			output.println(title + ",AWARD_COUNT");
			for (Map.Entry<String, Integer> current : table.entrySet())
			{
				output.println(current.getKey() + "," + current.getValue());
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	// create the state report
	public String stateReport()
	{
		// table for generating the state report
		Map<String, Integer> table = new HashMap<>();

		// analyzing the content of each employee that received an award
		for (Employee current : getEmployees())
		{
			String currentState = employeeDAO.getStateById(current.getState()).getState();

			if (table.containsKey(currentState))
			{
				table.replace(currentState, table.get(currentState) + 1);
			} else
			{
				table.put(currentState, 1);
			}
		}

		// creating the csv file
		String fileName = "state_report.csv";
		generateFile(table, fileName, "STATE");

		return fileName;
	}

	// create the department report
	public String departmentReport()
	{
		// table for generating the department report
		Map<String, Integer> table = new HashMap<>();

		// analyzing the content of each employee that received an award
		for (Employee current : getEmployees())
		{
			String currentDepartment = employeeDAO.getDepartmentById(current.getDepartment()).getDepartment();

			if (table.containsKey(currentDepartment))
			{
				table.replace(currentDepartment, table.get(currentDepartment) + 1);
			} else
			{
				table.put(currentDepartment, 1);
			}
		}

		// creating the csv file
		String fileName = "department_report.csv";

		generateFile(table, fileName, "DEPARTMENT");

		return fileName;
	}

	// create the position report
	public String positionReport()
	{
		// table for generating the position report
		Map<String, Integer> table = new HashMap<>();

		// analyzing the content of each employee that received an award
		for (Employee current : getEmployees())
		{
			String currentPosition = employeeDAO.getPositionById(current.getPosition()).getPosition();

			if (table.containsKey(currentPosition))
			{
				table.replace(currentPosition, table.get(currentPosition) + 1);
			} else
			{
				table.put(currentPosition, 1);
			}
		}

		// creating the csv file
		String fileName = "position_report.csv";

		generateFile(table, fileName, "POSITION");

		return fileName;
	}

	// create the gender report
	public String genderReport()
	{
		// table for generating the position report
		Map<String, Integer> table = new HashMap<>();

		// analyzing the content of each employee that received an award
		for (Employee current : getEmployees())
		{
			String currentGender = current.getGender();

			if (table.containsKey(currentGender))
			{
				table.replace(currentGender, table.get(currentGender) + 1);
			} else
			{
				table.put(currentGender, 1);
			}
		}

		// creating the csv file
		String fileName = "gender_report.csv";

		generateFile(table, fileName, "GENDER");

		return fileName;
	}

	// WORK IN PROGRESS FIX AFTER MERGING EVERYTHING BACK
	// create the award type report
	/*public String awardTypeReport()
	{
		// table for generating the award type report
		Map<Long, Integer> table = new HashMap<>();

		// analyzing the content of each employee that received an award
		for (Award current : awardDAO.getAwards())
		{
			Long currentAward = current.getAwardType();

			if (table.containsKey(currentAward))
			{
				table.replace(currentAward, table.get(currentAward) + 1);
			} else
			{
				table.put(currentAward, 1);
			}
		}

		// creating the csv file
		String fileName = "awardType_report.csv";

		generateFile(table, fileName, "AWARD TYPE");

		return fileName;
	}*/
}
