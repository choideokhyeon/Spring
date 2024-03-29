package AOP;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.aop.MyMath;

import lombok.extern.log4j.Log4j;

//Maven Spring Test 설치 필요
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class AopTests {

	@Autowired
	ApplicationContext ac;
	
	@Test
	public void aopTests() {
		log.info("Application Context : " + ac);
		MyMath math = (MyMath)ac.getBean("myMath");
		math.add(10,20);
		math.add(30, 40, 50);
	}
	
}
