package com.example.service.admin;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.admin.CourseInfo;
import com.example.persistence.admin.CourseDao;
import com.example.web.control.admin.CourseForm;

@Service
public class CourseResisterService {

	@Autowired
	private CourseDao dao;
	
	// 入力された講座情報をDBに登録するメソッド
	public boolean insertCourseInfo(CourseForm form) throws Exception {
		
		CourseInfo courseInfo = new CourseInfo();
		BeanUtils.copyProperties(form, courseInfo);
		boolean flag = dao.insertCourseinfo(courseInfo);
		return flag;
	}
	
	// 講座番号重複チェック処理メソッド
	public boolean isDuplicateCourseno(String courseno) {
		
		int count = dao.isExistCourse(courseno);
		return count == 0 ? false:true;
	}
	
}
