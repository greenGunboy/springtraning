package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.ApplyCourseInfo;
import com.example.persistence.ApplyDao;

@Service
public class CourseApplyService {

	@Autowired
	private ApplyDao Dao;
	
	public List<ApplyCourseInfo> serchCourseInfo() throws Exception {
		List<ApplyCourseInfo> list = Dao.getCourseInfo();
		return list;
	}
	
}
