package com.example.start;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {

	@RequestMapping("/")
	public String basic() {
		return "home";
	}
	
	@RequestMapping("/admin/home")
	public String admin() {
		return "admin/home";
	} // 관리자 페이지
	
	@RequestMapping("/member/home")
	public String member() {
		return "member/home";
	} // 로그인한 유저 페이지
	
	@RequestMapping("/login")
	public String login() {
		return "/login";
	} // 로그인 페이지
	
	@RequestMapping("/logout")
	public String logout() {
		return "home";
	} // 로그아웃
	
//	@RequestMapping("/member/denied")
//	public String denied() {
//		return "denied";
//	} // 권한없음
	
}
