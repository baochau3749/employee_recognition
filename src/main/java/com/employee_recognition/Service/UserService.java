package com.employee_recognition.Service;

import java.util.List;

import org.apache.commons.mail.EmailException;

import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.User;

public interface UserService {

	public List<User> getAllUsers();
	public User getUserByEmail(String email);
	public User getUserById(Long id);
	public User saveUser(User user, String type);
	public void deleteUserById(Long id);
	public User getLoggedInUser();
	
	public String generateRandomPassword();
	public void sendEmailResetPassword(User user) throws EmailException;
	public void sendEmailAward(String award, Employee employee) throws EmailException;
}


