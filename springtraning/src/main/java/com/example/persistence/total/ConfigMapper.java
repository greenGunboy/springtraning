package com.example.persistence.total;

import java.util.List;

import com.example.domain.total.SearchCourseInfo;
import com.example.domain.total.UpdateCourseInfo;
import com.example.web.control.total.ConfigForm;

public interface ConfigMapper {

	// 講座情報を取得。講座一覧にて使用
	public List<SearchCourseInfo> getCourseInfo(ConfigForm form);
	// course_applyテーブルから選択された講座情報を削除
	public void deleteCourseInfoFromcourse_apply(ConfigForm form);
	// courseテーブルから選択された講座情報を削除
	public void deleteCourseInfoFromcourse(ConfigForm form);
	// courseテーブルの選択された講座情報を修正
	public void updateCourseInfo(UpdateCourseInfo updCourseInfo);
}
