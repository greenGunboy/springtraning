package com.example.domain;

public class ApplyCourseInfo {

	private String courseno;
	private String coursename;
	private String year;
	private String month;
	private String day;
	private int week;
	private String weekday;
	private String starttime;
	private String endtime;
	private String vacantseats;
	
	public String getCourseno() {
		return courseno;
	}
	public void setCourseno(String courseno) {
		this.courseno = courseno;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
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
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getVacantseats() {
		return vacantseats;
	}
	public void setVacantseats(String vacantseats) {
		this.vacantseats = vacantseats;
	}
	public String getWeekday() {
		switch (this.week) {
		case 1:
			this.weekday = "(日)";
			break;
		case 2:
			this.weekday = "(月)";
			break;
		case 3:
			this.weekday = "(火)";
			break;
		case 4:
			this.weekday = "(水)";
			break;
		case 5:
			this.weekday = "(木)";
			break;
		case 6:
			this.weekday = "(金)";
			break;
		case 7:
			this.weekday = "(土)";
			break;
		}
		return weekday;
	}
	
}
