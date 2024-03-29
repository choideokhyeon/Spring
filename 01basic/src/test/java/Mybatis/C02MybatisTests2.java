package Mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.dto.TestDto;
import com.test.service.TestService;

import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class C02MybatisTests2 {
	
	@Autowired
	TestService service;
	
	//SELECT
	@Test
	public void func1()
	{
		TestDto dto = service.GetTestObject(200);
		log.info("Get DTO : " + dto);
	}
	
	
	//INSERT
	@Test
	public void func2()
	{
		TestDto dto = service.InsertTestObject(new TestDto(0, "TEST1234"));
		log.info("Insert DTO : " + dto);
	}
	
	
	//UPDATE & DELETE
	@Test
	public void func3()
	{
//		service.UpdateTestObject(new TestDto(3, "GO HOME"));
		service.DeleteTestObject(3);
	}
	
	
	//SELECTAll
	@Test
	public void func4()
	{
		List<Map<String,Object>> list = service.SelectAllTestObject();
		log.info("Total : " + list.size());
		list.forEach(map -> {log.info(map.get("id") + "," + map.get("name"));});
	}
	
	
	//SELECTAll + keyword
	@Test
	public void func5()
	{
		Map<String,Object> m = new HashMap();
		m.put("type", "name");		//type:name
		m.put("keyword", "test");	//keyword:test
		List<Map<String,Object>> list = service.SelectAllTestObject(m);
		log.info("Total : " + list.size());
		list.forEach(map -> {log.info(map.get("id") + "," + map.get("name"));});
	}
	
	
	//SELECTAll + keyword(choose)
	@Test
	public void func6()
	{
		Map<String,Object> m = new HashMap();
		m.put("condition", "true");
		m.put("type", "addr");
		m.put("keyword", "광역");
		List<Map<String,Object>> list = service.SelectAllChoose(m);
		log.info("Total : " + list.size());
		list.forEach(map -> {log.info(map.get("id") + "," + map.get("name") + "," + map.get("addr"));});
	}
}
  