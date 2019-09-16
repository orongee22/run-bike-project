package com.teamrun.runbike.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teamrun.runbike.user.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value=("user/login"), method = RequestMethod.GET)
	public String getForm(HttpServletRequest request) {
		return "user/loginform";
	}
	
	@RequestMapping(value=("user/login"), method= RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam("u_id")String u_id, @RequestParam("u_pw")String u_pw, HttpServletRequest request) {
		String result = "";
		System.out.println("u_id : "+u_id);
		System.out.println("u_pw : "+u_pw);
		int loginChk = loginService.login(u_id, u_pw, request);
		if(u_id == "admin" && u_pw == "admin") {
			result = "admin";
		} else {
			switch(loginChk) {
			case 2: 
				result = "ok";
				break;
			case 1:
				result = "yet";
				break;
			case 0:
				result = "no";
					
			}
		}
		
		return result;
	}
	
	@RequestMapping("user/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
}
