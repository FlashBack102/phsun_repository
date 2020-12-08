package com.example.start.admin.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAOImple implements AdminDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int boardDelete(int boardNum) {
		AdminDAO dao = sqlSession.getMapper(AdminDAO.class);
		return dao.boardDelete(boardNum);
	}

}
