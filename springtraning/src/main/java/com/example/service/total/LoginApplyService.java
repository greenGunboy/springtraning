package com.example.service.total;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.persistence.total.LoginDao;
import com.example.web.control.total.LoginForm;

@Service
public class LoginApplyService {

	@Autowired
	private LoginDao dao;
	
	/**
	 * 入力されたログインデータをcourse_userテーブルへinsertする
	 * @param form　入力されたログインデータ
	 */
	public boolean insertLoginInfo(LoginForm form) {
		
		return dao.insertLoginInfo(form);
	}
	
	/**
	 * ログイン画面で入力されたデータを照合し、合えば権限番号を返す
	 * @param form 入力されたログインデータ
	 * @return
	 */
	public int getUserInfo(LoginForm form) {
		return dao.getUserInfo(form);
	}
	
	/**
	 * 入力されたユーザIDと重なるデータをcourse_userテーブルから検索し、あった場合はtrueを返す
	 * @param form 入力されたログインデータ
	 * @return
	 */
	public boolean searchIsExistUserID(LoginForm form) {
		return dao.searchIsExistUserID(form);
	}
}
