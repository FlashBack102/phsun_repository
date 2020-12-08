package com.spring.board.guest.persistence;

import java.util.Map;

import com.spring.board.vo.UserVO;

public interface GuestDAO {

	public int register(Map<String, Object> map);
	
	public Map<String, Object> login(String username);
}
