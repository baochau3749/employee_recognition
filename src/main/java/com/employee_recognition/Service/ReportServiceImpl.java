package com.employee_recognition.Service;

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
	public String stateReport()
	{
		return reportRepository.stateReport();
	}

	@Override
	public String genderReport()
	{
		return reportRepository.genderReport();

	}

	@Override
	public String departmentReport()
	{
		
		return reportRepository.departmentReport();
	}

	@Override
	public String awardTypeReport()
	{
		return "";
	}

	@Override
	public String positionReport()
	{
		return reportRepository.positionReport();
	}

}
