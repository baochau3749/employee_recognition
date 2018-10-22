package com.employee_recognition.Repository;

import java.util.List;

import com.employee_recognition.Entity.User;

public interface UserRepository {
		
	public User findById(Long id);
	public User findByEmail(String email);
	public User save(User user);	
	public void deleteAll();	
	public void deleteById(Long id);
	public List<User> getAllUsers();
}
