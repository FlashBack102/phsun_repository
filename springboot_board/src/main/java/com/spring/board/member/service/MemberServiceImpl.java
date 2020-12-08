package com.spring.board.member.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.board.board.persistence.BoardDAO;
import com.spring.board.member.persistence.MemberDAO;
import com.spring.board.vo.BoardVO;
import com.spring.board.vo.CommentVO;

@Component
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO dao;
	
	@Autowired
	BoardDAO dao2;
	
	@Value("${address}")
    private String address;
	
	@Override
	public void board(HttpServletRequest req, Model model) {
		
		System.out.println(address + "파일 경로");
		
		int cnt = dao.boardCount(); // 게시글 갯수를 가져오는 DAO
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
		// 게시판 버튼을 처음 눌러 nextPage 파라미터에 값이 없어 null일 경우 1페이지라고 설정해줌
		// 하지만 7페이지를 보기 위해 7 버튼을 누른 경우 불러올 페이지 번호를 7페이지로 설정
		
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
		
		Map<String, Object> map = new HashMap<>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<BoardVO> boardList = dao.boardList(map);
		
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
		model.addAttribute("authority", (String)req.getSession().getAttribute("authority"));
	}
	
	@Override
	public void boardRegister(MultipartHttpServletRequest req, Model model) {
		
		MultipartFile file = req.getFile("imageupload");
		String id = (String)req.getSession().getAttribute("username");
		String content = req.getParameter("content");
		String subject = req.getParameter("subject");
		
		String originalfileName = file.getOriginalFilename();
		System.out.println(originalfileName);
		File dest = new File(address + originalfileName);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("content", content);
		map.put("subject", subject);
		map.put("file", originalfileName);
		
		System.out.println(id);
		System.out.println(content);
		System.out.println(subject);
		System.out.println(file);
		
		dao.boardRegister(map);
		
	}

	@Override
	public void boardRead(HttpServletRequest req, Model model) {
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		
		BoardVO vo = dao.boardRead(boardNum);
		List<CommentVO> dtos = dao2.boardCommentList(boardNum);

		List<Integer> level = new ArrayList<>();
		
		for(int i = 0; i < dtos.size(); i++) {
			level.add(dtos.get(i).getComment_level());
		}
		
		model.addAttribute("vo", vo);
		model.addAttribute("dtos", dtos);
		model.addAttribute("level", level);
	}

}
