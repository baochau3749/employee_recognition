package com.employee_recognition.Service;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDateValidator implements ConstraintValidator<ValidDate, Date> {

	@Override
	public void initialize(ValidDate validEmail) {
		System.out.println("inside ValidDateValidator - initialize");
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(validEmail);
	}

	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		System.out.println("inside ValidDateValidator - isValid");
		System.out.println("value = " + value);
		// TODO Auto-generated method stub
		//context.disableDefaultConstraintViolation();
		return false;
	}

//	@Override
//	public boolean isValid(String value, ConstraintValidatorContext context) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
