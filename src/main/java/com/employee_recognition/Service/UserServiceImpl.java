package com.employee_recognition.Service;

import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee_recognition.Entity.Award;
import com.employee_recognition.Entity.AwardType;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.User;
import com.employee_recognition.Entity.UserProfile;
import com.employee_recognition.Repository.EmployeeRepository;
import com.employee_recognition.Repository.RoleRepository;
import com.employee_recognition.Repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private EmployeeRepository employeeDAO;
	
	@Override
	public List<User> getAllUsers()
	{
		return userRepository.getAllUsers();
	}

	@Override
	public User getUserByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}

	@Override
	public User getUserById(Long id)
	{
		return userRepository.findById(id);
	}

	@Override
	public User saveUser(User user, String type)
	{
		user.setRole(roleRepository.findByRoleDescription(type));
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(Long id)
	{
		userRepository.deleteById(id);
	}

	@Override
	public User getLoggedInUser()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = ((UserDetails) authentication.getPrincipal()).getUsername();
		return getUserByEmail(userEmail);
	}

	@Override
	public String generateRandomPassword()
	{
		StringBuilder password = new StringBuilder();

		for (int x = 0; x < 5; x++)
		{
			int value = (int) (Math.random() * 26) + 97;
			char letter = (char) value;

			password.append(letter);
		}

		return password.toString();
	}

	// content is subject to change
	// just pass the user that needs a new password
	@Override
	public void sendEmailResetPassword(User user) throws EmailException
	{
		String name = user.getUserProfile().getFirstName() + " " + user.getUserProfile().getLastName();
		String userEmail = user.getEmail();
		String userPassword = user.getPassword();

		Email email = new SimpleEmail();
		email.setHostName("smtp.mail.yahoo.com");
		email.setSmtpPort(465);

		email.setAuthenticator(new DefaultAuthenticator("cs467.project@yahoo.com", "employee123"));
		email.setSSLOnConnect(true);

		email.setFrom("cs467.project@yahoo.com");
		email.setSubject("Password Reset");

		email.setMsg("Hello " + name + "\n\nWe receieved a request to reset your account password. "
				+ "Your new password is: " + userPassword);

		email.addTo(userEmail);
		email.send();
	}

	// content is subject to change
	// just pass the award file name (i.e awardFile.pdf) and the employee receiving the award
	@Override
	public void sendEmailAward(String award, Employee employee) throws EmailException
	{
//		User currentUser = award.getUser();
//		Employee employee = employeeDAO.findById(award.getEmployee());
//		AwardType awardType = award.getAwardType();
//		
//		String name = employee.getFirstName() + " " + employee.getLastName();
//		String employeeEmail = employee.getEmail();		
//		String message;
//		MultiPartEmail email = new MultiPartEmail();
//		email.setHostName("smtp.mail.yahoo.com");
//		email.setSmtpPort(465);
//
//		message = "Hello " + name + ",\n";
//		message = "Hello " + name + ",\n";
//		message += "In recognition of your dedicated service to our customers " +
//		           "and our company, you have been cho";
//		//description = "In recognition of your dedicated service " + "to our customers and our company.";
//		
//		
//		email.setAuthenticator(new DefaultAuthenticator("cs467.project@yahoo.com", "employee123"));
//		email.setSSLOnConnect(true);
//
//		email.setFrom("cs467.project@yahoo.com");
//		email.setSubject("Employee Award");
//		email.setMsg("Hello " + name);
//		email.addTo(employeeEmail);
//		
//		if (award != null) {
//			String awardPath = award;
//			System.out.println("Email attachment = " + awardPath);
//			EmailAttachment attachment = new EmailAttachment();
//			attachment.setPath(awardPath);
//			attachment.setDisposition(EmailAttachment.ATTACHMENT);
//			email.attach(attachment);
//		}
//		System.out.println("Email sent.");
//		email.send();
	}

	@Override
	public void sendEmailAward(String awardFile, Award award, User user) throws EmailException {
		UserProfile userProfile = user.getUserProfile();
		Employee employee = employeeDAO.findById(award.getEmployee());
		AwardType awardType = award.getAwardType();
		
		String employeeName = employee.getFirstName() + " " + employee.getLastName();
		String userName = userProfile.getFirstName() + " " + userProfile.getLastName();
		String employeeEmail = employee.getEmail();		
		String message;
		
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.mail.yahoo.com");
		email.setSmtpPort(465);

		message = "Hello " + employeeName + ",\n";
		message += "In recognition of your dedicated service to our customers " +
				   "and our company, you have been chosen to receive the attached " +
				   awardType.getType() + "award.\n\n" + userName;

		email.setAuthenticator(new DefaultAuthenticator("cs467.project@yahoo.com", "employee123"));
		email.setSSLOnConnect(true);

		email.setFrom("cs467.project@yahoo.com");
		email.setSubject("Employee Award");
		email.setMsg(message);
		email.addTo(employeeEmail);
		
		if (awardFile != null) {
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(awardFile);
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			email.attach(attachment);
		}
		
		System.out.println("Email sent.");
		email.send();		
	}
}
