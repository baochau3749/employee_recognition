package com.employee_recognition.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.validation.BindingResult;

@Entity
@Table(name = "user_profile")
public class UserProfile
{
	@Id
	@Column(name = "user_profile_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long profileId;
	
//	@NotEmpty(message="First Name is missing.")
//	@Size(max=50, message="First Name must be 50 characters or less.")
	@Column(name="first_name")
	private String firstName;
	
//	@NotEmpty(message="Last Name is missing.")
//	@Size(max=50, message="Last Name must be 50 characters or less.")
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="signature_file")
	private String targetFile;

	// default constructor
	public UserProfile() {}
	
	// overload constructor
	public UserProfile(String firstName, String lastName, String signatureFile)
	{
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setTargetFile(signatureFile);
	}
	
	// getters and setters
	public Long getProfileId()
	{
		return profileId;
	}

	public void setProfileId(Long profileId)
	{
		this.profileId = profileId;
	}

	public String getFullName()
	{
		return firstName + " " + lastName;
	}
	
	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = (firstName == null) ? "" : firstName.trim();
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = (lastName == null) ? "" : lastName.trim();
	}

	public String getTargetFile()
	{
		return targetFile;
	}

	public void setTargetFile(String targetFile)
	{
		this.targetFile = (targetFile == null) ? "" : targetFile.trim();
	}

	@Override
	public String toString() {
		return "UserProfile [profileId=" + profileId + ", fullName=" + getFullName() + ", targetFile=" + targetFile + "]";
	}
	
	public void validate(BindingResult bindingResult, String newFileName, String errorFieldName) {
		if (this.firstName.isEmpty()) {
			bindingResult.rejectValue(errorFieldName, errorFieldName, "First name is missing.");
		}
		else if (this.firstName.length() > 50) {
			bindingResult.rejectValue(errorFieldName, errorFieldName, "First name must be 50 characters or less.");
		}

		if (this.lastName.isEmpty()) {
			bindingResult.rejectValue(errorFieldName, errorFieldName, "Last name is missing.");
		}
		else if (this.lastName.length() > 50) {
			bindingResult.rejectValue(errorFieldName, errorFieldName, "Last name must be 50 characters or less.");
		}
		
		if (!newFileName.isEmpty()) {
			String fExt = newFileName.replaceAll(".*\\.", "");
			boolean isNewFileExtensionValid = fExt.equals("jpeg") || fExt.equals("jpg") || fExt.equals("png");
			if (!isNewFileExtensionValid) {
				String errorMsg = "Signature file must be a jpeg, jpg, or png file. ";
				bindingResult.rejectValue(errorFieldName, errorFieldName, errorMsg);
			}
		}
		else if (this.targetFile.isEmpty()) {
			bindingResult.rejectValue(errorFieldName, errorFieldName, "Signature file is required.");
		}
	}
}
