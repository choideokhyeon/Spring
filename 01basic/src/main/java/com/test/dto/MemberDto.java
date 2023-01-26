package com.test.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
	private String email;
	private String pwd;
	
	private String phone;
	private String zipcode;
	
	//@DateTimeFormat(pattern="yyyy+mm+dd") //WebBinder와 동시적용시에는 WebBinder 적용
//	private Date birth;	//String -> Data
	
	private String addr1;
	private String addr2;
	private String grade;
}
