package com.example.persistence.total;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.web.control.total.LoginForm;

@Repository
public class LoginDao {

	@Autowired
	private LoginMapper mapper;
	
	public LoginDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 入力されたログインデータをcourse_userテーブルへinsertする
	 * @param form　入力されたログインデータ
	 */
	public boolean insertLoginInfo(LoginForm form) {
		try {
			mapper.insertLoginInfo(form);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * ログイン画面で入力されたデータを照合し、合えば権限番号を返す
	 * @param form 入力されたログインデータ
	 * @return 権限番号。無かった場合は‐1を返す
	 */
	public int getUserInfo(LoginForm form) {
		try {
			return mapper.getUserInfo(form);
		} catch(Exception e) {
			return -1;
		}
	}
	
	/**
	 * 入力されたユーザIDと重なるデータをcourse_userテーブルから検索し、あった場合はtrueを返す
	 * @param form 入力されたログインデータ
	 * @return
	 */
	public boolean searchIsExistUserID(LoginForm form) {
		try {
			return mapper.searchIsExistUserID(form);
		} catch(Exception e) {
			return false;
		}
	}
}
