package com.employee_recognition.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employee_recognition.Entity.User;

@Repository
@Transactional
public class UserRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	public User findById(Long id) {
		return entityManager.find(User.class, id);
	}
	
	public User save(User user) {
		if (user.getId() == null)
			entityManager.persist(user);
		else
			entityManager.merge(user);
		
		return user;		
	}
	
	public void deleteAll() {
		String queryStr = "DELETE FROM user WHERE user_id > 0";
		Query query = entityManager.createNativeQuery(queryStr);
		query.executeUpdate();
	}
	
	public void deleteById(Long id) {
		User user = findById(id);
		entityManager.remove(user);
	}
}
