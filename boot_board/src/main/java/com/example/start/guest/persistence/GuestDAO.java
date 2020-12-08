package com.example.start.guest.persistence;

import java.util.Map;

import com.example.start.vo.UserVO;

public interface GuestDAO {

	public int register(Map<String, Object> map);
	
	public Map<String, Object> login(String username);
}
