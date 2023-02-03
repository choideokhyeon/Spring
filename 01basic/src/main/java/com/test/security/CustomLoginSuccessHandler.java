package com.test.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		System.out.println("Login Success!!");
		
		List<String> roleNames = new ArrayList();
		
		authentication.getAuthorities().forEach((authority) -> {roleNames.add(authority.getAuthority());});
		//admin 계정이라면 ROLE_ADMIN , ROLE_MEMBER(security-context.xml 참고)
		
		//권한 검증 후 권한에 맞게 자동으로 리다이렉트
		if(roleNames.contains("ROLE_ADMIN"))
		{
			response.sendRedirect("admin");
			return ;
		}
		
		if(roleNames.contains("ROLE_MEMBER"))
		{			
			response.sendRedirect("member");
			return ;
		}
	}
	
}
