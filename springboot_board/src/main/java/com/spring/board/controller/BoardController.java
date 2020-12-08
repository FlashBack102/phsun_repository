package com.spring.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.board.board.service.BoardService;

@RequestMapping("/board/")
@Controller
public class BoardController {

	@Autowired
	BoardService service;

	@ResponseBody
	@RequestMapping("delete")
	public int boardDelete(HttpServletRequest req, Model model) {

		int cnt = service.boardDelete(req, model);
		return cnt;
	}

	@RequestMapping("board_update")
	public String boardUpdate(HttpServletRequest req, Model model) {

		service.boardUpdate(req, model);
		return "board/board_update";
	}

	@ResponseBody
	@RequestMapping("board_update_pro")
	public int boardUpdatePro(MultipartHttpServletRequest req, Model model) {
		int cnt = service.boardUpdatePro(req, model);

		return cnt;
	}
	
	@ResponseBody
	@RequestMapping("downloads")
	public String boardDownloads(HttpServletRequest req, Model model, HttpServletResponse res) throws Exception {
		
		service.boardDownloads(req, model, res);
		return "";
	}
	
	@RequestMapping("search")
	public String boardSearch(HttpServletRequest req, Model model) {
		service.boardSearch(req, model);
		return "board/board_search";
	}
	
	@ResponseBody
	@RequestMapping("comment_register")
	public int commentRegister(HttpServletRequest req, Model model) {
		
		int cnt = service.commentRegister(req, model);
		return cnt;
	}
	
	@ResponseBody
	@RequestMapping("comment_delete")
	public int commentDelete(HttpServletRequest req, Model model) {
		int cnt = service.commentDelete(req, model);
		return cnt;
	}
	
	@ResponseBody
	@RequestMapping("reply_register")
	public int replyRegister(HttpServletRequest req, Model model) {
		int cnt = service.replyRegister(req, model);
		
		return cnt;
	}
}
