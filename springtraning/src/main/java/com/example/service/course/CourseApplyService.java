package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.CourseInfo;
import com.example.persistence.ApplyDao;
import com.example.web.control.ApplicationForm;

@Service
public class CourseApplyService {

	// TODO 変数名の先頭を小文字に
	@Autowired
	private ApplyDao dao;
	
	/**
	 * 講座情報を取得する
	 * 希望講座に一覧表示に使用する。
	 * @return
	 * @throws Exception
	 */
	// TODO メソッド名のスペルミス：serch-> search
	public List<CourseInfo> searchCourseInfo() throws Exception {
//		List<CourseInfo> list = dao.getCourseInfo();
//		return list;
		 return dao.getCourseInfo();
	}
	
//	/**
//	 * applicationテーブルへ利用者情報をinsert
//	 * @param applycationForm 入力された利用者情報
//	 */
//	public boolean insertApply(ApplicationForm applicationForm) {
//		return dao.insertApply(applicationForm);
//	}
//	
//	/**
//	 * 上記メソッドでinsertされたprimary keyを取得
//	 * course_applyテーブルinsert時に使用する
//	 * @return
//	 */
//	public String lastInsertId() {
//		return dao.lastInsertId();
//	}
//	
//	/**
//	 * course_applyテーブルへ利用者IDと希望講座をinsertする
//	 * @param id　利用者ID
//	 * @param courseno　入力された希望講座（複数）
//	 */
//	public boolean insertCourseApply(String id, String[] courseno) {
//		return dao.insertCourseApply(id, courseno);
//	}
	
	/**
	 * applicationテーブルへ利用者情報をinsert
	 * course_applyテーブルへ利用者IDと希望講座をinsertする
	 * 
	 * @param applicationForm 入力された利用者情報と希望講座（複数）情報
	 * @return
	 */
	@Transactional
	public boolean insertApplyInfo(ApplicationForm applicationForm) {
		try {
			dao.insertApply(applicationForm);
			// 上記で登録された利用者ID
			String id = dao.lastInsertId();
			dao.insertCourseApply(id, applicationForm.getApplyCourse());
			return true;
		} catch(Exception e){
			return false;
		}
	}
	
}
