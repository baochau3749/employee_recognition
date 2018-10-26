package com.employee_recognition.Service;

import java.util.List;

import com.employee_recognition.Entity.Award;

public interface AwardService
{
	public void saveAward(Award award);
	public List<Award> getAwardsByUserID(Long id);
	public void deleteAward(Award award);
	public void deleteAwardByID(Long id);
}
