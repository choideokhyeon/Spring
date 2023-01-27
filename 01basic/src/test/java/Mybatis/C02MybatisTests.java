package Mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.mapper.TestMapper;

import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class C02MybatisTests {
	
	@Autowired
	TestMapper mapper;

	@Test
	public void func1()
	{
		log.info("mapper : " + mapper);
		log.info("CountXML : " + mapper.countXML());
		log.info("CountAt : " + mapper.countAt());
	}
}
