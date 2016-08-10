package com.example.web.control.total;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.total.LoginApplyService;

@Controller
public class LoginController {

	@Autowired
	private LoginApplyService service;
	
	@ModelAttribute("loginForm")
	public LoginForm setForm() {
		return new LoginForm();
	}
	
	@RequestMapping("total/login")
	public String loginPage() {
		return "total/login";
	}
	
	@RequestMapping(value="total/logininput", params="login")
	public String loginToMenuPage(@Validated @ModelAttribute("loginForm") LoginForm form, 
			BindingResult result) {
		if(result.hasErrors()){
			return "total/login";
		} else {
			int authority = service.getUserInfo(form);
			if(authority == 0) {
				return "admin/menu";
			} else if(authority == 1) {
				return "course/menu";
			} else {
				result.reject("errors.required.loginInfo");
				return "total/login";
			}
		}
	}
	
	@RequestMapping(value="total/logininput", params="register")
	public String loginToInputPage() {
		return "total/logininput";
	}
	
	@RequestMapping(value="total/loginconf", params="check")
	public String loginToCheck(@ModelAttribute("loginForm") LoginForm form, 
			BindingResult result) {
		if(form.getUserid().equals("")){
			result.reject("errors.required.loginID");
			return "total/logininput";
		} else {
			boolean flg = service.searchIsExistUserID(form);
			if(flg){
				result.reject("errors.exist.loginID");
				return "total/logininput";
			} else {
				return "total/logininput";
			}
		}
	}
	
	@RequestMapping(value="total/loginconf", params="confirm")
	public String loginToConfPage(@Validated @ModelAttribute("loginForm") LoginForm form, 
			BindingResult result) {
		if(result.hasErrors()){
			return "total/logininput";
		} else {
			boolean flg = service.searchIsExistUserID(form);
			if(flg){
				result.reject("errors.exist.loginID");
				return "total/logininput";
			} else {
				return "total/loginconf";
			}
		}
	}
	
	@RequestMapping(value="total/loginend")
	public String loginToEnd(@ModelAttribute("loginForm") LoginForm form) {
		service.insertLoginInfo(form);
		return "total/loginend";
	}
	
	
	@RequestMapping(value="total/loginmenu", params="toCourseApply")
	public String loginToMenu() {
		return "course/menu";
	}
	
	@RequestMapping(value="total/loginmenu", params="logout")
	public String logout() {
		return "total/login";
	}
}
