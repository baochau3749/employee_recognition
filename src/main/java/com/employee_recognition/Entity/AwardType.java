package com.employee_recognition.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table (name="award_type")
public class AwardType {
	@Id
	@Column(name = "award_type_id")
	private Long awardTypeId;
	@Column(name = "type")
	private String type;
	
	@OneToMany(mappedBy="awardType")
	private List<Award> awards;
	
	public AwardType() {}
	
	public AwardType(String s, Long i) {
		awardTypeId = i;
		type = s;
	}
	public List<Award> getAwards() {
		return awards;
	}
	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}
	public Long getAwardTypeId() {
		return awardTypeId;
	}
	public void setAwardTypeId(Long id) {
		this.awardTypeId = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String awardType) {
		this.type = awardType;
	}
	
}
