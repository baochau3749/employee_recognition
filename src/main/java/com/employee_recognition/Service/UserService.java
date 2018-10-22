package com.employee_recognition.Service;

import java.util.List;

import com.employee_recognition.Entity.User;

public interface UserService {

	public List<User> getAllUsers();
	public User getUserByEmail(String email);
	public User getUserById(Long id);
	public User saveUser(User user, String type);
	public void deleteUserById(Long id);
	public User getLoggedInUser();
}


