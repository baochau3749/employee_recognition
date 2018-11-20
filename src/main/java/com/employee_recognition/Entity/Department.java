package com.employee_recognition.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="department")
public class Department {
	@Id
	@Column(name = "department_id")
	private int department_id;
	@Column(name = "department")
	private String department;
	
	public Department() {}
	
	public Department(String d, int i) {
		department_id = i;
		department = d;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
}
