package com.spring.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/index")
public class TestController {
	
	// ----------------------------- Spring boot -----------------------------------
	// Spring boot는 라이브러리 추가를 위해 pom.xml을 사용하는 것은 똑같지만,
	// servlet-context.xml과 web.xml은 다루지 않음
	
	// ----------------------------- Annotation ------------------------------------
	// @RestController는 @Controller + @ResponseBody -> view가 필요업고 json으로 넘겨줘야 하는 곳에 반영
	// @Controller는 상황에 따라 @RequestMapping으로 view를 넘겨주거나 @ResponseBody를 통해 json을 넘겨줌
	// @PathVariable은 url경로로 들어오는 값을 그대로 변수로 사용한다는 뜻
	// -> mapping에 {name}이 있으니 @PathVariable("name")사용지 {name}에 들어오는 값이 String name이라는 변수에 담긴다.
	// @RequestParam은 request.getParameter()와 같음
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView home(ModelAndView mv, HttpServletRequest req, Model model) {
		
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping(value="/postman")
	public String postman() {
		
		return "postman";
	}
	
	/*
	 * @GetMapping("/{name}") public String index(HttpServletRequest req, Model
	 * model, @PathVariable("name") String name) { System.out.println(name +
	 * "이름이다"); return "demo project!!"; }
	 */
}
