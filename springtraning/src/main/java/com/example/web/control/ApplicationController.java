package com.example.web.control;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.CourseInfo;
import com.example.service.CourseApplyService;
import com.example.service.CourseResisterService;

public class ApplicationController {
	
	@Autowired
	private CourseApplyService service;

	@ModelAttribute(value="applicationForm")
	public ApplicationForm setForm() {
		return new ApplicationForm();
	}
	
	@RequestMapping("/course/menu")
	public String menuPage() {
		return "course/menu";
	}
	
	@RequestMapping(value = "/course/input", params="courseApply")
	public String inputPage() {
		return "course/input";
	}
	
	@RequestMapping(value = "/course/conf", params="courseApply")
	public String confPage() {
		return "course/conf";
	}
	
	@RequestMapping(value="/course/end", params="back")
	public String confToinputPage(@ModelAttribute("applicationForm") CourseForm form) {
		return "course/input";
	}
	
	@RequestMapping(value="/course/end", params="register")
	public String confToendPage(@ModelAttribute("applicationForm") CourseForm form) throws Exception {
		CourseInfo courseInfo = new CourseInfo();
		BeanUtils.copyProperties(form, courseInfo);
		boolean flg = service.insertCourseInfo(courseInfo);
		if(flg) {
			return "redirect:/course/end?finish";
		} else {
			return "course/error";
		}
	}
}
