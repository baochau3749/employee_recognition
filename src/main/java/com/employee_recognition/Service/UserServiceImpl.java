package com.employee_recognition.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee_recognition.Entity.User;
import com.employee_recognition.Repository.RoleRepository;
import com.employee_recognition.Repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.getAllUsers();		
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User saveUser(User user, String type) {
		user.setRole(roleRepository.findByRoleDescription(type));
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}



}
