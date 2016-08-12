package com.example.domain.total;

public class UpdateCourseInfo {

	private String courseno;
	private String coursename;
	private String thedate;
	private String year;
	private String month;
	private String day;
	private Integer vacantseats;
	private String sthour;
	private String stmin;
	private String starttime;
	private String endhour;
	private String endmin;
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
	public Integer getVacantseats() {
		return vacantseats;
	}
	public void setVacantseats(Integer vacantseats) {
		this.vacantseats = vacantseats;
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
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	// update用に"0000-00-00"の形にフォーマット
	public String getThedate() {
		thedate = year + "-" + String.format("%02d", Integer.valueOf(month)) + "-" + String.format("%02d", Integer.valueOf(day));
		return thedate;
	}
	// update用に"00:00"の形にフォーマット
	public String getStarttime() {
		this.starttime = sthour + ":" + stmin;
		return starttime;
	}
	// update用に"00:00"の形にフォーマット
	public String getEndtime() {
		this.endtime = endhour + ":" + endmin;
		return endtime;
	}
}
