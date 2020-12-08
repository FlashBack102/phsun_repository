package com.example.start.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.start.guest.service.GuestService;

@Controller
@RequestMapping("/guest/")
public class GuestController {

	@Autowired
	GuestService service;
	
	@RequestMapping(value="register_form")
	public String registerForm() {
		return "register_form";
	}
	
	@RequestMapping(value="login_form")
	public String loginForm() {
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value="register")
	public int register(HttpServletRequest req, Model model) {
		
		int insertNum = service.register(req, model);
		return insertNum;
	}
	
	@ResponseBody
	@RequestMapping("failure")
	public String failure() {
		
		return null;
	} // 로그인 실패
}
