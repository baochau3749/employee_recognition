package com.employee_recognition.Controller;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.employee_recognition.Entity.AwardType;
import com.employee_recognition.Repository.AwardTypeRepository;

public class AwardTypeEditor extends PropertyEditorSupport {
	public void setById(String awardType) {
		/*System.out.println("id is " + awardType);
		AwardTypeRepository a = new AwardTypeRepository();
		AwardType at = new AwardType();
		at = a.findById(Integer.parseInt(awardType));
		setValue(at);*/
	}
}
