package com.example.persistence.total;

import com.example.web.control.total.LoginForm;

public interface LoginMapper {

	// course_userテーブルへ入力されたログインデータをisnertする
	public void insertLoginInfo(LoginForm form);
	// 入力されたユーザIDとパスワードをもとに、権限番号を返す
	public int getUserInfo(LoginForm form);
	// 入力されたユーザIDと重なるデータをcourse_userテーブルから検索し、あった場合はtrueを返す
	public boolean searchIsExistUserID(LoginForm form);
}
