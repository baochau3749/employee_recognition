package com.employee_recognition.Entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@Column(name = "user_profile_id")
	private Long userProfile;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public User() {
		timeCreated = new Timestamp(System.currentTimeMillis());
		roles = new HashSet<Role>();
	}

	public User(String email, String password) {
		this();
		this.email = email;
		this.password = password;
	}

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

	public Long getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(Long userProfile) {
		this.userProfile = userProfile;
	}

	public void setRole(Role role) {
		roles.clear();
		roles.add(role);
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		String roleDescription = "";
		for (Role role : roles) {
			if (role != null) {
				if (roleDescription.isEmpty())
					roleDescription = role.getRole();
				else
					roleDescription += ", " + role.getRole();
			}
		}
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", roles=(" + roleDescription + ")]";
	}
}
