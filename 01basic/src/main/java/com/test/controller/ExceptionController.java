package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	public String error(Exception ex, Model model)
	{
		log.info("======Exception func======");
		model.addAttribute("ex", ex);
		return "error";
	}
	
	@GetMapping("/test1")
	public void func1()
	{
		log.info("======test1 page======");
//		String str = null;
//		str.toString();
		throw new NullPointerException("메세지 : Null 예외 발생");
	}
	
	@GetMapping("/test3")
	public void func2()
	{
		log.info("======test3 page======");
		int n1=10;
		int n2=0;
		log.info(n1/n2);	//산술오류
	}
}
