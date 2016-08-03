package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.CourseInfo;
import com.example.persistence.CourseInfoMapper;

@Service
public class DIService {

	@Autowired
	private CourseInfoMapper mapper;
	
	public void insertCourseInfo(CourseInfo courseInfo) {
		mapper.insert(courseInfo);
	}
	
}
