package com.employee_recognition.Entity;

import java.sql.Timestamp;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	
	@ManyToOne
	@JoinColumn(name="award_type_id")
	private AwardType awardType;
	
	@Column(name="date_time")
	private Timestamp dateGiven;
	
	// default constructor
	public Award()
	{
		
	}

	// overload constructor
	public Award(User user, Long employee, AwardType awardType)
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

	public Timestamp getdateGiven()
	{
		return dateGiven;
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

	public AwardType getAwardType()
	{
		return awardType;
	}

	public void setAwardType(AwardType awardType)
	{
		this.awardType = awardType;
	}

	public void setdateGiven(Timestamp dateGiven)
	{
		this.dateGiven = dateGiven;
		
		//Timestamp time = new Timestamp(parsedDate.getTime());
		//this.dateGiven = time;
	}
		
		
}