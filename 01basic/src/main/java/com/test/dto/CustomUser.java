package com.test.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User{
	//230207 Security
	
	private MemberDto2 member;

	public CustomUser(
			String username,
			String password,
			Collection<? extends GrantedAuthority> authorities)
	{
		super(username, password, authorities);
	}
	
	
	public CustomUser(MemberDto2 dto)
	{
		//User 생성자 호출
		super(
				dto.getUserid(),
				dto.getUserpw(),
				dto.getAuthList().stream().map(auth->new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList())
				//List<AuthDto2>	list 를 map으로 꺼내서							//ROLE_USER 
																				//ROLE_ADMIN
				);
		member = dto;
	}
	
}