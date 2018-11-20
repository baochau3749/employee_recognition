package com.employee_recognition.Entity;

public class Report
{
	private String name;
	private String label;
	
	// constructor overload
	public Report(String name, String label)
	{
		super();
		this.name = name;
		this.label = label;
	}
	
	// getters and setters
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getLabel()
	{
		return label;
	}
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	
}
