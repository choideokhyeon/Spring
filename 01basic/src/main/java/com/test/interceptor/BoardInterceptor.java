package com.test.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.test.dto.AuthDto;

import lombok.extern.log4j.Log4j;

@Log4j
public class BoardInterceptor implements HandlerInterceptor {
	
	Map<String, Integer>pageGradeMap = new HashMap();
	
	
	public void init(ServletConfig config) throws ServletException {
	}

	
	//DispatcherServlet -> preHandle -> SubController
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("BoardInterceptor - preHandle");
		
		String path = request.getContextPath();
		
		pageGradeMap.put(path+"/board/list", 1);
		pageGradeMap.put(path+"/board/read", 1);
		pageGradeMap.put(path+"/board/post", 2);
		
		//Request요청 전처리
		System.out.println("[BoardInterceptor] Start!");
		
		//Uri 꺼내오기
		String uri = request.getRequestURI();
		System.out.println("[BoardInterceptor] URI : " + uri);
		
		//Session으로부터 authdto꺼내오기
		HttpSession session = request.getSession(false);
		AuthDto adto =(AuthDto)session.getAttribute("authdto");
		if(adto!=null)
		{
			//페이지 권한 가져오기
			int pageGrade=0;
			pageGrade = pageGradeMap.get(uri);
			//유저 권한 가져오기
			int userGrade=0;
			userGrade = Integer.parseInt(adto.getGrade());
			
			System.out.println("[BoardInterceptor] USERGrade : " + userGrade);
			System.out.println("[BoardInterceptor] PAGEGrade : " + pageGrade);
			
			//익명계정(0) 이 로그인이필요한(1이상)페이지로 접근요청한경우
			if(pageGrade>=1 && userGrade==0)
			{
				throw new ServletException("로그인이 필요한 페이지입니다..");
			}
			if(pageGrade>=2 && userGrade==1)
			{
				throw new ServletException("관리자 권한이 필요한 페이지입니다..");
			}
			
		}
		
		return true;
	}

	
	
	//DispatcherServlet <- postHandle <- SubController
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("BoardInterceptor - postHandle");
	}

	
	
	//view 파일 호출 이후에 실행
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("BoardInterceptor - afterCompletion");
	}
	
}
