package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.test.dto.TestDto;

import lombok.extern.log4j.Log4j;

@Repository
@Log4j
public class TestDaoImpl implements TestDao {
	
	@Autowired
	DataSource ds;
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	@Override
	public int insert(TestDto dto) throws Exception
	{
		int result = 0;
		try
		{
			//conn = ds.getConnection(); 일반 db 연결
			conn = DataSourceUtils.getConnection(ds);	//트랜젝션 db 연결(DataSourceUtils 사용)
			log.info("CONN : " + conn);
			pstmt = conn.prepareStatement("insert into tbl_test values(?,?)");
			pstmt.setInt(1, dto.getId());
			pstmt.setString(2, dto.getName());
			result = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;	//트랜젝션 전용 예외 발생시키기
		}
		finally {
			try{pstmt.close();} catch(Exception e) {}
			DataSourceUtils.releaseConnection(conn, ds); //트랙젝션 db 연결해제
		}
		
		return result;
	}
}
