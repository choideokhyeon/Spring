package Tx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.service.TestService;

import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TxTests2 {

	@Autowired
	TestService service;
	
	@Test
	public void func1() throws Exception
	{
		//Tx 기본 값 적용
		service.func1();
	}
	
	@Test
	public void func2() throws Exception
	{
		//모든 Exception에 대한 Tx 적용
		service.func2();
	}
	
	@Test
	public void func3() throws Exception
	{
		//RuntimeException에 대한 Tx 적용
		service.func3();
	}
}
