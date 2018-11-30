package com.employee_recognition.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeForm {
	@Autowired
	private Employee employee;
	@Autowired
	private StringDate stringDate;
	
	public EmployeeForm(Employee employee, StringDate stringDate) {
		this.employee = employee;
		this.stringDate = stringDate;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	@Autowired
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public StringDate getStringDate() {
		return stringDate;
	}

	public void setStringDate(StringDate stringDate) {
		this.stringDate = stringDate;
	}

	
	
}
