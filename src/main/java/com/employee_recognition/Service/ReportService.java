package com.employee_recognition.Service;

import java.util.Map;

public interface ReportService
{
	public Map< String,Map<String, Integer>> stateReport();
	public Map< String,Map<String, Integer>> genderReport();
	public Map< String,Map<String, Integer>> departmentReport();
	public Map< String,Map<String, Integer>> positionReport();
	// public Map< String,Map<String, Integer>> awardTypeReport();
}
