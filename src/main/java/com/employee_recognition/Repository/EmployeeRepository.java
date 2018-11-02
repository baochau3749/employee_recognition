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
	//state functions
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

		for (int i = 0; i < size; i++ ) {
			String s = (String) resultList.get(i);
			sList.add(s);
		}
		
		return (sList.size() > 0) ? sList : null;
		
	}
	
	public ArrayList<State> createStateList(ArrayList<String> states){
		ArrayList<State> sList = new ArrayList<State>();
		for (int i = 0; i < states.size(); i++) {	
			int id = findStateByName(states.get(i));
			State s = new State(states.get(i), id);
			//System.out.println(id);
			sList.add(s);
		}
		return sList;
	}
	
	//position functions
	public int findPositionByName(String position) {
		String queryStr = "SELECT position_id FROM position WHERE position='" + position + "'";
		Query q = entityManager.createNativeQuery(queryStr);
		List<?> resultList = q.getResultList();
		//int size = resultList.size(); 
		//ArrayList<Integer> intList = new ArrayList<Integer>();
		Integer id = (Integer) resultList.get(0);
		
		return id;
	}
	
	public ArrayList<String> getPositionList() {
		String queryStr = "SELECT position FROM position";
		Query q = entityManager.createNativeQuery(queryStr);
		List<?> resultList = q.getResultList();
		int size = resultList.size(); 
		ArrayList<String> sList = new ArrayList<String>();

		for (int i = 0; i < size; i++ ) {
			String s = (String) resultList.get(i);
			sList.add(s);
		}
		
		return (sList.size() > 0) ? sList : null;
	}
	
	public ArrayList<Position> createPositionList(ArrayList<String> positions){
		ArrayList<Position> sList = new ArrayList<Position>();
		for (int i = 0; i < positions.size(); i++) {	
			int id = findPositionByName(positions.get(i));
			Position s = new Position(positions.get(i), id);
			//System.out.println(id);
			sList.add(s);
		}
		return sList;
	}
	
	
	//department functions
	public int findDepartmentByName(String department) {
		String queryStr = "SELECT department_id FROM department WHERE department='" + 
				department + "'";
		Query q = entityManager.createNativeQuery(queryStr);
		List<?> resultList = q.getResultList();
		//int size = resultList.size(); 
		//ArrayList<Integer> intList = new ArrayList<Integer>();
		Integer id = (Integer) resultList.get(0);
		
		return id;
	}
	
	public ArrayList<String> getDepartmentList() {
		String queryStr = "SELECT department FROM department";
		Query q = entityManager.createNativeQuery(queryStr);
		List<?> resultList = q.getResultList();
		int size = resultList.size(); 
		ArrayList<String> sList = new ArrayList<String>();

		for (int i = 0; i < size; i++ ) {
			String s = (String) resultList.get(i);
			sList.add(s);
		}
		
		return (sList.size() > 0) ? sList : null;
	}
	
	public ArrayList<Department> createDepartmentList(ArrayList<String> departments){
		ArrayList<Department> sList = new ArrayList<Department>();
		for (int i = 0; i < departments.size(); i++) {	
			int id = findDepartmentByName(departments.get(i));
			Department s = new Department(departments.get(i), id);
			//System.out.println(id);
			sList.add(s);
		}
		return sList;
	}
	
	//employee functions
	public List<Employee> getEmployees() {
		String queryStr = "SELECT * FROM employee";
		Query query = entityManager.createNativeQuery(queryStr, Employee.class);		
		
		List<?> resultList = query.getResultList();
		int size = resultList.size(); 
		List<Employee> empList = new ArrayList<Employee>();
		
		for (int i = 0; i < size; i++ ) {
			Employee emp = (Employee) resultList.get(i);
			empList.add(emp);
		}
		
		return (empList.size() > 0) ? empList : null;
	}
	
	
	/*public void addState(Employee employee, String state) {
		String queryStr = "SELECT * FROM state WHERE role.role = :description";
		Query query = entityManager.createNativeQuery(queryStr, Role.class);		
		query.setParameter("description", roleDescription);
		
		List<?> resultList = query.getResultList();
				
		
	}
	public void addDepartment(Employee employee) {
		
	}
	public void addPosition(Employee employee) {
		
	}
	
	public List<String> getStates() {
		String queryStr = "SELECT * FROM state";
		Query query = entityManager.createNativeQuery(queryStr);		
		
		List<?> resultList = query.getResultList();
		int size = resultList.size(); 
		List<String> stateList = new ArrayList<String>();
		
		for (int i = 0; i < size; i++ ) {
			String s = (String) resultList.get(i);
			stateList.add(s);
		}
		
		return stateList;
	}
	
	public List<String> getDepartment() {
		String queryStr = "SELECT * FROM department";
		Query query = entityManager.createNativeQuery(queryStr);		
		
		List<?> resultList = query.getResultList();
		int size = resultList.size(); 
		List<String> departmentList = new ArrayList<String>();
		
		for (int i = 0; i < size; i++ ) {
			String d = (String) resultList.get(i);
			departmentList.add(d);
		}
		
		return departmentList;
	}
	
	public List<String> getPositions() {
		String queryStr = "SELECT * FROM position";
		Query query = entityManager.createNativeQuery(queryStr);		
		
		List<?> resultList = query.getResultList();
		int size = resultList.size(); 
		List<String> positionList = new ArrayList<String>();
		
		for (int i = 0; i < size; i++ ) {
			String p = (String) resultList.get(i);
			positionList.add(p);
		}
		
		return positionList;
	}*/
	
	
	public Employee save(Employee employee) {
		if (employee.getId() == null)
			entityManager.persist(employee);
		else
			entityManager.merge(employee);
		
		return employee;
	}
}

//package com.employee_recognition.Repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.employee_recognition.Entity.Role;
//
//@Repository
//public interface RoleRepository extends JpaRepository<Role, Long> {
//	Role findByRole(String role);
//}
