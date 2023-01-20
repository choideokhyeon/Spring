package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.dto.YoilDTO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class SimpleController {
	
	@RequestMapping("/Yoil1")
	public void func1(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		//로그(Log4j) 출력
		log.info("====== /Yoil1 진입 ======");
		
		
		//파라미터
		int year = Integer.parseInt(req.getParameter("year"));
		int month = Integer.parseInt(req.getParameter("month"));
		int day = Integer.parseInt(req.getParameter("day"));
		
		
		//입력값 검증(isValid)
		//생략
		
		//서비스실행(처리)
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfweek = cal.get(Calendar.DAY_OF_WEEK)-1;
		char yoil = "일월화수목금토".charAt(dayOfweek);
		
		//뷰 이동
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>YOIL PAGE</h1>");
		out.print(year + "-" + month + "-" + day + "은 " + yoil + "요일 입니다<br>");
		out.print("</body>");
		out.print("</html>");
		//http://localhost/app/Yoil1?year=년도&month=달&day=일
	}
	


	
//================================================================================
	
	
	
	
	@RequestMapping("/Yoil2")
						//Yoil1의 파라미터 받아오기와 동일
	public void func2(int year, int month, int day, HttpServletResponse resp) throws IOException
	{
		//로그(Log4j) 출력
		log.info("====== /Yoil2 진입 ======");
		
		
		//파라미터
		
		//입력값 검증(isValid)
		//생략
		
		//서비스실행(처리)
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfweek = cal.get(Calendar.DAY_OF_WEEK)-1;
		char yoil = "일월화수목금토".charAt(dayOfweek);
		
		//뷰 이동
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>YOIL2 PAGE</h1>");
		out.print(year + "-" + month + "-" + day + "은 " + yoil + "요일 입니다<br>");
		out.print("</body>");
		out.print("</html>");
	}
	
	
	
//================================================================================
	
	
	@RequestMapping("/Yoil3")
	public void func3(int year, int month, int day) throws IOException
	{
		//로그(Log4j) 출력
		log.info("====== /Yoil3 진입 ======");
		
		
		//파라미터
		log.info(year + "-" + month + "-" + day);
		
		//입력값 검증(isValid)
		//생략
		
		//서비스실행(처리)
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfweek = cal.get(Calendar.DAY_OF_WEEK)-1;
		char yoil = "일월화수목금토".charAt(dayOfweek);
		
		//뷰 이동
	}
	
	

//================================================================================
	
	
	//views 하위 카테고리 접근의 경우
	@RequestMapping("/Yoil4")
	public String func4(int year, int month, int day) throws IOException
	{
		//로그(Log4j) 출력
		log.info("====== /Yoil4 진입 ======");
		
		
		//파라미터
		log.info(year + "-" + month + "-" + day);
		
		
		//입력값 검증(isValid)
		//생략
		
		//서비스실행(처리)
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfweek = cal.get(Calendar.DAY_OF_WEEK)-1;
		char yoil = "일월화수목금토".charAt(dayOfweek);
		
		//뷰 이동
		
		return "yoil/Yoil4";	//return으로 경로 위치 받아옴(확장자 생략가능)
	}
	
	
	
//================================================================================
	
	
	//model 이용하기
	@RequestMapping("/Yoil5")
	public String func5(int year, int month, int day, Model model) throws IOException
	{
		//로그(Log4j) 출력
		log.info("====== /Yoil5 진입 ======");
		
		
		//파라미터
		log.info(year + "-" + month + "-" + day);
		
		
		//입력값 검증(isValid)
		//생략
		
		//서비스실행(처리)
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfweek = cal.get(Calendar.DAY_OF_WEEK)-1;
		char yoil = "일월화수목금토".charAt(dayOfweek);
		
		//뷰 이동
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);
		
		return "yoil/Yoil5";	//return으로 경로 위치 받아옴(확장자 생략가능)
	}
	
	
	
	
//================================================================================
	
	
	//DTO 받아와서 만들기
	@RequestMapping("/Yoil6")
	public String func6(YoilDTO DTO, Model model) throws IOException
	{
		//로그(Log4j) 출력
		log.info("====== /Yoil6 진입 ======");
		
		
		//파라미터
		log.info(DTO.getYear() + "-" + DTO.getMonth() + "-" + DTO.getDay());
		
		
		//입력값 검증(isValid)
		if(! isValid(DTO))
		{
			return "yoil/YoilError";
		}
		
		
		//서비스실행(처리)	
		char yoil = getYoil(DTO);
		
		
		//뷰 이동
		model.addAttribute("year", DTO.getYear());
		model.addAttribute("month", DTO.getMonth());
		model.addAttribute("day", DTO.getDay());
		model.addAttribute("yoil", yoil);
		
		return "yoil/Yoil6";	//return으로 경로 위치 받아옴(확장자 생략가능)
	}
	
	//서비스실행을 대신 해줄 getYoil메소드
	private char getYoil(YoilDTO DTO)
	{
		//서비스실행(처리)
		Calendar cal = Calendar.getInstance();
		cal.set(DTO.getYear(), DTO.getMonth()-1, DTO.getDay());
		int dayOfweek = cal.get(Calendar.DAY_OF_WEEK)-1;
		char yoil = "일월화수목금토".charAt(dayOfweek);
		return yoil;
	}
	
	//유효성 검사
	private boolean isValid(YoilDTO param)
	{
		return true;
	}
}
