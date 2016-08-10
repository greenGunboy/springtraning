package com.example.web.control.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.admin.CourseResisterService;
import com.example.web.control.total.ConfigForm;

@Controller
public class CourseController {
	
	@Autowired
	private CourseResisterService service;
	
/*************************表示の際に使う値***************************/
	// selectタグで年を表示
	// 現在年から5年後までを表示
	@ModelAttribute("yearList")
	public List<String> setYear() {
		List<String> yearList = new ArrayList<String>();
		int cal = Calendar.getInstance().get(Calendar.YEAR);
		for (int i =cal; i<= cal+5; i++) {
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
	
	@ModelAttribute(value="courseForm")
	public CourseForm setForm() {
		return new CourseForm();
	}
	
	// menu.htmlでの「講座登録」ボタン押下時処理
	@RequestMapping(value = "/admin/input", params="courseregister")
	public String inputPage() {
		return "admin/input";
	}
	
	// menu.htmlでの「講座修正削除」ボタン押下時処理
	@RequestMapping(value = "/admin/input", params="courseedit")
	public String editPage(@ModelAttribute("configForm") ConfigForm form) {
		return "total/input";
	}
	
	// menu.htmlでの自動遷移用処理
	@RequestMapping(value = "/admin/input")
	public String input() {
		return "admin/input";
	}
	
	// input.htmlでの「戻る」ボタン押下時処理
	@RequestMapping(value="/admin/conf", params="back")
	public String inputToMenuPage() {
		return "admin/menu";
	}
	
	// input.htmlでの「確認」ボタン押下時処理
	@RequestMapping(value="/admin/conf", params="confirm")
	public String inputToConfPage(@Validated @ModelAttribute("courseForm") CourseForm form, 
			BindingResult result) {
		// 講座番号の重複チェック
		if(service.isDuplicateCourseno(form.getCourseno())) {
			result.rejectValue("courseno", "errors.duplicate.courseno");
		}
		//　「講座開催日」の「年」「月」「日」の未記入チェック
		if(form.getYear().equals("") || form.getMonth().equals("") || form.getDay().equals("")) {
			result.reject("errors.required.date");
		}
		// 「開始時刻」の「時」「分」の未記入チェック
		if(form.getSthour().equals("") || form.getStmin().equals("")) {
			result.reject("errors.required.stime");
		}
		// 「終了時刻」の「時」「分」の未記入チェック
		if(form.getEndhour().equals("") || form.getEndmin().equals("")) {
			result.reject("errors.required.etime");
		}
		// 「開始時刻」が「終了時刻」より後ではないかチェック
		if(!form.getSthour().equals("") && !form.getStmin().equals("") && !form.getEndhour().equals("") && !form.getEndmin().equals("")) {
			if(Integer.parseInt(form.getSthour() + form.getStmin()) >= Integer.parseInt(form.getEndhour() + form.getEndmin())) {
				result.reject("errors.contradiction.time");
			}
		}
		if(result.hasErrors()) {
			return "admin/input";
		}
		return "admin/conf";
	}
	
	// conf.htmlでの「戻る」ボタン押下時処理
	@RequestMapping(value="/admin/end", params="back")
	public String confToinputPage(@ModelAttribute("courseForm") CourseForm form) {
		return "admin/input";
	}
	
	// conf.htmlでの「登録」ボタン押下時処理
	@RequestMapping(value="/admin/end", params="register")
	public String confToendPage(@ModelAttribute("courserForm") CourseForm form) throws Exception {
		boolean flg = service.insertCourseInfo(form);
		if(flg) {
			return "redirect:/admin/end?finish";
		} else {
			return "course/error";
		}
	}
	
	// リダイレクト後処理
	@RequestMapping(value="/admin/end", params="finish")
	public String confToendFinish() {
		return "admin/end";
	}
	
	// end.htmlでの「講座管理メニュー」ボタン押下時処理
	@RequestMapping(value="/admin/menu", params="toMenu")
	public String endToMenuPage() {
		return "admin/menu";
	}
	
	// end.htmlでの「追加登録」ボタン押下時処理
	@RequestMapping(value="/admin/menu", params="addregister")
	public String endToNextPage() {
		return "admin/input";
	}	
}
