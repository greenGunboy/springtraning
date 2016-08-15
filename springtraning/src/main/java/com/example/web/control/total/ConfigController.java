package com.example.web.control.total;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.domain.total.SearchCourseInfo;
import com.example.service.total.CourseEditService;

@Controller
@SessionAttributes(types = SearchForm.class)
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
		for (int i =cal-5; i<= cal+5; i++) {
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
	
	// 修正削除用form
	@ModelAttribute("configForm")
	public ConfigForm setForm() {
		return new ConfigForm();
	}
	
	// 検索用form
	@ModelAttribute("searchForm")
	public SearchForm setSearchForm() {
		return new SearchForm();
	}
	
	
	/** 講座検索画面 **/
	@RequestMapping("total/conf")
	public String inputToMenuPage(@ModelAttribute("searchForm") SearchForm form, 
			Model model) throws Exception {
		
		// 入力された検索条件で講座情報を読み込み一覧表示
		List<SearchCourseInfo> list = service.getCourseInfo(form);
		model.addAttribute("courseInfo", list);
		return "total/conf";
	}
	
	
	/** 講座一覧画面 **/
	// 「修正」ボタン押下時
	@RequestMapping(value="total/config", params="edit")
	public String editPage(@ModelAttribute("configForm") ConfigForm form) {
		return "total/editinput";
	}
	// 「削除」ボタン押下時
	@RequestMapping(value="total/config", params="delete")
	public String deletePage(@ModelAttribute("configForm") ConfigForm form) {
		return "total/deleteconf";
	}
	// 「講座検索」ボタン押下時
	@RequestMapping(value="total/config", params="search")
	public String searchPage() {
		return "total/input";
	}
	
	
	/** 削除確認画面 **/
	// 「戻る」ボタン押下時
	@RequestMapping(value="total/deleteend", params="back")
	public String backFromDeletePage() {
		return "total/conf";
	}
	// 「削除」ボタン押下時
	@RequestMapping(value="total/deleteend", params="delete")
	public String confTodeletePage(@ModelAttribute("configForm") ConfigForm form) {
		service.deleteCourseInfo(form);
		return "total/deleteend";
	}
	
	
	/** 講座修正画面 **/
	// 「戻る」ボタン押下時
	@RequestMapping(value="total/editconf", params="back")
	public String backToConfigPage() {
		return "total/conf";
	}
	// 「確認」ボタン押下時
	@RequestMapping(value="total/editconf", params="confirm")
	public String inputToEditConfPage(@Validated @ModelAttribute("configForm") ConfigForm form, 
			BindingResult result) {
		
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
			return "total/editinput";
		}
		return "total/editconf";
	}
	
	
	/** 修正確認画面 **/
	// 「戻る」ボタン押下時
	@RequestMapping(value="total/editend", params="back")
	public String inputTobackPage(@ModelAttribute("configForm") ConfigForm form) {
		return "total/editinput";
	}
	// 「修正」ボタン押下時
	@RequestMapping(value="total/editend", params="edit")
	public String inputToConfPage(@ModelAttribute("configForm") ConfigForm form) {
		service.updateCourseInfo(form);
		return "total/editend";
	}
	

	/** 削除完了、および修正完了画面 **/
	// 「講座管理メニュー」ボタン押下時
	@RequestMapping(value="total/configmenu", params="menu")
	public String topPage() {
		return "admin/menu";
	}
	// 「講座検索」ボタン押下時
	@RequestMapping(value="total/configmenu", params="search")
	public String toSearchPage() {
		return "total/input";
	}
	// 「講座一覧」ボタン押下時
	@RequestMapping(value="total/configmenu", params="courseList")
	public String courseListPage(@ModelAttribute("searchForm") SearchForm form, 
			Model model) throws Exception {
		
		// 入力された検索条件で講座情報を読み込み一覧表示
		List<SearchCourseInfo> list = service.getCourseInfo(form);
		model.addAttribute("courseInfo", list);
		return "total/conf";
	}
	// 「ログアウト」ボタン押下時
	@RequestMapping(value="total/configmenu", params="logout")
	public String logout() {
		return "login/login";
	}
}
