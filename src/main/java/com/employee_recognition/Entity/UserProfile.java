package com.employee_recognition.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_profile")
public class UserProfile
{
	@Id
	@Column(name = "user_profile_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="signature_file")
	private String targetFile;

	// default constructor
	public UserProfile() {}
	
	// overload constructor
	public UserProfile(String firstName, String lastName, String signatureFile)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.targetFile = signatureFile;
	}
	
	// getters and setters
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getTargetFile()
	{
		return targetFile;
	}

	public void setTargetFile(String targetFile)
	{
		this.targetFile = targetFile;
	}
}
