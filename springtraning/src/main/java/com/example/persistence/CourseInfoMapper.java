package com.example.persistence;

import com.example.domain.CourseInfo;

public interface CourseInfoMapper {

	public void insert(CourseInfo courseInfo);
	
	public int getCountByCourseno(String courseno);
}
