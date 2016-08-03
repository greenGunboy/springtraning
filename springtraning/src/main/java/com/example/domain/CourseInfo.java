package com.example.domain;

public class CourseInfo {
	
	private String courseno;
	private String coursename;
	private String thedate;
	private Integer vacantseats;
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
	public String getThedate() {
		return thedate;
	}
	public void setThedate(String thedate) {
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
	
}
