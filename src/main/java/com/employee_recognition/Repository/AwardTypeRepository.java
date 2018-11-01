package com.employee_recognition.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employee_recognition.Entity.AwardType;
import com.employee_recognition.Entity.Department;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.Position;
import com.employee_recognition.Entity.Role;
import com.employee_recognition.Entity.State;

@Repository
@Transactional
public class AwardTypeRepository {

	@Autowired
	private EntityManager entityManager;
	
	public AwardType findById(int id) {
		return entityManager.find(AwardType.class, id);
	}
	//state functions
	public int findAwardTypeByName(String type) {
		String queryStr = "SELECT award_type_id FROM award_type WHERE type='" + type + "'";
		Query q = entityManager.createNativeQuery(queryStr);
		List<?> resultList = q.getResultList();
		//int size = resultList.size(); 
		//ArrayList<Integer> intList = new ArrayList<Integer>();
		Integer id = (Integer) resultList.get(0);
		
		return id;
	}
	
	public ArrayList<String> getAwardTypeStringList() {
		String queryStr = "SELECT type FROM award_type";
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
	
	public List<AwardType> getAwardTypeList() {
		String queryStr = "SELECT * FROM award_type";
		Query query = entityManager.createNativeQuery(queryStr, AwardType.class);		
		
		List<?> resultList = query.getResultList();
		List<AwardType> typeList = new ArrayList<AwardType>();
		
		for (int i = 0; i < resultList.size(); i++) {
			typeList.add((AwardType) resultList.get(i));
		}
		
		return (resultList.size() >= 1) ? typeList : null;
	}
	
	public ArrayList<AwardType> createAwardTypeList(ArrayList<String> types){
		ArrayList<AwardType> sList = new ArrayList<AwardType>();
		for (int i = 0; i < types.size(); i++) {	
			int id = findAwardTypeByName(types.get(i));
			AwardType s = new AwardType(types.get(i), (long) id);
			System.out.println(id);
			sList.add(s);
		}
		return sList;
	}
	
	
	public AwardType save(AwardType awardType) {
		
		if (awardType.getAwardTypeId() == null)
			entityManager.persist(awardType);
		else
			entityManager.merge(awardType);
		
		return awardType;
	}
}
