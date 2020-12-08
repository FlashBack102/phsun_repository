package com.spring.board.board.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.board.board.persistence.BoardDAO;
import com.spring.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO dao;
	
	@Override
	public int boardDelete(HttpServletRequest req, Model model) {
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		
		int cnt = dao.boardDelete(boardNum);
		return cnt;
	}

	@Override
	public void boardUpdate(HttpServletRequest req, Model model) {
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		
		BoardVO vo = dao.boardUpdate(boardNum);
		model.addAttribute("vo", vo);
	}

	@Override
	public int boardUpdatePro(MultipartHttpServletRequest req, Model model) {
		MultipartFile file = req.getFile("fileUpload");
		String existFile = req.getParameter("existFile");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subject", subject);
		map.put("content", content);
		map.put("boardNum", boardNum);
		if(file == null) {
			map.put("file", existFile);
		} else {
			String originalfileName = file.getOriginalFilename();
			File dest = new File("F:\\Study2020\\img\\" + originalfileName);
			// File dest = new File(application.getProperty("address") + originalfileName);
			map.put("file", originalfileName);
			try {
				file.transferTo(dest);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		int cnt =  dao.boardUpdatePro(map);
		return cnt;
	}
	
	@Override
	public void boardDownloads(HttpServletRequest req, Model model, HttpServletResponse res) throws Exception {
		
		String originalfileName = req.getParameter("attachedFile");
		File file = new File("F:\\Study2020\\img\\" + originalfileName);
		res.setContentType("application/octet-stream;charset=utf-8");
		res.setContentLength((int) file.length());
		res.setHeader("Content-Disposition", "attachment; filename=\"" +
				java.net.URLEncoder.encode(file.getName(), "utf-8") + "\";");
		res.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = res.getOutputStream();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
			System.out.println(fis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		out.flush();
	}

	@Override
	public void boardSearch(HttpServletRequest req, Model model) {
		String type = req.getParameter("type");
		String search = req.getParameter("search");
		
		System.out.println(type);
		System.out.println(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("search", search);

		List<BoardVO> vo = dao.boardSearch(map);
		
		int cnt = vo.size(); // 게시글 갯수를 가져오는 DAO
		int pageSize = 5;
		int pageBlock = 5; // 5페이지 단위로 페이징 처리
		int pageCount = 0; // 총 페이지 갯수
		int currentPage = 0; // 현재 보고있는 페이지 번호
		int startPage = 0;
		int endPage = 0;
		int startNum = 0;
		int endNum = 0;
		
		if(req.getParameter("nextPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(req.getParameter("nextPage"));
		}
		
		pageCount = (cnt / pageBlock) + (cnt % pageBlock > 0 ? 1 : 0);
		// 만약, 게시글이 133개라면 10개씩 한 블록마다 보여주면 14개의 블록이 필요함(13 * 10 + 3)->3을 위한 1블록 추가
		// 삼항연산자를 통해 게시글 갯수가 10으로 나뉘어 떨어지는지 검사 후 1을 추가해줌
		
		startPage = (currentPage / pageBlock) * pageBlock + 1;
		// 시작페이지로써 1, 11, 21, 31을 나타냄
		if(currentPage % pageSize == 0) {
			startPage = currentPage - pageBlock + 1;
		} // 만약 현재 페이지가 30이면 if문 위 공식처럼 하면 시작페이지가 31로 나옴 -> 31/10 * 10 + 1 = 31
		// 그렇기에 현재 페이지와 페이지블록을 나눴을 때 나머지가 0인 경우 : 한 블록에 마지막 숫자에 도달한 것
		// 마지막 숫자에 도달했을 경우 계산된 시작페이지에서 페이지사이즈를 빼면 정상적인 시작페이지가 설정됨
		
		endPage = startPage + pageBlock - 1;
		// 마지막페이지로써, 10, 20, 30, 40 ... (43)이 될 수도 있음-> 이것들을 나타냄
		if(pageCount < endPage) {
			endPage = pageCount;
		}
		
		startNum = (currentPage - 1) * pageSize + 1;
		// 현 페이지의 시작 게시글번호
		// 현재 페이지가 2이면 (2-1) * 10 + 1을 하며 시작번호가 11이 됨
		// RowNum을 index로 하여 게시글을 가져올 것이기 때문에 1로 시작해야함
		
		endNum = startNum + pageSize - 1;
		// 현 페이지의 끝 게시글번호
		// 현재 페이지가 2이면 시작번호 : 11 + 10 - 1로 RowNum이 20인 게시글까지 표시함		
		
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<BoardVO> boardList = dao.boardSearchList(map);
		
		System.out.println(startNum + "시작번호");
		System.out.println(endNum + "끝번호");
		
		model.addAttribute("type", type);
		model.addAttribute("search", search);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageBlock", pageBlock);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("startNum", startNum);
		model.addAttribute("endNum", endNum);
		model.addAttribute("id", (String)req.getSession().getAttribute("username"));
		
	}

	@Override
	public int commentRegister(HttpServletRequest req, Model model) {
		// TODO Auto-generated method stub
		String comment = req.getParameter("comment");
		String id = (String)req.getSession().getAttribute("username");
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comment", comment);
		map.put("id", id);
		map.put("boardNum", boardNum);
		
		int cnt = dao.commentRegister(map);
		return cnt;
	}

	@Override
	public int commentDelete(HttpServletRequest req, Model model) {
		int commentNum = Integer.parseInt(req.getParameter("commentNum"));
		
		int cnt = dao.commentDelete(commentNum);
		return cnt;
	}

	@Override
	public int replyRegister(HttpServletRequest req, Model model) {
		String id = (String)req.getSession().getAttribute("username");
		String content = req.getParameter("content");
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		int ref = Integer.parseInt(req.getParameter("ref"));
		int level = Integer.parseInt(req.getParameter("level")) + 1;
		int length = Integer.parseInt(req.getParameter("length")) + 1;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("content", content);
		map.put("boardNum", boardNum);
		map.put("ref", ref);
		map.put("level", level);
		map.put("length", length);
		
		System.out.println(boardNum + "보드넘");
		
		int cnt = dao.replyRegister(map);
		return cnt;
	}
}
