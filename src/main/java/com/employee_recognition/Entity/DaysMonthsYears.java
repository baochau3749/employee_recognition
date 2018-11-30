package com.employee_recognition.Entity;

import java.time.Year;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class DaysMonthsYears {
	
	private ArrayList<String> months = new ArrayList<String>();
	private ArrayList<String> days = new ArrayList<String>();
	private ArrayList<String> years = new ArrayList<String>();
	
	public DaysMonthsYears() {
		months.add("January");
		months.add("February");
		months.add("March");
		months.add("April");
		months.add("May");
		months.add("June");
		months.add("July");
		months.add("August");
		months.add("September");
		months.add("October");
		months.add("November");
		months.add("December");
		
		for (int i = Year.now().getValue(); i >= 1900; i--) {
			years.add(Integer.toString(i));
		}
		
		for (int i = 1; i < 32; i++) {
			days.add(Integer.toString(i));
		}
	}
	
	
	public ArrayList<String> getMonths() {
		return months;
	}

	public void setMonths(ArrayList<String> months) {
		this.months = months;
	}

	public ArrayList<String> getDays() {
		return days;
	}

	public void setDays(ArrayList<String> days) {
		this.days = days;
	}

	public ArrayList<String> getYears() {
		return years;
	}

	public void setYears(ArrayList<String> years) {
		this.years = years;
	}
	
	
	
	
}
