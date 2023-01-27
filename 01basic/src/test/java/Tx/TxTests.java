package Tx;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.test.dao.TestDao;
import com.test.dto.TestDto;

import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TxTests {

	@Autowired
	TestDao dao;
	
//	@Test
//	public void test() {
//		log.info("DAO : " + dao);
//		int result = dao.insert(new TestDto(2,"강호동"));
//		if(result > 0)
//		{
//			log.info("INSERT 성공");
//		}
//	}
	
	
	@Autowired
	DataSource ds;
	
	@Test
	public void test2() {
		PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
		TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
		try
		{
			dao.insert(new TestDto(5, "심형탁"));
			dao.insert(new TestDto(5, "심형탁"));
			tm.commit(status);	//commit
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			tm.rollback(status);	//rollback
		}
	}
}
