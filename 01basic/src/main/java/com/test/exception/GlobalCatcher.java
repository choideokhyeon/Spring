package com.test.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class GlobalCatcher {
	
	@ExceptionHandler(NullPointerException.class)
	public String NullpointerEx(Exception ex, Model model)
	{
		log.info("======global NullPointer Exception======");
		model.addAttribute("ex",ex);
		return "error";
	}
	
	@ExceptionHandler(ArithmeticException.class)
	public String ArithmeticEx(Exception ex, Model model)
	{
		log.info("======global Arithmetic Exception======");
		return "error";
	}
}
