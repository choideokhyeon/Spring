package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.dto.BoardDto2;

public interface BoardMapper {
	Class<?> Integer = null;

	@Select("select * from tbl_board2 where bno>0")
	public List<BoardDto2> getList();
	
	@Insert
	("insert into tbl_board2(bno,title,content,writer,regdate,updatedate)"
			+ "values(null, #{title},#{content},#{writer},now(),now())")
	public void insert(BoardDto2 board);
	
	@Select("select * from tbl_board2 where bno=#{bno}")
	public BoardDto2 read(int bno);
	
	@Delete("delete from tbl_board2 where bno=#{bno}")
	public int delete (int bno);
	
	@Update
	("update tbl_board2 set title=#{title},content=#{content},writer=#{writer},updateDate=now() where bno=#{bno}")
	public int update(BoardDto2 board);
	
	@Select("select count(*) from tbl_board2 where bno>0")
	public int getTotalCount();
}
