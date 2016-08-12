package com.example.web.control.total;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class ConfigForm {

	private String courseno;
	@NotEmpty
	private String coursename;
	private String thedate;
	private String year;
	private String month;
	private String day;
	private String starttime;
	private String sthour;
	private String stmin;
	private String endtime;
	private String endhour;
	private String endmin;
	@NotNull
	@Range(min=1, max=50, message="「{0}」は、{min}以上、{max}以下で入力してください。")
	private Integer vacantseats;
	private String minvacantseats;
	private String maxvacantseats;
	private String state;
	
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
	// 「講座開催日」から年のみ取得
	public String getYear() {
		if(thedate != null) {
			String[] date = thedate.split("-");
			this.year = date[0];
		}
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	// 「講座開催日」から月のみ取得
	public String getMonth() {
		if(thedate != null) {
			String[] date = thedate.split("-");
			this.month = String.format("%01d", Integer.parseInt(date[1]));
		}
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	// 「講座開催日」から日のみ取得
	public String getDay() {
		if(thedate != null) {
			String[] date = thedate.split("-");
			this.day = String.format("%01d", Integer.parseInt(date[2]));
		}
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	// 「開始時刻」から時のみ取得
	public String getSthour() {
		if(starttime != null){
			String[] startTime = starttime.split(":");
			this.sthour = startTime[0];
		}
		return sthour;
	}
	public void setSthour(String sthour) {
		this.sthour = sthour;
	}
	// 「開始時刻」から分のみ取得
	public String getStmin() {
		if(starttime != null){
			String[] startTime = starttime.split(":");
			this.stmin = startTime[1];
		}
		return stmin;
	}
	public void setStmin(String stmin) {
		this.stmin = stmin;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	// 「終了時刻」から時のみ取得
	public String getEndhour() {
		if(endtime != null) {
			String[] endTime = endtime.split(":");
			this.endhour = endTime[0];
		}
		return endhour;
	}
	public void setEndhour(String endhour) {
		this.endhour = endhour;
	}
	// 「終了時刻」から分のみ取得
	public String getEndmin() {
		if(endtime != null) {
			String[] endTime = endtime.split(":");
			this.endmin = endTime[1];
		}
		return endmin;
	}
	public void setEndmin(String endmin) {
		this.endmin = endmin;
	}
	public Integer getVacantseats() {
		return vacantseats;
	}
	public void setVacantseats(Integer vacantseats) {
		this.vacantseats = vacantseats;
	}
	public String getMinvacantseats() {
		return minvacantseats;
	}
	public void setMinvacantseats(String minvacantseats) {
		this.minvacantseats = minvacantseats;
	}
	public String getMaxvacantseats() {
		return maxvacantseats;
	}
	public void setMaxvacantseats(String maxvacantseats) {
		this.maxvacantseats = maxvacantseats;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
