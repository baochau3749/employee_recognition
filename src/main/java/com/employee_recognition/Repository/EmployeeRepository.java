package com.employee_recognition.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employee_recognition.Entity.Department;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.Position;
import com.employee_recognition.Entity.State;

@Repository
@Transactional
public class EmployeeRepository {

	@Autowired
	private EntityManager entityManager;

	public Employee findById(Long id) {
		return entityManager.find(Employee.class, id);
	}

	// state functions
	public State findStateById(int id) {
		return entityManager.find(State.class, id);
	}

	public int findStateByName(String state) {
		String queryStr = "SELECT state_id FROM state WHERE state='" + state + "'";
		Query q = entityManager.createNativeQuery(queryStr);
		List<?> resultList = q.getResultList();
		Integer id = (Integer) resultList.get(0);

		return id;
	}

	public ArrayList<String> getStateList() {
		String queryStr = "SELECT state FROM state";
		Query q = entityManager.createNativeQuery(queryStr);
		List<?> resultList = q.getResultList();
		int size = resultList.size();
		ArrayList<String> sList = new ArrayList<String>();

		for (int i = 0; i < size; i++) {
			String s = (String) resultList.get(i);
			sList.add(s);
		}

		return (sList.size() > 0) ? sList : null;

	}

	public ArrayList<State> createStateList(ArrayList<String> states) {
		ArrayList<State> sList = new ArrayList<State>();
		for (int i = 0; i < states.size(); i++) {
			int id = findStateByName(states.get(i));
			State s = new State(states.get(i), id);
			// System.out.println(id);
			sList.add(s);
		}
		return sList;
	}

	// position functions
	public Position findPositionById(int id) {
		return entityManager.find(Position.class, id);
	}

	public int findPositionByName(String position) {
		String queryStr = "SELECT position_id FROM position WHERE position='" + position + "'";
		Query q = entityManager.createNativeQuery(queryStr);
		List<?> resultList = q.getResultList();

		Integer id = (Integer) resultList.get(0);

		return id;
	}

	public ArrayList<String> getPositionList() {
		String queryStr = "SELECT position FROM position";
		Query q = entityManager.createNativeQuery(queryStr);
		List<?> resultList = q.getResultList();
		int size = resultList.size();
		ArrayList<String> sList = new ArrayList<String>();

		for (int i = 0; i < size; i++) {
			String s = (String) resultList.get(i);
			sList.add(s);
		}

		return (sList.size() > 0) ? sList : null;
	}

	public ArrayList<Position> createPositionList(ArrayList<String> positions) {
		ArrayList<Position> sList = new ArrayList<Position>();
		for (int i = 0; i < positions.size(); i++) {
			int id = findPositionByName(positions.get(i));
			Position s = new Position(positions.get(i), id);
			// System.out.println(id);
			sList.add(s);
		}
		return sList;
	}

	// Delete Award by ID
	public void deleteByID(Long id) {
		String awardQuery = "DELETE FROM award WHERE employee_id=:employeeID";
		Query awQuery = entityManager.createNativeQuery(awardQuery);
		awQuery.setParameter("employeeID", id);
		awQuery.executeUpdate();
		String queryString = "DELETE FROM employee WHERE employee_id=:employeeID";
		Query query = entityManager.createNativeQuery(queryString);
		query.setParameter("employeeID", id);
		query.executeUpdate();
	}

	// department functions
	public Department findDepartmentById(int id) {
		return entityManager.find(Department.class, id);
	}

	public int findDepartmentByName(String department) {
		String queryStr = "SELECT department_id FROM department WHERE department='" + department + "'";
		Query q = entityManager.createNativeQuery(queryStr);
		List<?> resultList = q.getResultList();

		Integer id = (Integer) resultList.get(0);

		return id;
	}

	public ArrayList<String> getDepartmentList() {
		String queryStr = "SELECT department FROM department";
		Query q = entityManager.createNativeQuery(queryStr);
		List<?> resultList = q.getResultList();
		int size = resultList.size();
		ArrayList<String> sList = new ArrayList<String>();

		for (int i = 0; i < size; i++) {
			String s = (String) resultList.get(i);
			sList.add(s);
		}

		return (sList.size() > 0) ? sList : null;
	}

	public ArrayList<Department> createDepartmentList(ArrayList<String> departments) {
		ArrayList<Department> sList = new ArrayList<Department>();
		for (int i = 0; i < departments.size(); i++) {
			int id = findDepartmentByName(departments.get(i));
			Department s = new Department(departments.get(i), id);

			sList.add(s);
		}
		return sList;
	}

	// employee functions
	public List<Employee> getEmployees() {
		String queryStr = "SELECT * FROM employee";
		Query query = entityManager.createNativeQuery(queryStr, Employee.class);

		List<?> resultList = query.getResultList();
		int size = resultList.size();
		List<Employee> empList = new ArrayList<Employee>();

		for (int i = 0; i < size; i++) {
			Employee emp = (Employee) resultList.get(i);
			empList.add(emp);
		}

		return (empList.size() > 0) ? empList : null;
	}

	public Employee save(Employee employee) {
		if (employee.getId() == null)
			entityManager.persist(employee);
		else
			entityManager.merge(employee);

		return employee;
	}
}


