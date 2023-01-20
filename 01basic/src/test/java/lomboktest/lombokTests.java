package lomboktest;

import org.junit.Test;

import com.test.dto.BoardDTO;
import com.test.dto.PersonDTO;

public class lombokTests {

	@Test
	public void LombokTests() {
		PersonDTO DTO = new PersonDTO();
		DTO.setName("홍길동");
		DTO.setAge("55");
		DTO.setAddr("대구");
		System.out.println(DTO);
	}
	
	@Test
	public void lombokTests2()
	{
		BoardDTO DTO = new BoardDTO();
		System.out.println("BoardDTO : " + DTO);
		
		BoardDTO DTO2 = new BoardDTO("1","1","1","1","1","1","1","1","1");
		System.out.println("BoardDTO2 : " + DTO2);
		
		//@builder : 원하는 값과 순서에 상관없이 삽입가능
		BoardDTO DTO3 = new BoardDTO().builder()
				.no("1")
				.title("제목")
				.content("내용")
				.filename("파일이름")
				.email("example@naver.com")
				.build();
	}

}
