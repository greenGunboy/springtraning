package com.example.web.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.ApplyCourseInfo;
import com.example.domain.CourseInfo;
import com.example.service.CourseApplyService;

@Controller
public class ApplicationController {
	
	@Autowired
	private CourseApplyService service;
	
	/*************************表示の際に使う値***************************/
	// selectタグで年を表示
	// 65年前から18年前までを表示
	@ModelAttribute("yearList")
	public List<String> setYear() {
		List<String> yearList = new ArrayList<String>();
		int cal = Calendar.getInstance().get(Calendar.YEAR);
		for (int i =cal-65; i<= cal-18; i++) {
			yearList.add(String.valueOf(i));
		}
		return yearList;
	}
	// selectタグで月を表示
	@ModelAttribute("monthList")
	public List<String> setMonth() {
		List<String> monthList = new ArrayList<String>();
		for (int i =1; i<= 12; i++) {
			monthList.add(String.valueOf(i));
		}
		return monthList;
	}
	// selectタグで日を表示
	@ModelAttribute("dayList")
	public List<String> setDay() {
		List<String> dayList = new ArrayList<String>();
		for (int i =1; i<= 31; i++) {
			dayList.add(String.valueOf(i));
		}
		return dayList;
	}
	// selectタグで時を表示
	@ModelAttribute("hourList")
	public List<String> setHour() {
		List<String> hourList = new ArrayList<String>();
		for (int i=10; i<=18; i++) {
			hourList.add(String.format("%02d", i));
		}
		return hourList;
	}
	// selectタグで分を表示
	@ModelAttribute("minList")
	public List<String> setMin() {
		List<String> minList = new ArrayList<String>();
		for (int i=0; i<=59; i++) {
			minList.add(String.format("%02d", i));
		}
		return minList;
	}
/************************************************************/
	

	@ModelAttribute(value="applicationForm")
	public ApplicationForm setForm() {
		return new ApplicationForm();
	}
	
	@RequestMapping("/course/menu")
	public String menuPage() {
		return "course/menu";
	}
	
	@RequestMapping("/course/input")
	public String inputPage(Model model) throws Exception {
		
		List<ApplyCourseInfo> list = service.serchCourseInfo();
		model.addAttribute("applycourseInfo", list);
		
		return "course/input";
	}
	
	@RequestMapping("/course/conf")
	public String confPage() {
		return "course/conf";
	}
	
	@RequestMapping(value="/course/end", params="back")
	public String confToinputPage(@ModelAttribute("applicationForm") CourseForm form) {
		return "course/input";
	}
	
	@RequestMapping(value="/course/end", params="apply")
	public String confToendPage() {
		return "course/end";
	}
}
