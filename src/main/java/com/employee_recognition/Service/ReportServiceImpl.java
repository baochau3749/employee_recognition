package com.employee_recognition.Service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_recognition.Repository.ReportRepository;

@Service
@Transactional
public class ReportServiceImpl implements ReportService
{
	@Autowired
	private ReportRepository reportRepository;
	
	@Override
	public Map<String, Map<String, Integer>> stateReport()
	{
		return reportRepository.stateReport();
	}

	@Override
	public Map< String,Map<String, Integer>> genderReport()
	{
		return reportRepository.genderReport();

	}

	@Override
	public Map< String,Map<String, Integer>> departmentReport()
	{
		
		return reportRepository.departmentReport();
	}

	@Override
	public Map< String,Map<String, Integer>> positionReport()
	{
		return reportRepository.positionReport();
	}

	@Override
	public Map< String,Map<String, Integer>> awardTypeReport()
	{
		return reportRepository.awardTypeReport();
	}
}
