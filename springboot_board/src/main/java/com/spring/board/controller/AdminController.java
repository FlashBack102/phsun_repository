package com.spring.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.admin.service.AdminService;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	AdminService service;
	
	@ResponseBody
	@RequestMapping("boardDelete")
	public int boardDelete(HttpServletRequest req, Model model) {
		
		int count = service.boardDelete(req, model);
		System.out.println(count + "합");
		return count;
	}
}
