package com.example.persistence.course;

import java.util.List;

import com.example.domain.admin.CourseInfo;
import com.example.domain.course.ApplyCourseInfo;
import com.example.web.control.course.ApplicationForm;

public interface ApplyInfoMapper {

	// 未来5個分の講座情報を取得する。希望講座一覧で使用する
	public List<CourseInfo> searchCourseInfo();
	// applicationテーブルへ利用者情報をinsert
	public void insertApply(ApplicationForm applicationForm);
	// 上記メソッドでinsertされたデータのIDを取得
	public String lastInsertId();
	// course_aaplyテーブルへ選択された希望講座をinsertする
	public void insertCourseApply(ApplyCourseInfo applyCourseInfo);
}
