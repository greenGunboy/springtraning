package com.example.web.control.total;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.admin.CourseInfo;
import com.example.service.total.CourseEditService;

@Controller
public class ConfigController {

	@Autowired
	private CourseEditService service;
	
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
	
	
	@ModelAttribute("configForm")
	public ConfigForm setForm() {
		return new ConfigForm();
	}
	
	@RequestMapping("total/conf")
	public String inputToMenuPage(@ModelAttribute("configForm") ConfigForm form, 
			Model model) throws Exception {
		form.getState();
		List<CourseInfo> list = service.getCourseInfo(form);
		model.addAttribute("courseInfo", list);
		return "total/conf";
	}
	
	@RequestMapping(value="total/config", params="edit")
	public String editPage() {
		return "total/editinput";
	}
	@RequestMapping(value="total/config", params="delete")
	public String deletePage() {
		return "total/deleteconf";
	}
	@RequestMapping(value="total/config", params="search")
	public String searchPage() {
		return "total/input";
	}
	
	@RequestMapping(value="total/editend", params="back")
	public String inputTobackPage() {
		return "total/editinput";
	}
	@RequestMapping(value="total/editend", params="edit")
	public String inputToConfPage() {
		return "total/editend";
	}
	
	@RequestMapping(value="total/configmenu", params="menu")
	public String topPage() {
		return "total/editend";
	}
	@RequestMapping(value="total/configmenu", params="search")
	public String toSearchPage() {
		return "total/editend";
	}
	@RequestMapping(value="total/configmenu", params="courseList")
	public String courseListPage() {
		return "total/editend";
	}
	@RequestMapping(value="total/configmenu", params="logout")
	public String logout() {
		return "total/editend";
	}
	
	
}
