package com.employee_recognition.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_recognition.Latex.LatexContent;
import com.employee_recognition.Entity.Award;
import com.employee_recognition.Entity.AwardType;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.User;
import com.employee_recognition.Repository.EmployeeRepository;

@Service
public class LatexServiceImpl implements LatexService {
	@Autowired
	private EmployeeRepository employeeDAO;

	@Autowired
	LatexContent latexContent;

	@Autowired
	private ServletContext context;

	@Override
	public File CreateAwardFile(Award award) {
		String mainDirectory = context.getRealPath("/");
		
		User currentUser = award.getUser();
		Employee employee = employeeDAO.findById(award.getEmployee());
		AwardType awardType = award.getAwardType();

		String awardDate = new SimpleDateFormat("MM/dd/yyyy").format(award.getdateGiven());
		String employeeName = employee.getFirstName() + " " + employee.getLastName();
		String userName = currentUser.getUserProfile().getFirstName() + " "
				+ currentUser.getUserProfile().getLastName();

		latexContent.setTitle(awardType.getType());
		latexContent.setName(employeeName);
		latexContent.setDate(awardDate);
		latexContent.setAwarder(userName);

		// File latexFile = latexContent.createLatexFile();
		latexContent.createLatexFile();
		
		try {
			Process p = Runtime.getRuntime().exec(mainDirectory + "/latex_compiler");
			p.waitFor();
			
			String s = null;
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			System.out.println("Result Status:");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			System.out.println("Result error:");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}
			
		} catch (InterruptedException | IOException e) {
			throw new RuntimeException("There's an error in creating award file.");
		}
		return null;
	}

}