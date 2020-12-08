package com.example.start.member.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.start.vo.BoardVO;
import com.example.start.vo.UserVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public UserVO readUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> readAuthority(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void boardRegister(Map<String, Object> map) {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		dao.boardRegister(map);
	}
	
	@Override
	public int boardCount() {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.boardCount(); 
	}

	@Override
	public List<BoardVO> boardList(Map<String, Object> map) {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.boardList(map);
	}

	@Override
	public BoardVO boardRead(int boardNum) {
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.boardRead(boardNum);
	}
	

}
