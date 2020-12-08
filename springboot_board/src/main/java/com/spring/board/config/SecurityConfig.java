package com.spring.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.spring.board.security.service.UserLoginFailureHandler;
import com.spring.board.security.service.UserLoginSuccessHandler;
import com.spring.board.security.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserLoginSuccessHandler good;
	
	@Autowired
	UserLoginFailureHandler bad;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
		// css와 js, image에는 시큐리티를 적용하지 않음
	}
	
	@Override
	public void configure(HttpSecurity security) throws Exception {
		security.authorizeRequests()
				// 페이지 권한 설정
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/member/**").hasAnyAuthority("ADMIN", "MEMBER")
				.antMatchers("/guest/**").permitAll()
			.and() // 로그인 설정
				.formLogin()
				.loginPage("/guest/login")
				.successHandler(good)
				.failureHandler(bad)
				.permitAll()
			.and() // 로그아웃 설정
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
			.and() // 403 예외처리 -> 권한거부
				.exceptionHandling().accessDeniedPage("/member/denied");
		
		// authhorizeRequest() : HttpServletRequest에 따라 해당하는 권한을 줌
		// antMatchers로 권한을 설정해줌 permitAll, hasRole, hasAuthority등으로
		// .anyRequest.authenticated() : 모든 요청에 대해 인증된 사용자만 접근가능하게함
		
		// formLogin() : 폼 기반으로 인증하게함
		// .loginPage() : 기본적으로 제공되는 form태그가 아닌 커스텀 로그인 폼을 사용하기 위해 사용
		// -> form의 action경로와 loginPage의 parameter경로가 일치해야지만 로그인을 진행할 수 있음
		// .defaultSuccessUrl() : 로그인이 성공했을 때 이동할 페이지 ex)마이페이지
		// .usernameParameter("parameter name")
		// : 로그인 진행시 input태그의 name값은 username이여야 하지만 이 메서드를 사용함으로써 input태그의
		// name값을 바꿀 수 있음
		
		// .logout() : 로그아웃
		// .logoutRequestMatcher(new AntPathReqeustMatcher("/member/logout"))은
		// 기본적으로 제공하는 로그아웃 url인 /logout이 아니라 url을 변경하여 로그아웃을 진행시킬 수 있게함
		// .invalidateHttpSession은 로그아웃시 Http세션을 초기화할지 안할지 여부를 선택함
		// .deleteCookie("key name") : 로그아웃 진행시, 제거하고자 하는 쿠키를 제거함
		// ex) setCookie를 아이디로 했을 경우, 로그아웃시 Http세션과 이 메서드를 사용하여 아이디가 저장된 쿠키 삭제
		
		// .exceptionHandling().accessDeniedPage("/user/denied") : 
		// exceptionHandling을 통해 예외가 발생했을 때 이동할 페이지를 지정해줄 수 있음
		// 여기에 accessDeniedPage를 추가하여 해당 url에 권한이 없을 경우 이동할 페이지를 지정해줌
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	// 모든 인증이 거쳐 이루어지는 AuthenticationManager를 생성하기 위해 AuthenticationManagerBuilder사용
	// userService(UserServiceImpl)에서 사용자에 관한 내용을 가져옴
	
}
