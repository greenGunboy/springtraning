package com.example.persistence.total;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.admin.CourseInfo;
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
	public List<CourseInfo> getCourseInfo(ConfigForm form) {
		return mapper.getCourseInfo(form);
	}
	
	
}
