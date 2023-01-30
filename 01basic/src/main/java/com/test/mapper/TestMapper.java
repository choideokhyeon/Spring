package com.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.test.dto.TestDto;

public interface TestMapper {
	
	//XML
	public int countXML();
	//At(어노테이션)
	@Select("Select count(*) from tbl_a")
	public int countAt();
		
	
	
	//XML
	public TestDto selectXML(int id);
	//At(어노테이션)
	@Select("Select * from tbl_a where id=#{id}")
	public TestDto selectAt(int id);
	
	
	//XML
	public List<TestDto> selectXML2(int id);
	//At
	@Select("Select * from tbl_a where id >= #{id}")
	public List<TestDto> selectAt2(int id);
	
	
	
	//INSERT XML
	public int insertXML(TestDto dto);
	//Insert At
	@Insert("insert into tbl_a values(#{id},#{name})")
	public int insertAt(TestDto dto);
	//@Param At 이용하기
	public int insertPARAM(@Param("id")int i, @Param("name")String n);
	
	
	
	//(Autoincrement 상태에서)SelectKey 이용하기
	//XML
	public int insertKeyBeforeXML(TestDto dto);
	public int insertKeyAfterXML(TestDto dto);
	
	//At
	@SelectKey(before=true, keyProperty="id", resultType=int.class, statement={"select max(id) from tbl_a"})
	@Insert("insert into tbl_a values(null,#{name})")
	public int insertKeyBeforeAt(TestDto dto);
	
	@SelectKey(before=false, keyProperty="id", resultType=int.class, statement={"select max(id) from tbl_a"})
	@Insert("insert into tbl_a values(null,#{name})")
	public int insertKeyAfterAt(TestDto dto);	
	
	
	
	//Update & Delete
	//XML
	int updateXML(TestDto dto);
	int deleteXML(int id);
	
	//At
	@Update("Update tbl_a SET name=#{name} where id=#{id}")
	int updateAt(TestDto dto);
	@Update("Delete from tbl_a where id=#{id}")
	int deleteAt(int id);
	
	
	
	//Insert HashMap
	int insertXMLHashmap(Map<String,Object> map);
	int insertXMLHashmap2(Map<String,Object> map);
	
	
	
	//SelectAll
	List<Map<String,Object>> selectXMLHashmap();
	
	
	
	//SelectAll + map(keyword)
	List<Map<String,Object>> selectXMLHashmapif(Map<String,Object> map);
}
