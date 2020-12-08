package com.example.start.board.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.start.vo.BoardVO;
import com.example.start.vo.CommentVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int boardDelete(int boardNum) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.boardDelete(boardNum);
	}

	@Override
	public BoardVO boardUpdate(int boardNum) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.boardUpdate(boardNum);
	}

	@Override
	public int boardUpdatePro(Map<String, Object> map) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.boardUpdatePro(map);
	}

	@Override
	public List<BoardVO> boardSearch(Map<String, Object> map) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.boardSearch(map);
	}

	@Override
	public List<BoardVO> boardSearchList(Map<String, Object> map) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.boardSearchList(map);
	}

	@Override
	public int commentRegister(Map<String, Object> map) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.commentRegister(map);
	}

	@Override
	public List<CommentVO> boardCommentList(int boardNum) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.boardCommentList(boardNum);
	}

	@Override
	public int commentDelete(int commentNum) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.commentDelete(commentNum);
	}

	@Override
	public int replyRegister(Map<String, Object> map) {
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.replyRegister(map);
	}

}
