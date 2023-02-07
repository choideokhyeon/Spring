package com.test.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberDto2 {
	//230207 Security
	private String userid;
	private String userpw;
	private String userName;
	private boolean enabled;
	private Date regdate;
	private Date updatedate;
	private List<AuthDto2> authList;	//권한 리스트
}