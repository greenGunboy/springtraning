package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.CourseInfo;
import com.example.persistence.ApplyDao;
import com.example.web.control.ApplicationForm;

@Service
public class CourseApplyService {

	@Autowired
	private ApplyDao Dao;
	
	/**
	 * 講座情報を取得する
	 * 希望講座に一覧表示に使用する。
	 * @return
	 * @throws Exception
	 */
	public List<CourseInfo> serchCourseInfo() throws Exception {
		List<CourseInfo> list = Dao.getCourseInfo();
		return list;
	}
	
	/**
	 * applicationテーブルへ利用者情報をinsert
	 * @param applycationForm 入力された利用者情報
	 */
	public boolean insertApply(ApplicationForm applycationForm) {
		boolean flag = Dao.insertApply(applycationForm);
		return flag;
	}
	
	/**
	 * 上記メソッドでinsertされたprimary keyを取得
	 * course_applyテーブルinsert時に使用する
	 * @return
	 */
	public String lastInsertId() {
		String id = Dao.lastInsertId();
		return id;
	}
	
	/**
	 * course_applyテーブルへ利用者IDと希望講座をinsertする
	 * @param id　利用者ID
	 * @param courseno　入力された希望講座（複数）
	 */
	public boolean insertCourseApply(String id, String[] courseno) {
		boolean flg = Dao.insertCourseApply(id, courseno);
		return flg;
	}
	
}
