package com.employee_recognition.Service;

import java.util.List;

import org.apache.commons.mail.EmailException;

import com.employee_recognition.Entity.Award;
import com.employee_recognition.Entity.User;

public interface UserService {

	public List<User> getAllUsers();
	public User getUserByEmail(String email);
	public Boolean isEmailAvailable(String email, Long excludeId);
	public User getUserById(Long id);
	public User saveUser(User user, String type);
	public void deleteUserById(Long id);
	public User getLoggedInUser();
		
	public String generateRandomPassword();
	public void sendEmailResetPassword(User user) throws EmailException;	
	public void sendEmailAward(String awardFile, Award award, User user) throws EmailException;
}


