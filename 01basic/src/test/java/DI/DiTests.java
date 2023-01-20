package DI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.dto.BoardDTO;
import com.test.dto.PersonDTO;

import lombok.extern.log4j.Log4j;

//Maven Spring Test 설치 필요
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DiTests {
	
	//@Autowired : 의존성 주입
	@Autowired
	PersonDTO personDTO;
	
	@Autowired
	BoardDTO board;
	
	@Autowired
	PersonDTO personDTO2;

	@Test
	public void test() {
		log.info("PersonDTO : " + personDTO);
		log.info("BoardDTO : " +  board);
		log.info("PersonDTO2 : " + personDTO2);
	}

}
