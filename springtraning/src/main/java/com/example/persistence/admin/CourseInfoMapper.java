package com.example.persistence.admin;

import com.example.domain.admin.CourseInfo;

public interface CourseInfoMapper {

	public void insert(CourseInfo courseInfo);
	
	public int getCountByCourseno(String courseno);
	
}
