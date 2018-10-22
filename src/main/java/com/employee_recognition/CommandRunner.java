package com.employee_recognition;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.employee_recognition.Entity.Role;
import com.employee_recognition.Entity.User;
import com.employee_recognition.Repository.RoleRepository;
import com.employee_recognition.Repository.UserRepository;

@Component
public class CommandRunner implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
	}
	
	
	public void testRoleRepository() {
		Role role; 
		Long id;
		
		System.out.println();
		System.out.println(">>> ***** TEST ROLE REPOSITORY *****\n");
		
		// Delete all existing records.
		roleRepository.deleteAll();
		System.out.println(">>> delete all role records");
		System.out.println();
		
		// Add "ADMIN" role
		role = roleRepository.save(new Role("ADMIN"));
		showRole(role, "added role = ");

		// Add "USER" role		
		role = roleRepository.save(new Role("USER"));
		id = role.getId();
		showRole(role, "added role = ");
		
		System.out.println();
		
		// Find "ADMIN" role		
		role = roleRepository.findByRoleDescription("ADMIN");
		showRole(role, "find 'ADMIN' role, found: ");
		
		// Find role by id		
		role = roleRepository.findById(id);
		showRole(role, "find role with id = " + id + ", found: ");
		
		System.out.println();
		// delete "USER" role	
		roleRepository.deleteById(role.getId());
		System.out.println(">>> delete the 'USER' role");
		
		// Find "USER" role	
		role = roleRepository.findByRoleDescription("USER");
		showRole(role, "find 'USER' role, found: ");
		
		System.out.println();
			
		role = roleRepository.findByRoleDescription("ADMIN");
		id = role.getId();
		role.setRole("ADMIN_UPDATED"); // Change role description
		
		// Update "ADMIN" role
		roleRepository.save(role);
		System.out.println(">>> change 'ADMIN' role to 'ADMIN_UPDATED'");
		
		// Show updated "ADMIN" role
		role = roleRepository.findById(id);
		showRole(role, "Updated 'ADMIN' role = ");
		
		System.out.println();
		
		// Delete all existing records.
		roleRepository.deleteAll();
		System.out.println(">>> delete all role records");
		
		System.out.println();
	}
	
	public void testUserRepository() {
		Long id;
		User user;
			
		System.out.println(">>> ***** TEST USER REPOSITORY *****\n");
		
		userRepository.deleteAll();	
		System.out.println(">>> delete all user records\n");
		
		roleRepository.deleteAll();
		
		Role adminRole = roleRepository.save(new Role("ADMIN"));
		Role userRole = roleRepository.save(new Role("USER"));
		
		User newUser = new User("test_email@gmail.com", "abc123");	
		newUser.setRole(adminRole);
		userRepository.save(newUser);		
		id = newUser.getId();
		System.out.println(">>> Add a new user");
			
		user = userRepository.findById(id);
		showUser(user, "New user = ");
		System.out.println();
		
		newUser.setEmail("chaub@oregonstate.edu");
		newUser.setRole(userRole);
		user = userRepository.save(newUser);	
		System.out.println(">>> Change user email to chaub@oregonstate.edu" + 
						   " and set to 'USER' role");
		
		user = userRepository.findById(id);
		showUser(user, "Updated user = ");
		System.out.println();
		
		userRepository.deleteById(id);
		System.out.println(">>> Delete user with id = " + id);
		
		user = userRepository.findById(id);
		showUser(user, "find user with id = " + id + ", found: ");
		System.out.println();
	}	
	
	private void showRole(Role role, String msg) {
		if (role != null)
			System.out.println(">>> " + msg + role);
		else
			System.out.println(">>> " + msg + "None");
	}
	
	private void showUser(User user, String msg) {
		if (user != null)
			System.out.println(">>> " + msg + user);
		else
			System.out.println(">>> " + msg + "None");
	}
}
