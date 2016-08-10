package com.example.service.total;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.admin.CourseInfo;
import com.example.persistence.total.ConfigDao;
import com.example.web.control.total.ConfigForm;

@Service
public class CourseEditService {

	@Autowired
	private ConfigDao dao;
	
	/**
	 * courseテーブルから講座情報を検索する
	 * @param form 入力された検索条件
	 * @return
	 * @throws Exception
	 */
	public List<CourseInfo> getCourseInfo(ConfigForm form) throws Exception {
		return dao.getCourseInfo(form);
	}
}
