package com.example.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.CourseInfo;

@Repository
public class CourseDao {

	@Autowired
	private CourseInfoMapper mapper;
	
	public CourseDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 入力された講座情報をcourseテーブルへ登録する
	 * @param courseInfo 入力された講座情報
	 */
	public boolean insertCourseinfo(CourseInfo courseInfo) {
		try {
			mapper.insert(courseInfo);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * 講座番号の重複チェック
	 * @param courseno 入力された講座番号
	 * @return　重複データがあれば1、なければ0
	 */
	public int isExistCourse(String courseno) {
		int count = mapper.getCountByCourseno(courseno);
		return count;
	}
}
