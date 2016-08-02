package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class DIService {

	public String getData(String year, String month, String day) {
		return year + month + day;
	}
	
	public String getStTime (String sthour, String stmin) {
		return String.format("%02d:%02d", sthour, stmin);
	}
	
	public String getEndTime (String endhour, String endmin) {
		return String.format("%02d:%02d", endhour, endmin);
	}
}
