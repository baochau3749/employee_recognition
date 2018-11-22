package com.employee_recognition.Repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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


