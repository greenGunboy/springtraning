package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.CourseInfo;
import com.example.persistence.CourseInfoMapper;

@Service
public class DIService {

	@Autowired
	private CourseInfoMapper mapper;
	
	// 入力された講座情報をDBに登録するメソッド
	public void insertCourseInfo(CourseInfo courseInfo) {
		mapper.insert(courseInfo);
	}
	
	// 講座番号重複チェック処理メソッド
	public boolean isDuplicateCourseno(String courseno) {
		int count = mapper.getCountByCourseno(courseno);
		return count == 0 ? false:true;
	}
	
}
