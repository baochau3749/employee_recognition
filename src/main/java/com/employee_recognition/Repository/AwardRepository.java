package com.employee_recognition.Repository;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee_recognition.Entity.Award;

@Repository
public class AwardRepository
{
	@Autowired
	private EntityManager entityManager;
	
	// Create New Award
	public void saveAward(Award award)
	{
		entityManager.persist(award);
	}
	
	// Delete Award
	public void removeAward(Award award)
	{
		entityManager.remove(award);
	}
	
	// Delete Award by ID
	public void deleteByID(Long id)
	{
		String queryString = "DELETE FROM award WHERE award_id=:awardID";
		Query query = entityManager.createNativeQuery(queryString);
		query.setParameter("awardID", id);
		query.executeUpdate();
	}
	
	
	// Get Awards for User
	public List<Award> getUserAwards(Long userID)
	{
		String queryString = "SELECT * FROM award where user_id = :userID";
		Query query = entityManager.createNativeQuery(queryString, Award.class);
		query.setParameter("userID", userID);
		
		return query.getResultList();
	}
	
	
	// Get all the awards
	public List<Award> getAwards()
	{
		String queryString = "SELECT * FROM award";
		Query query = entityManager.createNativeQuery(queryString, Award.class);
		return query.getResultList();
	}
	
}
