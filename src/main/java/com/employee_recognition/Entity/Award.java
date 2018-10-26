package com.employee_recognition.Entity;

import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="award")
public class Award
{
	@Id
	@Column(name="award_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="employee_id")
	private Long employee;
	
	@Column(name="award_type_id")
	private Long awardType;
	
	@Column(name="date_time")
	private Timestamp timeCreated;
	
	// default constructor
	public Award()
	{
		timeCreated = new Timestamp(System.currentTimeMillis());
	}

	// overload constructor
	public Award(User user, Long employee, Long awardType)
	{
		this();
		this.user = user;
		this.employee = employee;
		this.awardType = awardType;
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

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Timestamp getTimeCreated()
	{
		return timeCreated;
	}

	@ManyToOne
	@JoinColumn(name="employee_id")
	public Long getEmployee()
	{
		return employee;
	}

	public void setEmployee(Long employee)
	{
		this.employee = employee;
	}

	public Long getAwardType()
	{
		return awardType;
	}

	public void setAwardType(Long awardType)
	{
		this.awardType = awardType;
	}

	public void setTimeCreated(Timestamp timeCreated)
	{
		this.timeCreated = timeCreated;
	}
}