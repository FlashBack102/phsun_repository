package com.example.start.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.start.guest.persistence.GuestDAO;
import com.example.start.vo.UserVO;

@Configuration
public class UserService implements UserDetailsService {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Autowired
	GuestDAO dao;

	public UserService() {
		
	}
	
	public UserService(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new UserLoginSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new UserLoginFailureHandler();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("두유노우?");
		System.out.println(username);
		
		Map<String, Object> user = dao.login(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}

		List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
		
		authority.add(new SimpleGrantedAuthority(user.get("AUTHORITY").toString()));
		
		return new UserVO(user.get("USERNAME").toString(),
					  user.get("PASSWORD").toString(),
					  (Integer)Integer.valueOf(user.get("ENABLED").toString()) == 1,
					  true,
					  true,
					  true,
					  authority,
					  user.get("USERNAME").toString()
					  );
	} // 이 메서드는 로그인 버튼을 누르면 실행되는 /guest/login/이 호출 될 때 수행되어짐

}
