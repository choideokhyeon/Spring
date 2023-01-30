package Mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.dto.TestDto;
import com.test.mapper.TestMapper;

import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class C02MybatisTests {
	
	@Autowired
	TestMapper mapper;

	//Select
	@Test
	public void func1()
	{
		log.info("mapper : " + mapper);
		log.info("CountXML : " + mapper.countXML());	//TestMapper.xml의 CountXML
		log.info("CountAt : " + mapper.countAt());		//TestMapper 인터페이스의 @Select 어노테이션
	}
	
	
	//Select
	@Test
	public void func2()
	{
		log.info("DTOXML : " + mapper.selectXML(2));	//TestMapper.xml의 SelectXML
		log.info("DTOAt : " + mapper.selectAt(3));		//TestMapper 인터페이스의 @Select 어노테이션
	}
	
	
	//Select
	@Test
	public void func3()
	{
		List<TestDto> list = mapper.selectXML2(1);
		for(TestDto dto : list)
		{
			log.info(dto);
		}
		log.info("=========================================");
		mapper.selectAt2(2).forEach((dto)->{log.info(dto);});
	}
	
	
	//Insert
	@Test
	public void func4()
	{
//		mapper.insertXML(new TestDto(4, "dd"));
//		mapper.insertAt(new TestDto(5, "ee"));
		mapper.insertPARAM(6, "ff");
	}
		
	
	//Insert Autoincrement XML
	@Test
	public void func5()
	{
		//Autoincrement Before
//		TestDto dto = new TestDto(0, "test1");
//		mapper.insertKeyBeforeXML(dto);
//		log.info("beforekey : " + dto.getId());
		
		//Autoincrement After
		TestDto dto = new TestDto(0, "test2");
		mapper.insertKeyAfterXML(dto);
		log.info("afterkey : " + dto.getId());
	}
	
	
	//Insert Autoincrement At
	@Test
	public void func6()
	{
		TestDto dto = new TestDto(0, "test3");
		mapper.insertKeyBeforeAt(dto);
		log.info("beforekey : " + dto.getId());	//insert 되기 전 key 최대값
		
		dto.setName("test4");
		mapper.insertKeyAfterAt(dto);
		log.info("afterkey : " + dto.getId()); //insert 이후의 key 최대값
	}
	
	
	//Update & Delete
	@Test
	public void func7()
	{
//		mapper.updateXML(new TestDto(1, "홍길동"));
//		mapper.deleteXML(1);
		
//		mapper.updateAt(new TestDto(2, "임꺽정"));
		mapper.deleteAt(2);
	}
	
	
	//Insert HashMap
	@Test
	public void func8()
	{
		Map<String,Object> map = new HashMap();
		map.put("id", 200);
		map.put("name", "test200");
		mapper.insertXMLHashmap(map);	
	}
	
	
	//List로 여러개 Insert 하기
	@Test
	public void func9()
	{
		List<TestDto> list = new ArrayList();
		list.add(new TestDto(300,"aaaa"));
		list.add(new TestDto(301,"bbbb"));
		list.add(new TestDto(302,"cccc"));
		list.add(new TestDto(303,"dddd"));
		list.add(new TestDto(304,"eeee"));
		
		Map<String,Object> map = new HashMap();
		map.put("list", list);
		
		mapper.insertXMLHashmap2(map);
	}
	
	
	//Select HashMap
	@Test
	public void func10()
	{
		List<Map<String,Object>> list = mapper.selectXMLHashmap();
		log.info("Total : " + list.size());
		list.forEach(map -> {log.info(map.get("id") + "," + map.get("name"));});
	}
}
