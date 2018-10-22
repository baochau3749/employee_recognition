package com.employee_recognition.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee_recognition.Entity.Role;
import com.employee_recognition.Entity.User;

@Repository

public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	public User findById(Long id) {
		return entityManager.find(User.class, id);
	}
	
	@Override
	public User findByEmail(String email) {
		String queryStr = "SELECT * FROM user WHERE email = :user_email";
		Query query = entityManager.createNativeQuery(queryStr, User.class);
		query.setParameter("user_email", email);
		
		List<?> resultList = query.getResultList();
		
		return (resultList.size() == 1) ? (User)resultList.get(0) : null;
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

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		String queryStr = "SELECT * FROM user";
		Query query = entityManager.createNativeQuery(queryStr, User.class);		
		
		return query.getResultList();
	}
}
