package com.example.start.security.service;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.start.vo.UserVO;

@Configuration
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		UserVO vo = (UserVO) authentication.getPrincipal();
		
		request.getSession().setAttribute("username", authentication.getName());
		
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		String authority = vo.getAuthorities().toString().replaceAll(match, "");
		request.getSession().setAttribute("authority", authority);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/getAuthority");
		dispatcher.forward(request, response);
	}

}
