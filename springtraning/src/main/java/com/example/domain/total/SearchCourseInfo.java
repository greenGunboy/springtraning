package com.example.domain.total;

import java.sql.Date;

public class SearchCourseInfo {

	private String courseno;
	private String coursename;
	private Date thedate;
	private Integer vacantseats;
	private String starttime;
	private String endtime;
	private String state;
	private Date nowtime;
	
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
	public Date getThedate() {
		return thedate;
	}
	public void setThedate(Date thedate) {
		this.thedate = thedate;
	}
	public Integer getVacantseats() {
		return vacantseats;
	}
	public void setVacantseats(Integer vacantseats) {
		this.vacantseats = vacantseats;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getNowtime() {
		return nowtime;
	}
	public void setNowtime(Date nowtime) {
		this.nowtime = nowtime;
	}
	
}
