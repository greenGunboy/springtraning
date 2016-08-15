package com.example.service.total;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.total.SearchCourseInfo;
import com.example.domain.total.UpdateCourseInfo;
import com.example.persistence.total.ConfigDao;
import com.example.web.control.total.ConfigForm;
import com.example.web.control.total.SearchForm;

@Service
public class CourseEditService {

	@Autowired
	private ConfigDao dao;
	
	/**
	 * courseテーブルから講座情報を検索する
	 * @param form 入力された検索条件
	 * @return 講座の検索結果
	 * @throws Exception
	 */
	public List<SearchCourseInfo> getCourseInfo(SearchForm form) throws Exception {
		
		// 講座一覧の検索結果
		List<SearchCourseInfo> list = dao.getCourseInfo(form);
		String DATE_PATTERN ="yyyy-MM-dd";
		
		for(SearchCourseInfo a: list){
			// 講座開催日の日時
			String CourseDate = (new SimpleDateFormat(DATE_PATTERN)).format(a.getThedate());
			String[] courseDate = (CourseDate.split("-"));
			String year = courseDate[0];
			String month = courseDate[1];
			String day = courseDate[2];
			
			// 現在日時
			String NowDate = (new SimpleDateFormat(DATE_PATTERN)).format(a.getNowtime());
			String[] nowDate = NowDate.split("-");
			String nyear = nowDate[0];
			String nmonth = nowDate[1];
			String nday = nowDate[2];

			// 日付を比較
			if (Integer.parseInt(nyear + nmonth + nday) > Integer.parseInt(year + month + day)) {
				a.setState("終了");
			} else if (Integer.parseInt(nyear + nmonth + nday) < Integer.parseInt(year + month + day)) {
				a.setState("開催予定");
			} else if (Integer.parseInt(year + month + day) == Integer.parseInt(nyear + nmonth + nday)) {
				a.setState("開催中");
			}
		}
		return list;
	}
	
	/**
	 * courseテーブルと、course_applyテーブルから選択された講座情報を削除する
	 * @param form 選択されたcourseno（講座番号）
	 * @return 削除完了時はtrue、完了しなかった場合はfalseを返す
	 */
	@Transactional
	public boolean deleteCourseInfo(ConfigForm form) {
		boolean deleteCourseApply_flg = dao.deleteCourseInfoFromcourse_apply(form);
		boolean deleteCourse_flg = dao.deleteCourseInfoFromcourse(form);
		if(deleteCourseApply_flg || deleteCourse_flg){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * courseテーブルに存在する選択された講座情報を修正する
	 * @param form 入力された講座の修正データ
	 * @return 修正完了時はtrue、完了しなかった場合はfalseを返す
	 */
	public boolean updateCourseInfo(ConfigForm form) {
		// form内データをinfoへコピーし、updateのようのデータに編集
		UpdateCourseInfo updCourseInfo = new UpdateCourseInfo();
		if(form!=null){
			BeanUtils.copyProperties(form, updCourseInfo);
		}
		boolean flg = dao.updateCourseInfo(updCourseInfo);
		return flg;
	}
}
