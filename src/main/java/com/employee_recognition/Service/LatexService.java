package com.employee_recognition.Service;

import java.io.File;

import com.employee_recognition.Entity.Award;

public interface LatexService {
	File CreateAwardFile(Award award);
}
