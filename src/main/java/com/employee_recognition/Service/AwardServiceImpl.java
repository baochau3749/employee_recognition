package com.employee_recognition.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_recognition.Entity.Award;
import com.employee_recognition.Repository.AwardRepository;


@Service
@Transactional
public class AwardServiceImpl implements AwardService
{
	@Autowired
	private AwardRepository awardRepository;
	
	@Override
	public void saveAward(Award award)
	{
		awardRepository.saveAward(award);
	}

	@Override
	public List<Award> getAwardsByUserID(Long id)
	{
		return awardRepository.getUserAwards(id);
	}

	@Override
	public void deleteAward(Award award)
	{
		awardRepository.removeAward(award);
	}

	@Override
	public void deleteAwardByID(Long id)
	{
		awardRepository.deleteByID(id);
	}

}
