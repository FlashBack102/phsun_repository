package com.example.start.member.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface MemberService {

	public void board(HttpServletRequest req, Model model);
	
	public void boardRegister(MultipartHttpServletRequest req, Model model);
	
	public void boardRead(HttpServletRequest req, Model model);
	
}
