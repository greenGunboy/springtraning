package com.example.web.control;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ApplicationForm {

	@NotEmpty
	private String name;
	@NotEmpty
	private String furigana;
	@NotEmpty
	@Email(message="「Eメールアドレス」の形式が不正です。")
	private String mail;
	private int gender;
	private String year;
	private String month;
	private String day;
	private String birthday;
	private String tel;
	@NotEmpty
	private String[] applyCourse;
	private String remarks;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFurigana() {
		return furigana;
	}
	public void setFurigana(String furigana) {
		this.furigana = furigana;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String[] getApplyCourse() {
		return applyCourse;
	}
	public void setApplyCourse(String[] applyCourse) {
		this.applyCourse = applyCourse;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	// insert用に"0000-00-00"の形にフォーマット
	public String getBirthday() {
		this.birthday = year + "-" + String.format("%02d", Integer.valueOf(month)) + "-" + String.format("%02d", Integer.valueOf(day));
		return birthday;
	}
}
