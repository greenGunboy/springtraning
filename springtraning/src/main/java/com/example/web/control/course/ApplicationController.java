package com.example.web.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String menuToinputPage(Model model) throws Exception {
		// 希望講座に表示する5件の講座一覧を取得しmodelにセット
		List<CourseInfo> list = service.searchCourseInfo();
		model.addAttribute("courseInfo", list);
		
		return "course/input";
	}
	
	@RequestMapping("/course/conf")
	public String inputToconfPage(@Validated @ModelAttribute("applicationForm") ApplicationForm form, 
			BindingResult result, Model model) throws Exception {
		// 希望講座に表示する5件の講座一覧を取得しmodelにセット
		List<CourseInfo> list = service.searchCourseInfo();
		model.addAttribute("courseInfo", list);
		
		// 「生年月日」の未記入チェック
		if(form.getYear().equals("") || form.getMonth().equals("") || form.getDay().equals("")) {
			result.reject("errors.required.birthday");
		}
		// 「電話番号」の数字チェック
		if (!form.getTel().equals("")) {
			try {
				Long.parseLong(form.getTel());
			} catch (NumberFormatException e) {
				result.reject("errors.NumberFormatException.tel");
			}
		}
		if(result.hasErrors()) {
			return "course/input";
		}
		return "course/conf";
	}
	
	@RequestMapping(value="/course/end", params="back")
	public String confToinputPage(@ModelAttribute("applicationForm") ApplicationForm form, 
			Model model) throws Exception {
		// 希望講座に表示する5件の講座一覧を取得しmodelにセット
		List<CourseInfo> list = service.searchCourseInfo();
		model.addAttribute("courseInfo", list);
		
		return "course/input";
	}
	
	//TODO Serviceのメソッドに付ける
	// Controllerは画面遷移を担うレイヤなので、
	// DB寄りのTransaction処理は、Serviceレイヤへ移管する
//	@Transactional
	@RequestMapping(value="/course/end", params="apply")
	public String confToendPage(ApplicationForm form) {
	// @Transactionの移管に伴い、ここから
		// 利用者情報をapplicationテーブルへinsert
//		service.insertApply(form);
		// 上記でinsertされたprimary keyを取得
//		String id = service.lastInsertId();
		// 利用者IDとその利用者が申込んだ講座をcourse_appplyテーブルへinsert
//		service.insertCourseApply(id, form.getApplyCourse());
	// ここまでを１つのServiceメソッド化する
		
		boolean flg = service.insertApplyInfo(form);
		if(flg){
			return "course/end";
		} else {
			return "course/error";
		}
	}
}
