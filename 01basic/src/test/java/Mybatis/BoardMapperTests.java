package Mybatis;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.dto.BoardDto2;
import com.test.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired
	BoardMapper mapper;

	//전체 글 보기
	@Test
	public void getListTest()
	{
		List<BoardDto2> list = mapper.getList();
		list.forEach(dao -> dao.toString());
		log.info(list);
	}
	
	//mapper insert로 DB에 Board테이블에 삽입하기
	@Test
	public void insertTest()
	{
		mapper.insert(new BoardDto2(0, "제목입력", "내용입력", "작성자입력", null, null));
	}
	
	//mapper read를 이용해서 DB에 게시물 하나를 열람하기
	@Test
	public void readTest()
	{
		log.info(mapper.read(9));
	}
	
	//mapper delete를 이용하여 DB에 게시물 삭제
	@Test
	public void DeleteTest()
	{
		mapper.delete(7);
	}
	
	//mapper update를 이용하여 DB에 게시물 수정
	@Test
	public void UpdateTest()
	{
		mapper.update(new BoardDto2(
				9,				//bno
				"제목을수정",		//title
				"내용을수정하세요",	//content
				"작성자수정하기",	//writer
				null, null));	//regdate,update
	}
	
	//mapper getTotalCount로 DB의 전체 게시물 개수 출력
	@Test
	public void getTotalCountTest()
	{
		log.info("게시물 총 개수 : " + mapper.getTotalCount());
	}
}