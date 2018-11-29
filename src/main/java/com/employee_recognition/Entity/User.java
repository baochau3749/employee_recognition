package com.employee_recognition.Entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.BindingResult;

import com.employee_recognition.Service.UserService;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Pattern(regexp=".+@.+\\..+", message="Email is missing or invalid (must be in \"___@___.___\" format).")
	@Size(max=255, message="Email must be 255 characters or less.")
	@Column(name = "email")
	private String email;

	@NotEmpty(message="Password is missing.")
	@Size(min=0, max=20, message="Password must be 1-20 characters.")
	@Column(name = "password")
	private String password;

	@Column(name = "time_created")
	private Timestamp timeCreated;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_profile_id")
	private UserProfile userProfile;

	@ManyToOne()
	@JoinColumn(name = "role_id")
	private Role role;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.REMOVE)
	private List<Award> userAwards;
	
	// default constructor
	public User() {
		timeCreated = new Timestamp(System.currentTimeMillis());
		userAwards = new ArrayList<>();
	}
	
	// overload constructor
	public User(String email, String password) {
		this();
		this.setEmail(email);
		this.setPassword(password);
	}

	// getters and setters
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = (email == null) ? "" : email.trim();		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = (password == null) ? "" : password.trim();
	}

	public Timestamp getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Timestamp timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public UserProfile getUserProfile()
	{
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile)
	{
		this.userProfile = userProfile;
	}
	
	public List<Award> getUserAwards()
	{
		return userAwards;
	}

	public void setUserAwards(List<Award> userAwards)
	{
		this.userAwards = userAwards;
	}

	// method to add awards to our user
	public void addAward(Award award)
	{
		userAwards.add(award);
		
		award.setUser(this);
	}
	
	@Override
	public String toString() {	
		return "User [UserId=" + userId + ", email=" + email + ", password=" + password + 
				", roles=(" + role.getRole() + "), " + userProfile + "]";
	}
	
	public void validate(BindingResult bindingResult, UserService userService) {		
		Boolean emailAvailable = userService.isEmailAvailable(this.email, this.userId);		
		if (!emailAvailable) {
			bindingResult.rejectValue("email", "user.email", "Email address has been used by another account.");
		}		
	}
}
