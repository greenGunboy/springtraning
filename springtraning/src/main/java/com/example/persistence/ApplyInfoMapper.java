package com.example.persistence;

import java.util.List;

import com.example.domain.ApplyCourseInfo;
import com.example.domain.CourseInfo;
import com.example.web.control.ApplicationForm;

public interface ApplyInfoMapper {

	// 未来5個分の講座情報を取得する
	public List<CourseInfo> serchCourseInfo();
	// applicationテーブルへ利用者情報をinsert
	public void insertApply(ApplicationForm applycationForm);
	// 上記メソッドでinsertされたデータのIDを取得
	public String lastInsertId();
	// course_aaplyテーブルへ選択された希望講座をinsertする
	public void insertCourseApply(ApplyCourseInfo applyCourseInfo);
}
