package com.spring.board.guest.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.board.guest.persistence.GuestDAO;

@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	GuestDAO dao;
	
	@Override
	public int register(HttpServletRequest req, Model model) {
		String id = req.getParameter("id");
		String pwd = passwordEncoder.encode(req.getParameter("pwd"));
		String authority = req.getParameter("authority");
		
		System.out.println("가공된 : " + id);
		System.out.println("가공된 : " + pwd);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pwd", pwd);
		map.put("authority", authority);
		
		return dao.register(map);
	}

}
