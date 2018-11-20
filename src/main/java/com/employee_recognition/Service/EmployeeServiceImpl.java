package com.employee_recognition.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_recognition.Entity.Department;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.Position;
import com.employee_recognition.Entity.State;
import com.employee_recognition.Repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee getEmployeeById(Long id)
	{
		return employeeRepository.findById(id);
	}

	@Override
	public State getStateById(int id)
	{
		return employeeRepository.findStateById(id);
	}

	@Override
	public Department getDepartmentById(int id)
	{
		return employeeRepository.findDepartmentById(id);
	}

	@Override
	public Position getPositionById(int id)
	{
		return employeeRepository.findPositionById(id);
	}

}
