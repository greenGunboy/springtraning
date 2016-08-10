package com.example.web.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewController {
	
	@ModelAttribute("courseForm")
	public CourseForm setForm() {
		return new CourseForm();
	}
	
	@RequestMapping("/admin/menu")
	public String menuPage() {
		return "admin/menu";
	}
	
}
