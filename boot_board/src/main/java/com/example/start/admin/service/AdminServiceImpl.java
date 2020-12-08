package com.example.start.admin.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.start.admin.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO dao;
	
	@Override
	public int boardDelete(HttpServletRequest req, Model model) {
		int count = 0;
		String checkList[] = req.getParameterValues("checkList");
		for(int i = 0; i < checkList.length; i++) {
			int boardNum = Integer.parseInt(checkList[i]);
			count += dao.boardDelete(boardNum);
		}
		
		System.out.println(checkList.length);
		System.out.println(count);
		
		if(count == checkList.length) {
			return count;
		} else {
			return 0;
		}
	}

}
