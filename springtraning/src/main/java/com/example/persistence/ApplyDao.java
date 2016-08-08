package com.example.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.ApplyCourseInfo;

@Repository
public class ApplyDao {
	
	@Autowired
	private ApplyInfoMapper mapper;

	public ApplyDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List<ApplyCourseInfo> getCourseInfo() {
		List<ApplyCourseInfo> list = mapper.serchCourseInfo();
		
		return list;
	}
}
