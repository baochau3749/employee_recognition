package com.employee_recognition.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employee_recognition.Entity.Role;

@Repository
@Transactional
public class RoleRepository {

	@Autowired
	private EntityManager entityManager;
	
	public Role findById(Long id) {
		return entityManager.find(Role.class, id);
	}

	public Role findByRoleDescription(String roleDescription) {
		String queryStr = "SELECT * FROM role WHERE role.role = :description";
		Query query = entityManager.createNativeQuery(queryStr, Role.class);		
		query.setParameter("description", roleDescription);
		
		List<?> resultList = query.getResultList();
		
		return (resultList.size() == 1) ? (Role)resultList.get(0) : null;
	}
	
	public Role save(Role role) {
		if (role.getId() == null)
			entityManager.persist(role);
		else
			entityManager.merge(role);
		
		return role;
	}
	
	public void deleteAll() {
		String queryStr = "DELETE FROM role WHERE role_id > 0";
		Query query = entityManager.createNativeQuery(queryStr);
		query.executeUpdate();
	}
	
	public void deleteById(Long id) {
		Role role = findById(id);
		entityManager.remove(role);
	}
}

