package com.example.start.guest.persistence;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.start.vo.UserVO;

@Repository
public class GuestDAOImpl implements GuestDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int register(Map<String, Object> map) {
		GuestDAO dao = sqlSession.getMapper(GuestDAO.class);
		return dao.register(map);
	}

	@Override
	public Map<String, Object> login(String username) {
		GuestDAO dao = sqlSession.getMapper(GuestDAO.class);
		return dao.login(username);
	}

}
