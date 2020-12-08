package com.example.start.member.persistence;

import java.util.List;
import java.util.Map;

import com.example.start.vo.BoardVO;
import com.example.start.vo.UserVO;

public interface MemberDAO {
	
    public UserVO readUser(String username);
    
    public List<String> readAuthority(String username);
    
    public void boardRegister(Map<String, Object> map);
    
    public int boardCount();
    
    public List<BoardVO> boardList(Map<String, Object> map);
    
    public BoardVO boardRead(int boardNum);
}
