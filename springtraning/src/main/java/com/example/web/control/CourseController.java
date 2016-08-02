package com.example.web.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseController {
	
	@ModelAttribute("yearList")
	public List<String> setList() {
		List<String> yearList = new ArrayList<String>();
		int cal = Calendar.getInstance().get(Calendar.YEAR);
		for (int i =cal; i<= cal+5; i++) {
			yearList.add(String.valueOf(i));
		}
		return yearList;
	}
	
	@ModelAttribute("courseForm")
	public CourseForm setForm() {
		return new CourseForm();
	}
	
	@RequestMapping(value = "/admin/conf", params="back", method=RequestMethod.POST)
	public String inputToconfPage() {
		return "admin/menu";
	}
	
	@RequestMapping(value = "/admin/conf", params="confirm", method=RequestMethod.POST)
	public String inputTomenuPage() {
		return "admin/conf";
	}
	
	@RequestMapping(value = "/admin/end", params="back", method=RequestMethod.POST)
	public String confToinputPage() {
		return "admin/input";
	}
	
	@RequestMapping(value = "/admin/end", params="register", method=RequestMethod.POST)
	public String confToendPage() {
		return "admin/end";
	}
	
	@RequestMapping(value = "/admin/menu", params="toMenu", method=RequestMethod.POST)
	public String endToMenuPage() {
		return "admin/menu";
	}
	
	@RequestMapping(value = "/admin/menu", params="addregister", method=RequestMethod.POST)
	public String endToNextPage() {
		return "admin/input";
	}
	
}
