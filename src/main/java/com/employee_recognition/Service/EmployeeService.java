package com.employee_recognition.Service;

import com.employee_recognition.Entity.Department;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.Position;
import com.employee_recognition.Entity.State;

public interface EmployeeService
{
	public Employee getEmployeeById(Long id);
	public State getStateById(int id);
	public Department getDepartmentById(int id);
	public Position getPositionById(int id);
}
