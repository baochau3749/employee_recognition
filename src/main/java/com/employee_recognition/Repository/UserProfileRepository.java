package com.employee_recognition.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employee_recognition.Entity.Award;
import com.employee_recognition.Entity.Department;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.Position;
import com.employee_recognition.Entity.State;
import com.employee_recognition.Entity.UserProfile;

@Repository
@Transactional
public class UserProfileRepository {

	@Autowired
	private EntityManager entityManager;
	
	public UserProfile findById(Long id) {
		return entityManager.find(UserProfile.class, id);
	}
	
	// Delete Award
	public void removeProfile(UserProfile profile)
	{
		entityManager.remove(profile);
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
