package com.test.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.TestDao;
import com.test.dto.TestDto;
import com.test.service.TestService;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AService{
	
	@Autowired
	ADao adao;
	
	@Autowired
	BService bservice;
	
	@Transactional(rollbackFor=Exception.class)
	public void func1() throws Exception
	{
		adao.insert(new TestDto(1, "aaa"));
		adao.insert(new TestDto(2, "bbb"));
	}
	
	
	
	//Propagation.REQUIRED 기본값
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void func2() throws Exception
	{
		adao.insert(new TestDto(1, "aaa"));
		bservice.func2();
		adao.insert(new TestDto(1, "bbb"));
	}
	
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public void func3() throws Exception
	{
		adao.insert(new TestDto(1, "aaa"));
		bservice.func3();
		adao.insert(new TestDto(1, "bbb"));
	}
}
