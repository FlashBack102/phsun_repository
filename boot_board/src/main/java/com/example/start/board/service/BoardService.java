package com.example.start.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {

	public int boardDelete(HttpServletRequest req, Model model);
	
	public void boardUpdate(HttpServletRequest req, Model model);
	
	public int boardUpdatePro(MultipartHttpServletRequest req, Model model);
	
	public void boardDownloads(HttpServletRequest req, Model model,  HttpServletResponse res) throws Exception;
	
	public void boardSearch(HttpServletRequest req, Model model);
	
	public int commentRegister(HttpServletRequest req, Model model);
	
	public int commentDelete(HttpServletRequest req, Model model);
	
	public int replyRegister(HttpServletRequest req, Model model);
	
}
