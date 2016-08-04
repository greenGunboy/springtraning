package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.CourseInfo;
import com.example.persistence.CourseDao;

@Service
public class CourseResisterService {

	@Autowired
	private CourseDao Dao;
	
	// 入力された講座情報をDBに登録するメソッド
	public boolean insertCourseInfo(CourseInfo courseInfo) throws Exception {
		
		boolean flag = Dao.insertCourseinfo(courseInfo);
		return flag;
	}
	
	// 講座番号重複チェック処理メソッド
	public boolean isDuplicateCourseno(String courseno) {
		
		int count = Dao.isExistCourse(courseno);
		return count == 0 ? false:true;
	}
	
}
