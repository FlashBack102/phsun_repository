package com.example.start.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.example.start.member.service.MemberService;

@ConfigurationProperties(prefix="file")
@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	MemberService service;
	
	@ResponseBody
	@RequestMapping("main")
	public String main(HttpServletRequest req, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication((Authentication) null);

		// SecurityContextHolder.clearContext();
		return (String)req.getSession().getAttribute("username");
	}
	
	@ResponseBody
	@RequestMapping("getAuthority")
	public String getAuthority(HttpServletRequest req, Model model) {
		return (String)req.getSession().getAttribute("username");
	}
	
	@RequestMapping("board")
	public String board(HttpServletRequest req, Model model) {
		
		service.board(req, model);
		return "member/board";
	}
	
	@RequestMapping("write")
	public String write() {
		
		return "member/write";
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}
	
	@ResponseBody
	@RequestMapping("boardRegister")
	public String boardRegister(MultipartHttpServletRequest req,Model model) {
		service.boardRegister(req, model);
		return "";
	}
	
	@RequestMapping(value="boardRead", method=RequestMethod.GET)
	public String boardRead(HttpServletRequest req, Model model) {
		
		service.boardRead(req, model);
		return "member/read";
	}
	
	@RequestMapping("denied")
	public String denied() {
		
		return "member/denied";
	}
}
