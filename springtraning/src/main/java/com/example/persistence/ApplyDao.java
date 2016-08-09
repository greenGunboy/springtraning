package com.example.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.ApplyCourseInfo;
import com.example.domain.CourseInfo;
import com.example.web.control.ApplicationForm;

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
	
	/**
	 * 講座情報を取得する
	 * 希望講座に一覧表示に使用する。
	 * @return
	 */
	public List<CourseInfo> getCourseInfo() {
		List<CourseInfo> list = mapper.serchCourseInfo();
		return list;
	}
	
	/**
	 * applicationテーブルへ利用者情報をinsert
	 * @param applycationForm 入力された利用者情報
	 */
	public void insertApply(ApplicationForm applycationForm) {
		// 性別が選択されていない場合3（性別不明）に変換
		if(applycationForm.getGender() == 0){
			applycationForm.setGender(3);
		}
		mapper.insertApply(applycationForm);
	}
	
	/**
	 * 上記メソッドでinsertされたprimary keyを取得
	 * course_applyテーブルinsert時に使用する
	 * @return 最後にinsertされたID
	 */
	public String lastInsertId() {
		String id = mapper.lastInsertId();
		return id;
	}
	
	/**
	 * course_applyテーブルへ利用者IDと希望講座をinsertする
	 * @param id　利用者ID
	 * @param courseno　入力された希望講座（複数）
	 */
	public void insertCourseApply(String id, String[] courseno) {
		ApplyCourseInfo applyCourseInfo = new ApplyCourseInfo();
		// 利用者IDをセット
		applyCourseInfo.setId_application(id);
		// 選択された希望講座の回数分course_aaplyテーブルへinsertする
		for(String coursenm : courseno) {
			applyCourseInfo.setCourseno(coursenm);
			mapper.insertCourseApply(applyCourseInfo);
		}
	}
}
