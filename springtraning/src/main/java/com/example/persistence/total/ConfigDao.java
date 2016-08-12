package com.example.persistence.total;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.total.SearchCourseInfo;
import com.example.domain.total.UpdateCourseInfo;
import com.example.web.control.total.ConfigForm;

@Repository
public class ConfigDao {

	@Autowired
	private ConfigMapper mapper;
	
	public ConfigDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * courseテーブルから検索情報と合致する講座情報を取得する
	 * @param form 入力された検索情報
	 * @return
	 */
	public List<SearchCourseInfo> getCourseInfo(ConfigForm form) {
		return mapper.getCourseInfo(form);
	}
	
	/**
	 * course_applyテーブルから選択された講座に希望登録している利用者情報を削除する
	 * @param form　選択されたcourseno（講座番号）
	 * @return 削除完了した場合true、完了しなかった場合はfalseを返す
	 */
	public boolean deleteCourseInfoFromcourse_apply(ConfigForm form) {
		try {
			mapper.deleteCourseInfoFromcourse_apply(form);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * courseテーブルから選択された講座情報を削除する
	 * @param form 選択されたcourseno（講座番号）
	 * @return 削除完了した場合true、完了しなかった場合はfalseを返す
	 */
	public boolean deleteCourseInfoFromcourse(ConfigForm form) {
		try {
			mapper.deleteCourseInfoFromcourse(form);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * 選択された講座情報を修正
	 * @param updCourseInfo　入力された講座修正データ
	 * @return 修正完了した場合true、完了しなかった場合false
	 */
	public boolean updateCourseInfo(UpdateCourseInfo updCourseInfo) {
		try {
			mapper.updateCourseInfo(updCourseInfo);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
