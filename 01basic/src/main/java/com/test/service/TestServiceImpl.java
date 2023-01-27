package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.TestDao;
import com.test.dto.TestDto;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class TestServiceImpl implements TestService {
	
	@Autowired
	TestDao dao;
	
	@Override
	public void func1() throws Exception
	{
		dao.insert(new TestDto(5, "신동엽"));
		dao.insert(new TestDto(5, "신동엽"));
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void func2() throws Exception
	{
		dao.insert(new TestDto(5, "신동엽"));
		dao.insert(new TestDto(5, "신동엽"));
	}
	
	@Override
	@Transactional	//RuntimeException 발생시에만 적용
	public void func3() throws Exception
	{
		dao.insert(new TestDto(5, "신동엽"));
		throw new RuntimeException();
//		dao.insert(new TestDto(5, "신동엽"));
	}
}
