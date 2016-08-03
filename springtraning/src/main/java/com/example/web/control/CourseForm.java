package com.example.web.control;

import org.hibernate.validator.constraints.NotEmpty;

public class CourseForm {
	
	@NotEmpty
	private String courseno;
	@NotEmpty
	private String coursename;
	private String year;
	private String month;
	private String day;
	@NotEmpty
	private String vacantseats;
	private String sthour;
	private String stmin;
	private String endhour;
	private String endmin;
	private String thedate;
	private String starttime;
	private String endtime;
	
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
	public String getVacantseats() {
		return vacantseats;
	}
	public void setVacantseats(String vacantseats) {
		this.vacantseats = vacantseats;
	}
	public String getSthour() {
		return sthour;
	}
	public void setSthour(String sthour) {
		this.sthour = sthour;
	}
	public String getStmin() {
		return stmin;
	}
	public void setStmin(String stmin) {
		this.stmin = stmin;
	}
	public String getEndhour() {
		return endhour;
	}
	public void setEndhour(String endhour) {
		this.endhour = endhour;
	}
	public String getEndmin() {
		return endmin;
	}
	public void setEndmin(String endmin) {
		this.endmin = endmin;
	}
	public String getThedate() {
		this.thedate = year + "-" + String.format("%02d", Integer.valueOf(month)) + "-" + String.format("%02d", Integer.valueOf(day));
		return thedate;
	}
	public String getStarttime() {
		this.starttime = sthour + ":" + stmin;
		return starttime;
	}
	public String getEndtime() {
		this.endtime = endhour + ":" + endmin;
		return endtime;
	}
	
}
