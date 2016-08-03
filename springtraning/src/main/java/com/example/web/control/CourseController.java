package com.example.web.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.CourseInfo;
import com.example.service.DIService;

@Controller
public class CourseController {
	
	@Autowired
	private DIService service;
	
/*************************表示の際に使う値***************************/
	@ModelAttribute("yearList")
	public List<String> setYear() {
		List<String> yearList = new ArrayList<String>();
		int cal = Calendar.getInstance().get(Calendar.YEAR);
		for (int i =cal; i<= cal+5; i++) {
			yearList.add(String.valueOf(i));
		}
		return yearList;
	}
	
	@ModelAttribute("monthList")
	public List<String> setMonth() {
		List<String> monthList = new ArrayList<String>();
		for (int i =1; i<= 12; i++) {
			monthList.add(String.valueOf(i));
		}
		return monthList;
	}
	
	@ModelAttribute("dayList")
	public List<String> setDay() {
		List<String> dayList = new ArrayList<String>();
		for (int i =1; i<= 31; i++) {
			dayList.add(String.valueOf(i));
		}
		return dayList;
	}
	
	@ModelAttribute("hourList")
	public List<String> setHour() {
		List<String> hourList = new ArrayList<String>();
		for (int i=10; i<=18; i++) {
			hourList.add(String.format("%02d", i));
		}
		return hourList;
	}
	
	@ModelAttribute("minList")
	public List<String> setMin() {
		List<String> minList = new ArrayList<String>();
		for (int i=0; i<=59; i++) {
			minList.add(String.format("%02d", i));
		}
		return minList;
	}
/************************************************************/
	
	@ModelAttribute(value="courseForm")
	public CourseForm setForm() {
		return new CourseForm();
	}
	
	@RequestMapping(value = "/admin/input", params="courseregister", method=RequestMethod.POST)
	public String inputPage() {
		return "admin/input";
	}
	
	@RequestMapping(value = "/admin/input", params="courseedit", method=RequestMethod.POST)
	public String editPage() {
		return "admin/end";
	}
	
	@RequestMapping(value="/admin/conf", params="back", method=RequestMethod.POST)
	public String inputToMenuPage() {
		return "admin/menu";
	}
	
	@RequestMapping(value="/admin/conf", params="confirm", method=RequestMethod.POST)
	public String inputToConfPage(@Validated @ModelAttribute("courseForm") CourseForm form, 
			BindingResult result) {
		if(result.hasErrors()) {
			return "admin/input";
		}
		return "admin/conf";
	}
	
	@RequestMapping(value="/admin/end", params="back", method=RequestMethod.POST)
	public String confToinputPage() {
		return "admin/input";
	}
	
	@RequestMapping(value="/admin/end", params="register", method=RequestMethod.POST)
	public String confToendPage(@ModelAttribute("courserForm") CourseForm form) {
		CourseInfo courseInfo = new CourseInfo();
		BeanUtils.copyProperties(form, courseInfo);
		service.insertCourseInfo(courseInfo);
		return "redirect:/admin/end?finish";
	}
	
	@RequestMapping(value="/admin/end", params="finish")
	public String confToendFinish() {
		return "admin/end";
	}
	
	@RequestMapping(value="/admin/menu", params="toMenu", method=RequestMethod.POST)
	public String endToMenuPage() {
		return "admin/menu";
	}
	
	@RequestMapping(value="/admin/menu", params="addregister", method=RequestMethod.POST)
	public String endToNextPage() {
		return "admin/input";
	}
	
}
