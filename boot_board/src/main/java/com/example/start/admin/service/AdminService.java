package com.example.start.admin.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface AdminService {

	public int boardDelete(HttpServletRequest req, Model model);
}
