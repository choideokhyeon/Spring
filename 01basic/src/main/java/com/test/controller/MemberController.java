package com.test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.dto.MemberDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberController{
	
	@InitBinder
	public void toDate(WebDataBinder binder)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyymmdd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df,false));	//false	: null 허용X
																					//true	: null 허용O
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df2,false));
		
		binder.registerCustomEditor(String[].class , new StringArrayPropertyEditor("-"));	// - 단위로 전화번호를 배열로 분리
	}
	
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public void doGetform()
	{
		log.info("======join form======");
	}
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String doPost(MemberDTO DTO, BindingResult result, Model model)
	{
		log.info("======request save======");
		log.info("result : " + result);
		
		//1 파라미터
		log.info("MemberDTO : " + DTO);
		
		//2 유효성체크
		boolean flag = isValid(DTO);
		if(!flag)
		{
			//메세지 전달
			model.addAttribute("msg", "유효성체크 결과 오류 발생..");
			
			//특정 뷰 이동
			//[redirect:/경로] 해당 경로를 redirect방식으로 보냄
			return "redirect:/member/join";
		}
		
		//3 서비스
		//4 뷰로 이동
//		return "login";
		return "joinresult";
	}


	private boolean isValid(MemberDTO dTO) {
		
		return false;
	}
}
