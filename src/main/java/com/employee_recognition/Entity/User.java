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

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "email")
	private String email;

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
		this.email = email;
		this.password = password;
	}

	// getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", roles=(" + role.getRole() + ")]";
	}
}
