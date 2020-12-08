package com.spring.board.board.persistence;

import java.util.List;
import java.util.Map;

import com.spring.board.vo.BoardVO;
import com.spring.board.vo.CommentVO;

public interface BoardDAO {

	public int boardDelete(int boardNum);
	
	public BoardVO boardUpdate(int boardNum);
	
	public int boardUpdatePro(Map<String, Object>map);
	
	public List<BoardVO> boardSearch(Map<String, Object>map);
	
	public List<BoardVO> boardSearchList(Map<String, Object>map);
	
	public int commentRegister(Map<String, Object>map);
	
	public List<CommentVO> boardCommentList(int boardNum);
	
	public int commentDelete(int commentNum);
	
	public int replyRegister(Map<String, Object> map);
}
