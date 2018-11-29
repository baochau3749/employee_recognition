package com.employee_recognition.Entity;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee") 
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long id;

	@NotEmpty(message="First name is required.")
	@Size(min=0, max=50, message="First name must be 50 characters or less.")
	@Column(name = "first_name")
	private String firstName;

	@NotEmpty(message="Last name is required.")
	@Size(min=0, max=50, message="Last name must be 50 characters or less.")
	@Column(name = "last_name")
	private String lastName;

	@Pattern(regexp=".+@.+\\..+", 
			message="Email is missing or invalid (must be in \"___@___.___\" format).")
	@Size(max=255, message="Email must be 255 characters or less.")
	@Column(name = "email")
	private String email;

	@Column(name = "gender")
	private String gender;
	
	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "state_id")
	private int state;

	@Column(name = "position_id")
	private int position;

	@Column(name = "department_id")
	private int department;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	private List<Award> awards;

	public Employee() {
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", gender=" + gender + ", birthDate=" + birthDate + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = (firstName == null) ? "" : firstName.trim();	
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = (lastName == null) ? "" : lastName.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = (email == null) ? "" : email.trim();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFormattedBirthDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
		return dateFormat.format(this.birthDate).toString();
	}
	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int id) {
		this.state = id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}
	
}
