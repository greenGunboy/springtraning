package com.example.web.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewController {
	
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
	
	@RequestMapping("/admin/menu")
	public String menuPage() {
		return "admin/menu";
	}
	
	@RequestMapping(value = "/admin/input", params="courseregister", method=RequestMethod.POST)
	public String inputPage() {
		return "admin/input";
	}
	
	@RequestMapping(value = "/admin/input", params="courseedit", method=RequestMethod.POST)
	public String editPage() {
		return "admin/end";
	}
	
}
