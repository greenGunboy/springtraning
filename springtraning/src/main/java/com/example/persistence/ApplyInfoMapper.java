package com.example.persistence;

import java.util.List;

import com.example.domain.ApplyCourseInfo;
import com.example.domain.CourseInfo;
import com.example.web.control.ApplicationForm;

public interface ApplyInfoMapper {

	public List<CourseInfo> serchCourseInfo();
	
	public void insertApply(ApplicationForm applycationForm);
	
	public String lastInsertId();
	
	public void insertCourseApply(ApplyCourseInfo applyCourseInfo);
}
